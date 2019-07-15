package simple.article.five;

import java.util.concurrent.ExecutionException;

/***
 * 计算接口
 * @param <A> 执行参数
 * @param <V> 返回结果
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException, ExecutionException;
}
