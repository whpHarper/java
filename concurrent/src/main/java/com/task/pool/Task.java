package com.task.pool;

import java.util.List;
import java.util.Map;

public class Task implements Runnable {
    private int id;
    private String username;
    private String password;
    private int count;
    private int finish;
    private List<IPSegment> ipSegmentList;
    public Task(String username,String passord,List<IPSegment> ipSegments,int id){
        this.id=id;
        this.username=username;
        this.password=password;
        this.count=0;
        for(IPSegment ipseg:ipSegments){
            this.count+=ipseg.length();
        }
        this.ipSegmentList=ipSegments;
        this.finish=0;
    }
    public int getId(){
        return this.id;
    }
    @Override
    public void run() {
        for(String in=nextInt();!in.isEmpty();in=nextInt()){
            System.out.println(Thread.currentThread().getName()+":"+in);
        }
    }

    private synchronized String nextInt(){
        if(ipSegmentList.size()<=0){
            return "";
        }

        IPSegment ipSegment=ipSegmentList.get(0);
        String rt=ipSegment.nextIp();
        if(rt.isEmpty()){
            ipSegmentList.remove(0);
            if(ipSegmentList.size()>0){
                rt=ipSegmentList.get(0).nextIp();
            }
        }
        return rt;
    }

    private synchronized void outResult(String in,Map<String,String> env){
        finish++;
        if(finish>=count){
            EnvDetectTaskPool.getInstance();
        }
    }

    public synchronized int progress() {
        return finish*100/count;
    }

    public synchronized void stop(){
        ipSegmentList.clear();
    }


}
