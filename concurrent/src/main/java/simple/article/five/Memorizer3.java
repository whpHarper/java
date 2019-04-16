package simple.article.five;

import simple.article.five.Computable;

import java.util.Map;
import java.util.concurrent.*;

public class Memorizer3<A,V> implements Computable<A,V> {
    //缓存不存放执行结果，改换为线程任务
    private final Map<A,Future<V>> cache=new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A,V> c;

    public Memorizer3(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) throws InterruptedException {
        Future<V> future=cache.get(arg);
        if(future==null){
            Callable<V> val=new Callable<V>() {
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft=new FutureTask<V>(val);
            future=ft;
            //先存放缓存任务
            cache.put(arg,future);
            //后执行返回结果
            ft.run();
        }
        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
