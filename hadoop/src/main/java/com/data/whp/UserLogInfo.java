package com.data.whp;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author whp 18-7-4
 */
public class UserLogInfo {
    private static UserLogInfo mLogInfo = null;

    static String  strLogFileName = "";

    private UserLogInfo() {
        createFile();
    }

    public static boolean exists(String pathFileName) {
        File mFile = new File(pathFileName);
        return mFile.exists();
    }

    public static String createFile() {
        try {
            if (strLogFileName == null || strLogFileName.length() < 1 || !exists(strLogFileName)) {
                strLogFileName = System.getProperty("user.dir");
                System.out.println(System.getProperty("user.dir"));

                if (strLogFileName == null) {
                    strLogFileName = "/home/presto/log";
                } else {
                    strLogFileName += "/home/presto/log";
                }
            }
            File dirFile = new File(strLogFileName);
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
            System.out.println("logFileName=[" + strLogFileName + "]");
            strLogFileName += "/flume-events.log";
            File logFile = new File(dirFile, "flume-events.log");
            if(!logFile.exists()){
                logFile.createNewFile();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return strLogFileName;
    }

    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr 写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath,String newstr){
        Boolean bool = false;
        String filein = newstr+"\r\n";//新写入的行，换行
        String temp = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            //文件原有内容
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            try {
                if (pw != null) {
                    pw.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch(IOException ex) {
                ex.printStackTrace();
            }

        }
        return bool;
    }

    /**
     * 启动实例
     * @return
     */
    public static synchronized UserLogInfo instance() {
        if (mLogInfo == null) {
            mLogInfo = new UserLogInfo();
        }

        return mLogInfo;
    }

    public void info(String info) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        mLogInfo.writeFileContent(strLogFileName, df.format(new Date()) + "--:" + info);
    }

    public static void main(String[] args) {
        UserLogInfo userLogInfo=UserLogInfo.instance();
        while(true){
            int random=1+(int)(Math.random()*255);
            String info="INFO FSNamesystem.audit: allowed=true\tugi=root (auth:SIMPLE)\tip=/192.168.1."+random+"\tcmd=listStatus\tsrc=/\tdst=null\tperm=null\tproto=rpc";
            userLogInfo.info(info);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
