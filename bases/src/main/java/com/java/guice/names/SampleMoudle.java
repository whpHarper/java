package com.java.guice.names;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.java.guice.ComplexHelloPrinter;
import com.java.guice.IHelloPrinter;
import com.java.guice.SimpleHelloPrinter;

public class SampleMoudle extends AbstractModule {
    @Override
    protected void configure() {
        bind(IHelloPrinter.class).annotatedWith(Names.named("simple")).to(SimpleHelloPrinter.class);
        bind(IHelloPrinter.class).annotatedWith(Names.named("complex")).to(ComplexHelloPrinter.class);
    }
}
