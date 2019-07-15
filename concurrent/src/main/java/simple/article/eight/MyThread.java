package simple.article.eight;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread {
    public static final String DEFULT_NAME="MyThread";
    public static volatile boolean debugLifeCycle=false;
    public static final AtomicInteger created=new AtomicInteger();
    private static final AtomicInteger alive=new AtomicInteger();
    private static final Logger log=Logger.getAnonymousLogger();
    public MyThread(Runnable r){
        this(r,DEFULT_NAME);
    }

    public MyThread(Runnable r,String threadName){
        super(r,threadName+"_"+created.getAndIncrement());
        setUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler(){
                    public void uncaughtException(Thread t, Throwable e) {
                        log.log(Level.SEVERE,"uncatch in thread"+t.getName(),e);
                    }
                }
        );
    }

    public void run(){
        boolean debug=this.debugLifeCycle;
        if(debug){
            log.log(Level.FINE,"created "+getName());
        }
        try {
            super.run();
            alive.getAndIncrement();
        }finally {
            alive.getAndDecrement();
            log.log(Level.FINE,"exiting "+getName());
        }
    }

    public static int getCreated(){
        return created.get();
    }

    public static int getAlived(){
        return alive.get();
    }

    public static boolean getDebug(){
        return debugLifeCycle;
    }

    public static void setDebug(boolean d){
        debugLifeCycle=d;
    }
}
