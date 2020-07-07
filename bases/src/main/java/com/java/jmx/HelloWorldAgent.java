/*
package com.java.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

*/
/**
 * @author whp 19-1-18
 *//*

public class HelloWorldAgent {
    public static void main(String[] args)throws MalformedObjectNameException, NullPointerException,
            InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, IOException {
        int rmiPort=1099;
        String jmxServerName="testJmxServer";

        Registry registry= LocateRegistry.createRegistry(rmiPort);
        MBeanServer mbs=MBeanServerFactory.createMBeanServer(jmxServerName);

        HtmlAdaptorServer adaptor=new HtmlAdaptorServer();
        ObjectName objectName;
        objectName=new ObjectName(jmxServerName+":name=htmladapter");
        adaptor.setPort(8082);
        adaptor.start();
        mbs.registerMBean(adaptor, objectName);

        ObjectName objName = new ObjectName(jmxServerName + ":name=" + "HelloWorld");
        mbs.registerMBean(new HelloWorld(), objName);

        JMXServiceURL url = new JMXServiceURL(
                "service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/" + jmxServerName);
        System.out.println("JMXServiceURL: " + url.toString());
        JMXConnectorServer jmxConnServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        jmxConnServer.start();
    }
}
*/
