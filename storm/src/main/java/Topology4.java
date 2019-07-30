import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @author whp 18-7-4
 */
public class Topology4 {
    public static void main(String[] args) throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReaderSpout());
        builder.setBolt("word-counter", new CountMaxBolt()).shuffleGrouping("word-reader");
        Config conf = new Config();
        conf.put(Config.TOPOLOGY_MAX_TASK_PARALLELISM, 1);
        conf.put("wordsFile", "/mnt/domain.log");
        conf.setDebug(false);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-Started-Topologie", conf, builder.createTopology());
        Thread.sleep(10000);
        cluster.killTopology("Getting-Started-Topologie");
        cluster.shutdown();
    }
}
