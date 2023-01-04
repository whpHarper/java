package com.java.leecode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FistUniqueChar
 * @Description 获取字符串第一个唯一字符
 * @Author whp
 * @Date 2023/1/3
 * @Version 1.0
 **/
public class FistUniqueChar {
    public static int getFirstUniqueChar(String s){
        Map<Character,Integer> map=new HashMap<>();
        int n=s.length();
        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(int i=0;i<n;i++){
            if(map.get(s.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String target="erweerqderimq";
        int firstUniqueChar = FistUniqueChar.getFirstUniqueChar(target);
        System.out.println(target.charAt(firstUniqueChar));
    }
}
