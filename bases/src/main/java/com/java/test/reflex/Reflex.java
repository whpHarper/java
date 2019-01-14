package com.java.test.reflex;

import java.lang.reflect.Constructor;

/**
 * @author whp 18-7-18
 */
public class Reflex {
    public static void main(String[] args){
        Student stu=new Student();
        Class clazz=stu.getClass();
        System.out.println(clazz.getName());

        try {
            Class stuClass=Class.forName("com.java.test.reflex.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获取所有构造函数
        Constructor[] conArray=clazz.getConstructors();
        for (Constructor c:conArray) {
            System.out.println(c);
        }


    }
}
