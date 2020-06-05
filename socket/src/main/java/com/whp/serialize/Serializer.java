package com.whp.serialize;

import com.whp.serialize.impl.JsonSerializer;

/**
 * @Classname Serializer
 * @Describe 序列化，提供算法，序列化，解序列化
 * @Date 2020/5/26
 * @Auth whp
 * @Version 1.0
 */
public interface Serializer {
    byte JSON_SERIALIZER=1;
    Serializer DEFAULT=new JsonSerializer();
    Byte getSerialAlgorithm();
    byte[] serialize(Object object);
    <T> T deSerialize(Class<T> tClass,byte[] bytes);
}
