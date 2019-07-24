package com.data.hive;

import org.apache.commons.collections.CollectionUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;

import java.util.List;

/**
 * @author whp 19-7-24
 */
public class HiveClientServer {
    public Hive getHiveClient() throws HiveException {
        System.setProperty("hadoop.home.dir", "C:\\Users\\gskj2\\winutils.exe");
        HiveConf hiveConf = new HiveConf();
        hiveConf.set("hive.metastore.local", "false");
        hiveConf.setVar(HiveConf.ConfVars.METASTOREURIS, "thrift://docker2:" + 9083);
        // hiveConf.setIntVar(HiveConf.ConfVars.METASTORE_THRIFT_RETRIES, 3);
        hiveConf.setIntVar(HiveConf.ConfVars.METASTORE_CLIENT_CONNECT_RETRY_DELAY, 60);
        Hive hiveClient = Hive.get(hiveConf);
        return hiveClient;
    }

    /*public void importDataBase(Hive hiveClient) throws HiveException {
        List<String> databaseNames=hiveClient.getAllDatabases();
        if(!CollectionUtils.isEmpty(databaseNames)) {

            for (String databaseName : databaseNames) {
                AtlasEntityWithExtInfo dbEntity = registerDatabase(databaseName);

                if (dbEntity != null) {
                    importTables(dbEntity.getEntity(), databaseName, tableToImport, failOnError);
                }
            }
        }
    }*/
}
