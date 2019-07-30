package parttern.dymaticproxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author whp 19-7-18
 */
public class Test {
    public static void main(String[] args){
        ChinesePoxy chinesePoxy=new ChinesePoxy();

        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Chinese.class);
        enhancer.setCallback(chinesePoxy);

        Chinese chinese=(Chinese)enhancer.create();
        chinese.sayHello();
    }
}
