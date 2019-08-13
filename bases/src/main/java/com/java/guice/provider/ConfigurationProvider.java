package com.java.guice.provider;

import com.google.inject.Provider;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class ConfigurationProvider  implements Provider<Configuration> {

    public Configuration get() {
        try {
            Configuration configuration=new PropertiesConfiguration("application.properties");
            return configuration;
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
