package parttern.dymaticproxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author whp 19-7-18
 */
public class DynamicProxy {
    public static void main(String[] args){
        HelloImpl hello=new HelloImpl();
        MyInvocationHandler myInvocationHandler=new MyInvocationHandler(hello);
        IHello proxyHello=(IHello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(),HelloImpl.class.getInterfaces(),myInvocationHandler);
        proxyHello.sayHellow();
    }
}
