package com.java.guice.setmap;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.java.guice.IHelloPrinter;

import java.util.Map;
import java.util.Set;

@Singleton
public class SetAndMapAPP {
    /*@Inject
    private Set<IHelloPrinter> printers;*/

    @Inject
    private Map<String,IHelloPrinter> map;

/*    public void setSayHello(){
        for(IHelloPrinter iHelloPrinter:printers){
            iHelloPrinter.print();
        }
    }*/

    public void mapSayHello(){
        for(String name:map.keySet()){
            map.get(name).print();
        }
    }
    public static void main(String[] args) {
       /* Injector injector= Guice.createInjector(new SetMoudle());
        SetAndMapAPP setAPP=injector.getInstance(SetAndMapAPP.class);
        System.out.println("==============set=============");
        setAPP.setSayHello();*/
        Injector mapInjector= Guice.createInjector(new MapMoudle());
        SetAndMapAPP mapAPP=mapInjector.getInstance(SetAndMapAPP.class);
        System.out.println("===============map===============");
        mapAPP.mapSayHello();
    }
}
