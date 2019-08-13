package com.java.guice.setmap;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.java.guice.ComplexHelloPrinter;
import com.java.guice.IHelloPrinter;
import com.java.guice.SimpleHelloPrinter;

public class SetMoudle extends AbstractModule {
    protected void configure() {
        Multibinder<IHelloPrinter> printers=Multibinder.newSetBinder(binder(),IHelloPrinter.class);
        printers.addBinding().to(SimpleHelloPrinter.class);
        printers.addBinding().to(ComplexHelloPrinter.class);
    }
}
