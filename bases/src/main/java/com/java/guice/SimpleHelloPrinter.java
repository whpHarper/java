package com.java.guice;

import javax.inject.Singleton;

@Singleton
public class SimpleHelloPrinter implements IHelloPrinter{

    @Override
    public void print() {
        System.out.println("hello ,simple hello printer");
    }
}
