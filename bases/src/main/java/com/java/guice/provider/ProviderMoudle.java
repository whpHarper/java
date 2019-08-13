package com.java.guice.provider;

import com.google.inject.AbstractModule;
import org.apache.commons.configuration.Configuration;

public class ProviderMoudle extends AbstractModule {
    protected void configure() {
        bind(Configuration.class).toProvider(ConfigurationProvider.class);
    }
}
