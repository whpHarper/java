package com.netty.serialize;

import com.netty.serialize.impl.JsonSerializer;

/***
 * @Author whp
 * @Description  序列化算法，将object与byte[] 进行相互转化
 * @Date  2020/6/12
 **/
public interface Serializer {
    Serializer DEFAULT=new JsonSerializer();
    byte getSerializeAlgrithmm();
    byte[] serialize(Object object);
    <T> T desrialize(Class<T> clazz,byte[] bytes);
}
