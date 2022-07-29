package com.java.util;

import java.util.*;

public class ClassUtil {

    public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }

        Set<Class<?>> interfacesFound = new HashSet<>();
        getAllInterfaces(cls, interfacesFound);

        return new ArrayList<>(interfacesFound);
    }

    private static void getAllInterfaces(Class<?> cls, Set<Class<?>> interfacesFound) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();

            for (Class<?> i : interfaces) {
                if (interfacesFound.add(i)) {
                    getAllInterfaces(i, interfacesFound);
                }
            }

            cls = cls.getSuperclass();
        }
    }
}
