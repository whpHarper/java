package com.java.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whp 19-2-21
 */
public class JVMTest {
    private static int count;

    public static void count(){
/*        System.out.println("JVM配置最大内存："+Runtime.getRuntime().maxMemory()/1024/1024+"M");
        System.out.println("JVM配置可用内存："+Runtime.getRuntime().freeMemory()/1024/1024+"M");
        System.out.println("JVM总内存："+Runtime.getRuntime().totalMemory()/1024/1024+"M");

        byte[] b=null;
        for(int i=0;i<10;i++){
            b=new byte[1*100*1024];
        }*/
//        -Xmx10m -Xms1m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
      /*  List<Object> listObject=new ArrayList<>();
        for(int i=0;i<10;i++){
            System.out.println("i:"+i);
            Byte[] bytes=new Byte[1*1024*1024];
            listObject.add(bytes);
        }
        System.out.println("添加成功");*/
      try{
          count++;
          count();
      }catch(Throwable e){
          System.out.println("最大深度："+count);
          e.printStackTrace();
      }

    }

    public static void main(String[] args){
//        count();
    }
}
