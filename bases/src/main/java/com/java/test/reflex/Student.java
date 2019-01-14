package com.java.test.reflex;

/**
 * @author whp 18-7-18
 */
public class Student {
    Student(String str){
        System.out.println("构造方法：s="+str);
    }

    public Student(){
        System.out.println("无参数共有构造方法");
    }

    public Student(char name){
        System.out.println("一个参数name="+name);
    }
}
