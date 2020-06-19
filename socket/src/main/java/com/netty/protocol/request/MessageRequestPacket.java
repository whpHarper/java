package com.netty.protocol.request;

import com.netty.protocol.Packet;
import lombok.Data;
import static com.netty.protocol.command.Command.MESSAGE_REQUEST;
/**
 * @Classname MessageRequestPacket
 * @Describe TODO
 * @Date 2020/6/12
 * @Auth whp
 * @Version 1.0
 */
@Data
public class MessageRequestPacket extends Packet {
    private String message;
    @Override
    public byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
