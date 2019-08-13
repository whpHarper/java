package com.java.guice.provider;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.configuration.Configuration;

public class Test {

    public static void main(String[] args) {
        Injector injector= Guice.createInjector(new ProviderMoudle());
        Configuration configuration=injector.getInstance(Configuration.class);
        System.out.printf("configuration value:"+configuration.getString("user.name"));
    }
}
