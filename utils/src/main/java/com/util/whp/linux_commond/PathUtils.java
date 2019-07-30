package com.util.whp.linux_commond;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author whp 18-7-16
 */
public class PathUtils {

    /**
     * 进程配置转换
     * @param process
     * @param serviceType
     * @return
     */
    public static String parse(String process  , String serviceType) {
        String homeDir ="";
        if(process!=null ) {
            String []javaConfig =  process.split(" ");
            for(String config : javaConfig ) {
                if(config.contains("-Dhadoop.home.dir") && serviceType.equals("hadoop")) {
                    String []dir = config.split("=");
                    homeDir = dir[1];
                    break;
                }else if(config.contains("-Dhbase.home.dir") && serviceType.equals("hbase")) {
                    String []dir = config.split("=");
                    homeDir = dir[1];
                    break;
                }else if( serviceType.equals("hive")) {
                    if(config.contains("-D") || config.contains("-X"))
                        continue;
                    String pattern = "(.*)\\/lib\\/.*$";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(config);
                    if(m.find()){
                        homeDir= m.group(1);
                        break;
                    }
                }
            }
        }
        return homeDir;
    }
}
