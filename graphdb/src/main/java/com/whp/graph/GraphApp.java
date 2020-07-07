package com.whp.graph;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Classname GraphApp
 * @Describe 图数据库的定义和实用
 * @Date 2020/7/2
 * @Auth whp
 * @Version 1.0
 */
public class GraphApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphApp.class);

    protected String propFileName;
    protected Configuration conf;
    protected Graph graph;
    protected GraphTraversalSource g;
    protected boolean supportsTransactions;
    protected boolean supportsSchema;
    protected boolean supportsGeoshape;

    /**
     * Constructs a graph app using the given properties.
     * @param fileName location of the properties file
     */
    public GraphApp(final String fileName) {
        propFileName = fileName;
    }

    /**
     * Opens the graph instance. If the graph instance does not exist, a new
     * graph instance is initialized.
     */
    public GraphTraversalSource openGraph() throws ConfigurationException {
        LOGGER.info("opening graph");
        conf = new PropertiesConfiguration(propFileName);
        graph = GraphFactory.open(conf);
        g = graph.traversal();
        return g;
    }

    /**
     * Closes the graph instance.
     */
    public void closeGraph() throws Exception {
        LOGGER.info("closing graph");
        try {
            if (g != null) {
                g.close();
            }
            if (graph != null) {
                graph.close();
            }
        } finally {
            g = null;
            graph = null;
        }
    }

    /**
     * Drops the graph instance. The default implementation does nothing.
     */
    public void dropGraph() throws Exception {
    }

    /**
     * Creates the graph schema. The default implementation does nothing.
     */
    public void createSchema() {
    }

    /**
     * Adds the vertices, edges, and properties to the graph.
     */
    public void createElements() {
        try {
            final Vertex whp=g.addV("person").property( "id",0).property("name","whp").property("age",29).next();

            final Vertex marko=g.addV("person").property( "id",1).property("name","marko").property("age",29).next();
            final Vertex vadas=g.addV("person").property("id",2).property("name","vadas").property("age",27).next();
            final Vertex lop=g.addV("software").property("id",3).property("name","lop").property("lang","java").next();
            final Vertex josh=g.addV("person").property("id",4).property("name","josh").property("age",27).next();
            final Vertex ripple=g.addV("software").property("id",5).property("name","ripple").property("lang","java").next();
            final Vertex peter=g.addV("person").property("id",6).property("name","peter").property("age",35).next();
            g.addE("knows").from(marko).to(vadas).property("id",7).property("weight",0.5).next();
            g.addE("created").from(marko).to(lop).property("id",9).property("weight",0.4).next();
            g.addE("knows").from(marko).to(josh).property("id",8).property("weight",0.1).next();
            g.addE("created").from(josh).to(ripple).property("id",10).property("weight",0.5).next();
            g.addE("created").from(josh).to(lop).property("id",11).property("weight",0.4).next();
            g.addE("created").from(peter).to(lop).property("id",12).property("weight",0.6).next();
            if (supportsTransactions) {
                g.tx().commit();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            if (supportsTransactions) {
                g.tx().rollback();
            }
        }
    }

    /**
     * Returns the geographical coordinates as a float array.
     */
    protected float[] getGeoFloatArray(final float lat, final float lon) {
        final float[] fa = { lat, lon };
        return fa;
    }

    /**
     * Runs some traversal queries to get data from the graph.
     */
    public void readElements() {
        try {
            if (g == null) {
                return;
            }

            LOGGER.info("reading elements");

            // look up vertex by name can use a composite index in JanusGraph
            final Optional<Map<Object, Object>> v = g.V().has("name", "marko").valueMap(true).tryNext();
            if (v.isPresent()) {
                LOGGER.info("there is a person "+v.get().toString());
            } else {
                LOGGER.warn("jupiter not found");
            }
            final Optional<Map<Object, Object>> markoAge=g.V().has("person","name","marko").outE().valueMap(true).tryNext();
            if (markoAge.isPresent()) {
                LOGGER.info(markoAge.get().toString());
            } else {
                LOGGER.warn("hercules battled hydra not found");
            }
            // look up an incident edge
            final Optional<Map<Object, Object>> edge = g.V().has("name", "marko").outE("knows").as("e").inV()
                    .has("name", "vadas").select("e").valueMap(true).tryNext();
            if (edge.isPresent()) {
                LOGGER.info(edge.get().toString());
            } else {
                LOGGER.warn("hercules battled hydra not found");
            }

            // numerical range query can use a mixed index in JanusGraph
            final List<Object> list = g.V().has("age", P.gte(10)).values("age").toList();
            LOGGER.info(list.toString());

            // pluto might be deleted
            final boolean plutoExists = g.V().has("name", "ripple").hasNext();
            if (plutoExists) {
                LOGGER.info("pluto exists");
            } else {
                LOGGER.warn("pluto not found");
            }

            // look up jupiter's brothers
            final List<Object> brothers = g.V().has("name", "marko").both("knows").values("name").dedup().toList();
            LOGGER.info("jupiter's brothers: " + brothers.toString());

        } finally {
            if (supportsTransactions) {
                g.tx().rollback();
            }
        }
    }

    /**
     * Makes an update to the existing graph structure. Does not create any
     * new vertices or edges.
     */
    public void updateElements() {
        try {
            if (g == null) {
                return;
            }
            LOGGER.info("updating elements");
            final long ts = System.currentTimeMillis();
            g.V().has("name", "jupiter").property("ts", ts).iterate();
            if (supportsTransactions) {
                g.tx().commit();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            if (supportsTransactions) {
                g.tx().rollback();
            }
        }
    }
    public void runApp() {
        try {
            // open and initialize the graph
            openGraph();
            if (supportsSchema) {
                createSchema();
            }
            // build the graph structure
            createElements();
            // read to see they were made
            readElements();

            /*for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep((long) (Math.random() * 500) + 500);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                // update some graph elements with changes
                updateElements();
                // read to see the changes were made
                readElements();
            }

            // delete some graph elements
            deleteElements();*/
            // read to see the changes were made
//            readElements();
            // close the graph
            closeGraph();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    /**
     * Deletes elements from the graph structure. When a vertex is deleted,
     * its incident edges are also deleted.
     */
    public void deleteElements() {
        try {
            if (g == null) {
                return;
            }
            LOGGER.info("deleting elements");
            // note that this will succeed whether or not pluto exists
            g.V().has("name", "pluto").drop().iterate();
            if (supportsTransactions) {
                g.tx().commit();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            if (supportsTransactions) {
                g.tx().rollback();
            }
        }
    }

}
