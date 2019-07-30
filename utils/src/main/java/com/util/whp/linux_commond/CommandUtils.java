package com.util.whp.linux_commond;

import ch.ethz.ssh2.Connection;

import java.text.DecimalFormat;

/**
 * Created by whp on 2017/12/28.
 */
public class CommandUtils {

  private static DecimalFormat dFormat =new DecimalFormat("#.0");

  /**
   * 获得进程信息，从进程中得到服务部署地址
   * @param conn
   * @param processName
   * @param pwd
   * @return
   */
  public static String getProcess(Connection conn , String processName , String pwd ){
    String head = processName.substring(0,1);
    String end = processName.substring(1);
    String shellString = String.format( CtrCommond.LinuxCmd.PID , pwd,head,end);
    return  CtrCommond.doCommond(conn,shellString );
  }

  /**
   * 使用cat获得到文件内容信息
   * @param conn
   * @param configDir
   * @param pwd
   * @return
   */
  public static String catConfig(Connection conn , String configDir , String pwd ){

    String shellString = String.format( CtrCommond.LinuxCmd.Cat , pwd,configDir);
    return  CtrCommond.doCommond(conn,shellString );
  }

}


