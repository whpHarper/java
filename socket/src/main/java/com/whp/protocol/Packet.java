package com.whp.protocol;

import lombok.Data;

/**
 * @Classname packet
 * @Describe 定义数据包
 * @Date 2020/5/26
 * @Auth whp
 * @Version 1.0
 */
@Data
public abstract class Packet {
    private Byte version=1;
    public abstract Byte getCommand();
}
