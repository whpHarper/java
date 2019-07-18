package parttern.dymaticproxy.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author whp 19-7-18
 */
public class ChinesePoxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("invoc method");
        Object intercept=methodProxy.invokeSuper(o,objects);
        return intercept;
    }
}
