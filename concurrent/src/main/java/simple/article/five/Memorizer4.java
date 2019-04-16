package simple.article.five;

import java.util.Map;
import java.util.concurrent.*;

public class Memorizer4<A,V> implements Computable<A,V> {
    private final ConcurrentMap<A,Future<V>> cache=new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A,V> c;

    public Memorizer4(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) throws InterruptedException, ExecutionException {
        Future<V> value=cache.get(arg);
        if(value==null) {
            Callable<V> cal = new Callable<V>() {
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(cal);
            value=ft;
            //在存放过程中再次判断是否存在
            cache.putIfAbsent(arg,value);
            ft.run();
        }

        return value.get();
    }
}
