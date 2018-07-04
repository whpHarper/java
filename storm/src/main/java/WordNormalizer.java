import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author merlin
 * @create 2017-09-27 下午3:08
 */
public class WordNormalizer implements IRichBolt {
  private OutputCollector collector;

  @Override
  public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
    this.collector=outputCollector;
  }

  /**
   * *bolt*从单词文件接收到文本行，并标准化它。
   * 文本行会全部转化成小写，并切分它，从中得到所有单词。
   */
  @Override
  public void execute(Tuple input) {
    String sentence = input.getString(0);
    String[] words = sentence.split(" ");
    for(String word : words){
      word = word.trim();
      if(!word.isEmpty()){
        word=word.toLowerCase();
        //发布这个单词
        List a = new ArrayList();
        a.add(input);
        System.out.println("发布这个单词"+word);
        collector.emit(a,new Values(word));
      }
    }
    //对元组做出应答
    System.out.println("对WordNormalizer元组做出应答");
    collector.ack(input);
  }

  @Override
  public void cleanup() {

  }
  /**
   * 这个*bolt*只会发布“word”域
   */
  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    System.out.println("这个*bolt*只会发布“word”域");
    declarer.declare(new Fields("word"));
  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
