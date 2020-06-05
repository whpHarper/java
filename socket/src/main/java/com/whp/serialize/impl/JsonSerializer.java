package com.whp.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.whp.serialize.Serializer;
import com.whp.serialize.SerializerAlogrithm;

/**
 * @Classname JsonSerializer
 * @Describe json序列化算法
 * @Date 2020/5/26
 * @Auth whp
 * @Version 1.0
 */
public class JsonSerializer implements Serializer {

    @Override
    public Byte getSerialAlgorithm() {
        return SerializerAlogrithm.json;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes( object );
    }

    @Override
    public <T> T deSerialize(Class<T> tClass, byte[] bytes) {
        return JSON.parseObject( bytes,tClass );
    }
}
