package com.java.callback;

/**
 * @Classname Caller
 * @Describe TODO
 * @Date 2020/6/19
 * @Auth whp
 * @Version 1.0
 */
public class Caller {
    public void call(ICallBack callBack){
        System.out.println("begin......");
        callBack.call();
        System.out.println("end.....");
    }
}
