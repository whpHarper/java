package com.java.guice.names;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.java.guice.IHelloPrinter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class NamesApp {
    @Inject  //注射器
    @Named("simple")
    private IHelloPrinter simpleHelloPrinter;

    @Inject
    @Named("complex")
    private IHelloPrinter complexHelloPrinter;
    public void hello(){
        simpleHelloPrinter.print();
        complexHelloPrinter.print();
    }

    public static void main(String[] args){
        Injector injector=Guice.createInjector(new SampleMoudle());
        NamesApp sample=injector.getInstance(NamesApp.class);
        sample.hello();
    }
}
