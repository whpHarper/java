package com.whp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.Buffer;

/**
 * @Classname ByteBufTest
 * @Describe TODO
 * @Date 2020/5/25
 * @Auth whp
 * @Version 1.0
 */
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer= ByteBufAllocator.DEFAULT.buffer(9,100);

    }

    private static void print(String action,Buffer buffer){
        System.out.println("after=============="+action+"===============");
        System.out.println("capcity:"+buffer.capacity());
        System.out.println("maxCapcity"+buffer.capacity());

    }
}
