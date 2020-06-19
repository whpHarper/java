package com.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.netty.serialize.Serializer;
import com.netty.serialize.SerializerAlgorithm;

/**
 * @Classname JsonSerivalizer
 * @Describe TODO
 * @Date 2020/6/12
 * @Auth whp
 * @Version 1.0
 */
public class JsonSerializer implements Serializer {

    @Override
    public byte getSerializeAlgrithmm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T desrialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(  bytes ,clazz );
    }
}
