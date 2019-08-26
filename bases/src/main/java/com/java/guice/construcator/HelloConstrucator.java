package com.java.guice.construcator;

/**
 * @author whp 19-8-26
 * @describ
 */
public class HelloConstrucator {
    private Boolean flag=false;

    public Boolean getFlag() {
        return flag;
    }

    public HelloConstrucator(Boolean flag){
        this.flag=flag;
    }

    public HelloConstrucator(){

    }

}
