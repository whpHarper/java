package com.java.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author whp 19-1-14
 */
public class QueueTest {
    public static void main(String[] args){

        BlockingQueue<String> arrayQueue=new ArrayBlockingQueue<String>(3);
        ExecutorService pool = Executors.newFixedThreadPool(6);
        PutThread putThread=new PutThread(arrayQueue);
        TakeThread takeThread=new TakeThread(arrayQueue);
        pool.execute(putThread);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.execute(takeThread);
//        pool.shutdown();
    }

}

class TakeThread extends Thread{
    private BlockingQueue<String> blockingQueue;
    public TakeThread(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    public void run(){
        try {
            System.out.println("block获取：");
            while(!blockingQueue.isEmpty()) {
                String a=blockingQueue.take();
                System.out.println("取出"+a);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PutThread extends Thread{
    private BlockingQueue<String> blockingQueue;
    public PutThread(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    public void run(){
        System.out.println("block插入：");
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
                blockingQueue.put("第"+i+"个值");
                System.out.println("放入第"+i+"个值");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
