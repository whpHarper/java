package com.netty.protocol.response;

import com.netty.protocol.Packet;
import com.netty.protocol.command.Command;
import lombok.Data;

/**
 * @Classname MessageResponsePacket
 * @Describe TODO
 * @Date 2020/6/12
 * @Auth whp
 * @Version 1.0
 */
@Data
public class MessageResponsePacket extends Packet {
    private String message;
    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
