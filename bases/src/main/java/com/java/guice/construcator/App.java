package com.java.guice.construcator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.java.guice.names.SampleMoudle;

/**
 * @author whp 19-8-26
 * @describ
 */
public class App {
    public static void main(String[] args) {
        Injector injector= Guice.createInjector(new Module());
        HelloConstrucator helloConstrucator=injector.getInstance(HelloConstrucator.class);

        System.out.println(helloConstrucator.getFlag());
    }
}
