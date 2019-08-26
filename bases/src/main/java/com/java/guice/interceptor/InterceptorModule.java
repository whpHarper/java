package com.java.guice.interceptor;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.java.guice.IHelloPrinter;
import com.java.guice.SimpleHelloPrinter;
import com.java.guice.construcator.HelloConstrucator;
import com.java.jmx.HelloWorld;

/**
 * @author whp 19-8-26
 * @describ
 */
public class InterceptorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Boolean.class).toInstance(true);
        bind(IHelloPrinter.class).to(SimpleHelloPrinter.class);
        bindInterceptor(Matchers.any(),Matchers.annotatedWith(HelloAnnotation.class),new HelloInterceptor());
    }
}
