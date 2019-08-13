package com.java.guice.setmap;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.java.guice.ComplexHelloPrinter;
import com.java.guice.IHelloPrinter;
import com.java.guice.SimpleHelloPrinter;
import javafx.beans.binding.MapBinding;

public class MapMoudle extends AbstractModule {

    protected void configure() {
        MapBinder<String,IHelloPrinter> priters=MapBinder.newMapBinder(binder(),String.class,IHelloPrinter.class);
        priters.addBinding("simple").to(SimpleHelloPrinter.class);
        priters.addBinding("complex").to(ComplexHelloPrinter.class);
    }
}
