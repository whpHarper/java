package com.java.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author whp 19-1-14
 */
public class ReetrantLockTest {

    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void testAwait(){
        try {
            lock.lock();
            condition.await();
            System.out.println("线程阻塞方法:"+Thread.currentThread().getName());
            for(int i=0;i<5;i++){
                System.out.println("The current thread is"+Thread.currentThread().getName()+"==i:"+i);
            }
//            condition.signalAll();
        }catch (Exception ex){

        }finally {
            lock.unlock();
        }
    }

    public void signal(){
        try{
            lock.lock();
            System.out.println("线程恢复方法");
            condition.signalAll();
        }catch (Exception ex){}finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        ReetrantLockTest reetrantLockTest=new ReetrantLockTest();
        ExecutorService pool = Executors.newFixedThreadPool(6);
        Thread thread1=new LockThread(reetrantLockTest);
        Thread thread2=new LockThread(reetrantLockTest);
        Thread thread5=new SignalThread(reetrantLockTest);
        Thread thread3=new LockThread(reetrantLockTest);
        Thread thread4=new LockThread(reetrantLockTest);
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.execute(thread5);

        pool.shutdown();
    }
}

class LockThread extends Thread{
    private ReetrantLockTest reetrantLockTest;
    public LockThread(ReetrantLockTest reetrantLockTest){
        this.reetrantLockTest=reetrantLockTest;
    }
    public void run(){
        reetrantLockTest.testAwait();
    }
}

class SignalThread extends Thread{
    private ReetrantLockTest reetrantLockTest;
    public SignalThread(ReetrantLockTest reetrantLockTest){
        this.reetrantLockTest=reetrantLockTest;
    }
    public void run(){
        reetrantLockTest.signal();
    }
}
