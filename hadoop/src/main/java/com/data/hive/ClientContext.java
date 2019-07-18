package com.data.hive;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;
import org.apache.commons.configuration.SubsetConfiguration;

import java.util.HashMap;

/**
 * @author whp 19-7-18
 */
public class ClientContext {
    private final Configuration configuration;

    public ClientContext() {
        configuration = new MapConfiguration(new HashMap<>());
    }

    private static class Context {
        private final ClientContext clientContext;
        protected final Configuration configuration;

        private Context(ClientContext clientContext, String prefix) {
            this.clientContext = clientContext;
            this.configuration = new SubsetConfiguration(clientContext.configuration, prefix);
        }

        public ClientContext end() {
            return clientContext;
        }
    }

    public static ClientContext withHive(final String username, final String password, final String host, final int port) {
        ClientContext context = new ClientContext();
        context.configuration.addProperty("username", username);
        context.configuration.addProperty("password", password);
        context.configuration.addProperty("host", host);
        context.configuration.addProperty("port", port);
        return context;
    }

    public String username() {
        return configuration.getString("username");
    }

    public String clientport() {
        return configuration.getString("clientport");
    }

    public String quorum() {
        return configuration.getString("quorum");
    }

    public String parent() {
        return configuration.getString("parent");
    }

    public String password() {
        return configuration.getString("password");
    }

    public String host() {
        return configuration.getString("host");
    }

    public int port() {
        return configuration.getInt("port");
    }
}
