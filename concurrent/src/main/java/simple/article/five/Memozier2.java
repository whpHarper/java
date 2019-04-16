package simple.article.five;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class Memozier2<A,V> implements Computable<A,V> {
    //使用线程安全容器
    private final Map<A,V> cache=new ConcurrentHashMap<A,V>();
    private final Computable<A,V> c;

    public Memozier2(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException, ExecutionException {
        //该容器类没有控制并发读，两个同时执行此步骤时，会导致同一个key下写入两次
        V result=cache.get(arg);
        if(result==null){
            result=c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
