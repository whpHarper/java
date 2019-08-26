package com.java.guice;

import com.java.guice.interceptor.HelloAnnotation;

import javax.inject.Singleton;

@Singleton
public class SimpleHelloPrinter implements IHelloPrinter{

    @Override
    @HelloAnnotation
    public void print() {
        System.out.println("hello ,simple hello printer");
    }
}
