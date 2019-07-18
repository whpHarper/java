package com.data.hive;

import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.auth.PlainSaslHelper;
import org.apache.hive.service.cli.thrift.TCLIService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import javax.security.sasl.SaslException;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * @author whp 19-7-18
 */
public class HiveSession extends BaseSession implements Closeable{

    TCLIService.Client client;
    TTransport transport;


    public HiveSession(ClientContext clientContext){
        this.executorService= Executors.newCachedThreadPool();
        client=createClient(clientContext);
    }

    protected TCLIService.Client createClient(ClientContext clientContext) {
        TTransport ot = HiveAuthFactory.getSocketTransport(clientContext.host(), clientContext.port(), 4000);

        TCLIService.Client client = null;
        try {
            transport = PlainSaslHelper.getPlainTransport(clientContext.username(), clientContext.password(), ot);
            client = new TCLIService.Client(new TBinaryProtocol(transport));
            transport.open();
        } catch (SaslException | TTransportException e) {

        }

        return client;
    }

    public static HiveSession login(String username, String password, String host, int port) {
        return new HiveSession(ClientContext.withHive(username, password, host, port));
    }

    @Override
    public void close() throws IOException {
        try {
            shutdown();
        } catch (InterruptedException e) {
        }
    }

    public void shutdown() throws InterruptedException, IOException {
        try {
            executorService.shutdownNow();
        } finally {
            closeClient();
        }
    }
    private void closeClient() throws IOException {
        if (transport != null) {
            transport.close();
        }
    }

    public TCLIService.Client getClient() {
        return client;
    }
}
