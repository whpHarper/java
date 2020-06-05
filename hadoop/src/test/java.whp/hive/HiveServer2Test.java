package hive;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Classname HiveServer2Test
 * @Describe TODO
 * @Date 2020/6/4
 * @Auth whp
 * @Version 1.0
 */
public class HiveServer2Test {
    private static String driverName="org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://192.168.1.242:10000/;principal=hive/docker2@HADOOP.COM";
    private static String user = "hdfs";
    private static String password="hdfs";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    @Before
    public void init() throws Exception{
        Configuration conf=new Configuration(   );
        conf.set( "hadoop.security.authentication", "Kerberos");
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            // 默认：这里不设置的话，win默认会到 C盘下读取krb5.init
            System.setProperty("java.security.krb5.conf", "C:\\ProgramData\\MIT\\Kerberos5\\krb5.ini");
        } // linux 会默认到 /etc/krb5.conf 中读取krb5.conf,本文笔者已将该文件放到/etc/目录下，因而这里便不用再设置了
        try {
            UserGroupInformation.setConfiguration(conf);
            UserGroupInformation.loginUserFromKeytab("hive/docker2@HADOOP.COM", "D:\\hive.keytab");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
    }
    @Test
    public void createDatabase() throws Exception{
        String sql = "create database hive_jdbc_whp";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }
    @Test
    public void dropDatabase() throws Exception {
        String sql = "drop database if exists hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    @Test
    public void showDatabases() throws Exception {
        String sql = "show databases";
        System.out.println("Running: " + sql + "\n");
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1) );
        }
    }

    @Test
    public void createTable() throws Exception {
        String sql = "create table t2(id int ,name String) row format delimited fields terminated by ',';";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    @Test
    public void loadData() throws Exception {
        String filePath = "/usr/tmp/student";
        String sql = "load data local inpath '" + filePath + "' overwrite into table t2";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    @Test
    public void selectData() throws Exception {
        String sql = "select * from t2";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        System.out.println("编号" + "\t" + "姓名" );
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
        }
    }
  /*  @Test
    public void drop(Statement stmt) throws Exception {
        String dropSQL = "drop table t2";
        boolean bool = stmt.execute(dropSQL);
        System.out.println("删除表是否成功:" + bool);
    }*/
    @After
    public void destory() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

}
