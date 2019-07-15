package com.java.jmx;

/**
 * @author whp 19-1-18
 */
public class HelloWorld implements HelloWorldMBean {
    private String name;
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void printHello() {
        System.out.println("Hello World, " + name);

    }

    public void printHello(String whoName) {
        System.out.println("Hello , " + whoName);

    }
}
