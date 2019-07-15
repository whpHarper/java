package com.imooc.miaosha.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class RedisDistributionLock implements Lock {

    @Autowired
    JedisPool jedisPool;
    //锁信息上下文，保存当前锁的持有人id
    private ThreadLocal<String> localContext=new ThreadLocal<String>();
    //默认过期时间
    private long timeout=100;
    //记录当前获得锁的线程
    private Thread exclusiveOwnerThread;//已经获取锁的线程

    public RedisDistributionLock(){

    }
    @Override
    public void lock() {
        //每100ms尝试一次获取锁
        while(!tryLock()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
            while(!tryLock()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

    @Override
    public boolean tryLock() {
        return tryLock(timeout,TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        String id= UUID.randomUUID().toString();//为每个持有人分配唯一id
        Thread t=Thread.currentThread();
        Jedis jedis=jedisPool.getResource();
        //放入锁的同时设置过期时间
        if("ok".equals(jedis.set("lock",id,"NX","PX",unit.toMillis(time)))){
            //记录下当前线程用户
            localContext.set(id);
            //记录下锁现有持有人
            setExclusiveOnwnerThread(t);
            return true;
        }else if(exclusiveOwnerThread==t){
            return true;
        }
        return false;
    }

    private void setExclusiveOnwnerThread(Thread t){
        exclusiveOwnerThread=t;
    }
    @Override
    public void unlock() {
        String script=null;
        try{
            Jedis jedis=jedisPool.getResource();
            script=inputStream2String(getClass().getResourceAsStream("/redis.script"));
            if(localContext.get()==null){
                return ;
            }
            //使用lua脚本保持一致性
            jedis.eval(script, Arrays.asList("lock"),Arrays.asList(localContext.get()));
            localContext.remove();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream in) throws InterruptedException, IOException {
        StringBuffer out= new StringBuffer();
        byte[] b=new byte[4096];
        for (int n;(n=in.read(b))!=-1;){
            out.append(new String(b,0,n));
        }
        return out.toString();
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
