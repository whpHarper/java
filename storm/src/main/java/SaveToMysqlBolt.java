import model.Domain;
import mysql.DBProvider;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author merlin
 * @create 2017-09-27 下午3:11
 */
public class SaveToMysqlBolt implements IRichBolt {
  private  Integer id;
  private String name;
  private Map<String,Integer> counters;
  private OutputCollector collector;
  private DBProvider provider;
  /**
   * 初始化
   */
  @Override
  public void prepare(Map map, TopologyContext context, OutputCollector outputCollector) {
    this.counters = new HashMap<String, Integer>();
    this.collector = outputCollector;
    this.name = context.getThisComponentId();
    this.id = context.getThisTaskId();
    this.provider=new DBProvider();
  }

  /**
   *  为每个单词计数
   */
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
    Connection conn = null;
    Statement stmt = null;
    if((domain.getDomain().contains("net")||domain.getDomain().contains("com")||domain.getDomain().contains("org")||domain.getDomain().contains("cn")) && (domain.getPrice()>200.0 && domain.getPrice()<2000.0))
    try {
      conn = provider.getConnection();
      stmt = conn.createStatement() ;
      stmt.executeUpdate("INSERT INTO t_domain (domain, price,apply_time,useful_time,owner)" +
              " VALUES ('" + domain.getDomain() + "'," + domain.getPrice() + ",'"+domain.getApplyTime()+"',"+domain.getUsefulTime()+",'"+domain.getOwner()+"')");
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      if(stmt != null){
        try {
          stmt.close();
          stmt = null;
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
      if(conn != null){
        try {
          conn.close();
          conn = null;
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    }

  }

  /**
   * 这个spout结束时（集群关闭的时候），我们会显示单词数量
   */
  @Override
  public void cleanup() {

  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

  }

  @Override
  public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
