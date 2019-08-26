package com.java.guice.construcator;

import com.google.inject.AbstractModule;

/**
 * @author whp 19-8-26
 * @describ
 */
public class Module  extends AbstractModule {
    @Override
    protected void configure() {
        bind(Boolean.class).toInstance(true);
        bind(HelloConstrucator.class).toInstance(new HelloConstrucator());//toConstructor(HelloConstrucator.class.getConstructor(Boolean.TYPE));
    }
}
