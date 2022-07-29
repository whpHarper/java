package com.util.whp.reflection;

import java.util.*;

public class ClassUtil {

    public static List<Class<?>> getAllInterfaces(Class<?> cls){
        if(cls==null){
            return Collections.emptyList();
        }
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        return new ArrayList<Class<?>>(classSet);
    }

    public void getAllInterfaces(Class<?> cls,Set<Class<?>> cLassSet){
        while(cls!=null){
            Class<?>[] interfaces = cls.getInterfaces();
            for(Class<?> intf:interfaces){
                if(cLassSet.add(intf)){
                    getAllInterfaces(intf,cLassSet);
                }
            }
            cls=cls.getSuperclass();
        }
    }
}
