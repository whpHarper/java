package com.util.whp.linux_commond;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by whp on 2017/12/28.
 */
public class CtrCommond {

  private static final Logger LOG = LoggerFactory.getLogger(CtrCommond.class);

  private static final String id_rsa_path =null;
  /**
   * 获取服务器链接
   */
  public static Connection getConn(String hostName, String userName, int sshPort, String passWord) {
    try {
      Connection conn = new Connection(hostName, sshPort);
      //连接到主机
      conn.connect();
      //使用用户名和密码校验
      boolean isconn = conn.authenticateWithPassword(userName, passWord);

      if (!isconn) {
        LOG.info("用户名称或者是密码不正确");
      } else {
        return conn;
      }
    } catch (IOException e) {
      LOG.error("获取服务器链接出现异常：" + e.toString());
      return null;
    }
    return null;
  }

  public static Connection getConn(String hostName, String userName, int sshPort,
                                   char[] pemPrivateKey, String password) {
    try {
      Connection conn = new Connection(hostName, sshPort);
      //连接到主机
      conn.connect();
      //使用PublicKey校验
      boolean isconn = conn.authenticateWithPublicKey(userName, pemPrivateKey, password);
      if (!isconn) {
        LOG.info("用户名称或者是密码不正确");
      } else {
        return conn;
      }
    } catch (IOException e) {
      LOG.error("获取服务器链接出现异常：" + e.toString());
      return null;
    }
    return null;
  }

  public static Connection getConn(String hostName, String userName, int sshPort,
                                   File pemFile, String password) {
    try {
      Connection conn = new Connection(hostName, sshPort);
      //连接到主机
      conn.connect();
      boolean isconn = conn.authenticateWithPublicKey(userName, pemFile, password);
      if (!isconn) {
        LOG.info("用户名称或者是密码不正确");
      } else {
        return conn;
      }
    } catch (IOException e) {
      LOG.error("获取服务器链接出现异常：" + e.toString());
      return null;
    }
    return null;
  }

  /**
   * 远程执行命令
   */
  public static String doCommond(Connection conn, String cmd) {
    String result = "";
    try {
      if (conn == null) {
        LOG.info("请先链接服务器");
      } else {
        Session sess = conn.openSession();
        sess.execCommand(cmd);
        InputStream stdout = new StreamGobbler(sess.getStdout());
        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
        while (true) {
          String line = stdoutReader.readLine();
          if (line == null) {
            break;
          }
          result += line + StaticKeys.SPLIT_BR;
        }
        //连接的Session和Connection对象都需要关闭
        stdoutReader.close();
        sess.close();
      }
    } catch (IOException e) {
      LOG.error("执行linux命令错误：" + e.toString());
    }
    if (result.endsWith(StaticKeys.SPLIT_BR)) {
      result = result.substring(0, result.length() - StaticKeys.SPLIT_BR.length());
    }

    if (!StringUtils.isEmpty(result)) {
      if (cmd.contains("DEV") || cmd.contains("iostat")) {
        if (result.contains("</br></br>")) {
          result = result.substring(result.lastIndexOf("</br></br>") + 10);
        }
      }
      if (cmd.contains("mpstat")) {
        if (result.contains("</br></br>")) {
          result = result.substring(result.lastIndexOf("</br></br>") + 10);
          int s = result.indexOf("</br>") + 5;
          s = result.indexOf("</br>", s);
          result = result.substring(0, s);
        }
      }
    }
    return result;
  }

  public class LinuxCmd {

    /**
     * 根据进程名称查询进程
     */
    public static final String PID = "echo %s | sudo -S ps aux | grep -i [%s]%s ";


    public static final String Cat = "echo %s | sudo -S cat %s ";

    public static final String VIEW_MEM = "free -m";//查看内存状态

    public static final String SYSTEM_RELEASE = "cat /etc/system-release";//查看系统版本

    public static final String UNAME_A = "uname -a";//查看系统详细信息

    public static final String DF_HL = "df -k | grep '^\\/dev/' | awk '{print $2,$3,$4}'";//查看磁盘空间

    //物理cpu个数
    public static final String WULI_CPU_NUM = "cat /proc/cpuinfo| grep \"physical id\"| sort| uniq| wc -l";

    //每个cpu的核数
    public static final String WULI_CPU_CORE_NUM = "cat /proc/cpuinfo| grep \"cpu cores\"| uniq";

    //cpu型号信息
    public static final String CPU_XINGHAO = "cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c";

    //cpu使用情况
    public static final String VMSTAT = "top -b -n 1 | sed -n '3p'";

    //查看服务器网络吞吐率
    public static final String SAR_DEV_1 = "sar -n DEV 1 3";

    //查看磁盘IO使用情况
    public static final String DISK_IO = "iostat -xkz 1 1";

    //tcp状态
    public static final String SAR_TCP_1 = "sar -n TCP 1 3";

    //查看系统负载状态
    public static final String UPTIME = "uptime";

    //根据PID查看进程状态
    public static final String dd = "ps aux|head -1;ps aux|grep {pid}";

    public static final String rpcinfo = "rpcinfo -p";//看rpc服务开放

    public static final String lsmod = "lsmod";//检查系统内核模块

    public static final String passwd_update_time = "ls -l /etc/passwd";//查看passwd文件修改时间

    public static final String crontab = "cat /etc/crontab";//查看计划任务

    public static final String promisc = "ip link | grep promisc";//检查网络：ip link | grep PROMISC（正常网卡不该在promisc模式，可能存在sniffer）

    public static final String date = "date \"+%Y-%m-%d %H:%M:%S\"";
    public static final String ntp = "ntpdate ";

    public static final String reboot = "reboot ";

    public static final String shutdown = "shutdown -h now ";
  }

}
