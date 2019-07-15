package simple.article.five;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Memozier1<A,V> implements Computable<A,V> {
    //缓存使用容器类map进行盛装
    private final Map<A,V> cache=new HashMap<A, V>();
    //真正计算类，定义为final确保引用不会重新指向其他对象
    private final Computable<A,V> c;

    public Memozier1(Computable<A, V> c) {
        this.c = c;
    }

    //使用同步关键字对缓存方法进行加锁
    public synchronized V compute(A arg) throws InterruptedException, ExecutionException {
        V result=cache.get(arg);
        if(result==null){
            result=c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
