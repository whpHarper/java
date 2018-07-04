import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @author whp 18-7-4
 */
public class Topology {
    public static void main(String[] args) throws InterruptedException {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReaderSpout());
        builder.setBolt("word-normalizer", new WordNormalizer()).shuffleGrouping("word-reader");
        builder.setBolt("word-counter", new SaveToMysqlBolt()).shuffleGrouping("word-normalizer");
        Config conf = new Config();
        conf.put(Config.TOPOLOGY_MAX_TASK_PARALLELISM, 1);
        //conf.put(Config.STORM_LOCAL_DIR,"/Users/merlin/Documents/eclipse-workspace/ideaProject/hive-resource/src/test/java/com/example/demo/storm");
        conf.put("wordsFile", "/mnt/domain.log");
        conf.setDebug(false);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-Started-Topologie", conf, builder.createTopology());
        Thread.sleep(60000);
        cluster.killTopology("Getting-Started-Topologie");
        cluster.shutdown();
    }
}
