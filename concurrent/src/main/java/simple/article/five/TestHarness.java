package simple.article.five;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException {
        final CountDownLatch startGate=new CountDownLatch(1);
        final CountDownLatch endGate=new CountDownLatch(nThreads);

        for(int i=0;i<nThreads;i++){
            Thread thread=new Thread(){
              public void run(){
                  try {
                      startGate.wait();
                      try {
                          task.run();
                      }finally {
                          endGate.countDown();
                      }
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
            };
        }
        Long startTime=System.nanoTime();
        startGate.countDown();
        endGate.wait();
        Long endTime=System.nanoTime();
        return endTime-startTime;
    }
}
