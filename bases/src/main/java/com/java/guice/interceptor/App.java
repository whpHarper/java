package com.java.guice.interceptor;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.java.guice.IHelloPrinter;
import com.java.guice.SimpleHelloPrinter;
import com.java.guice.construcator.HelloConstrucator;
import com.java.guice.construcator.Module;

/**
 * @author whp 19-8-26
 * @describ
 */
public class App {
    public static void main(String[] args) {
        Injector injector= Guice.createInjector(new InterceptorModule());
        IHelloPrinter helloPrinter=injector.getInstance(SimpleHelloPrinter.class);
        helloPrinter.print();
    }
}
