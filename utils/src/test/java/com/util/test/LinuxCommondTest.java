package com.util.test;

import ch.ethz.ssh2.Connection;
import com.util.whp.linux_commond.CommandUtils;
import com.util.whp.linux_commond.CtrCommond;
import com.util.whp.linux_commond.PathUtils;
import org.junit.Test;
/**
 * @author whp 18-7-16
 */

public class LinuxCommondTest {
    private final String HOST="192.168.1.221";
    private final int PORT=22;
    private final String USER_NAME="root";
    private final String PASSWORD="123qwe";
    private final String PROCESS_NAME="namenode";

    @Test
    public void testLoadXML(){
        Connection conn= CtrCommond.getConn(HOST,USER_NAME,PORT,PASSWORD);
        String process= CommandUtils.getProcess(conn,PROCESS_NAME,PASSWORD);
        System.out.println("===process=="+process);
        String homeDir= PathUtils.parse(process,"hadoop");
        System.out.println("===homDir=="+homeDir);
        String []hadoopDefaultConfig = {"hdfs-site.xml","core-site.xml","yarn-site.xml","mapred-site.xml"};
        for(String defaultConfig : hadoopDefaultConfig) {
            String configDir = homeDir + "/etc/hadoop/" + defaultConfig;
            String xmlFile = CommandUtils.catConfig(conn, configDir, PASSWORD);
            System.out.println(xmlFile);
        }
    }
}
