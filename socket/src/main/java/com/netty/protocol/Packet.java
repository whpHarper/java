package com.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Classname Packet
 * @Describe 定义转发协议包
 * @Date 2020/6/12
 * @Auth whp
 * @Version 1.0
 */
@Data
public abstract class Packet {

    @JSONField(deserialize = false,serialize = false)
    private byte version=1;  //序列化过程中不序列化版本号

    @JSONField(serialize = false)
    public abstract byte getCommand(); //不序列化命令
}
