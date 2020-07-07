package com.java.callback;

/**
 * @Classname CallBackTest
 * @Describe TODO
 * @Date 2020/6/19
 * @Auth whp
 * @Version 1.0
 */
public class CallBackTest {
    public static void main(String[] args) {
        Caller caller=new Caller();
        caller.call( ()->{
            System.out.println("访问成功");
        } );
    }
}
