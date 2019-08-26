package com.java.guice;

import javax.inject.Singleton;

@Singleton  //声明实例为单例模式
public class ComplexHelloPrinter implements IHelloPrinter{
    @Override
    public void print() {
        System.out.println("hello,complex hello printer");
    }

}
