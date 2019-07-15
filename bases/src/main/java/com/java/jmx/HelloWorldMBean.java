package com.java.jmx;

/**
 * @author whp 19-1-18
 */
public interface HelloWorldMBean {
    public String getName();
    public void setName(String name);
    public void printHello();
    public void printHello(String whoName);
}
