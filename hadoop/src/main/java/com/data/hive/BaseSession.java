package com.data.hive;

import org.apache.http.HttpHost;
import org.apache.http.protocol.BasicHttpContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author whp 19-7-18
 */
public class BaseSession {

    HttpHost httpHost;
    BasicHttpContext context;
    ExecutorService executorService;

    Map<String,String> header=new HashMap<>();

    public <T> Future<T> excutelater(Callable<T> callable){
        return executorService.submit(callable);
    }

    public void waitFor(long timeout, TimeUnit units,Future<?> ... futures) throws InterruptedException, ExecutionException, TimeoutException {
        if(futures!=null){
            timeout=TimeUnit.MILLISECONDS.convert(timeout,units);
            long start;
            for(Future future:futures) {
                start = System.currentTimeMillis();
                future.get(timeout, TimeUnit.MILLISECONDS);
                timeout -= (System.currentTimeMillis() - start);
            }
        }
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }
}
