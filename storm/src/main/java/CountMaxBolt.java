import model.Domain;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author whp 18-7-4
 */
public class CountMaxBolt implements IRichBolt{

    private  Integer id;
    private String name;
    private Map<String,String> counters;
    private Map<String,Double> maxNum;
    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext context, OutputCollector outputCollector) {
        this.counters = new HashMap<String, String>();
        this.collector = outputCollector;
        this.name = context.getThisComponentId();
        this.maxNum=new HashMap<>();
        this.id = context.getThisTaskId();
    }

    @Override
    public void execute(Tuple input) {
        String str=input.getString(0);
        String[] strArry=str.split("\t");
        Domain domain=new Domain();
        domain.setDomain(strArry[0]);
        domain.setPrice(Double.parseDouble(strArry[1]));
        domain.setApplyTime(strArry[2]);
        domain.setUsefulTime(Integer.parseInt(strArry[3]));
        domain.setOwner(strArry[4]);
        /**
         * 如果单词尚不存在于map，我们就创建一个，如果已在，我们就为它加1
         */
        if(!counters.containsKey(domain.getApplyTime())){
            counters.put(domain.getApplyTime(),domain.getDomain());
            maxNum.put(domain.getApplyTime(),domain.getPrice());
        }else{
            Double maxPrice=maxNum.get(domain.getApplyTime());
            if(domain.getPrice()>maxPrice){
                counters.put(domain.getApplyTime(),domain.getDomain());
                maxNum.put(domain.getApplyTime(),domain.getPrice());
            }
        }
        collector.ack(input);
    }

    /**
     * 这个spout结束时（集群关闭的时候），10秒后自动打印
     */
    @Override
    public void cleanup() {
        System.out.println("================"+counters.toString()+"===================");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
