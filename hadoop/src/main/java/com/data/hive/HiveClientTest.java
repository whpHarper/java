package com.data.hive;

import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;

import java.util.List;

/**
 * @author whp 19-7-24
 */
public class HiveClientTest {
    public static void main(String[] args) {
        HiveClientServer hiveClientServer=new HiveClientServer();

        Hive hiveClient= null;
        try {
            hiveClient = hiveClientServer.getHiveClient();
            List<String> databases=hiveClient.getAllDatabases();
            for(String database:databases){
                System.out.println(database);
            }
        } catch (HiveException e) {
            e.printStackTrace();
        }
    }


}
