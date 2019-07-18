package parttern.dymaticproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author whp 19-7-18
 */
public class MyInvocationHandler implements InvocationHandler{

    private Object target;//代理目标对象

    public MyInvocationHandler(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke method");
        System.out.println("Method name"+method.getName());
        Object result=method.invoke(target,args);
        return result;
    }
}
