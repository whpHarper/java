package com.netty.protocol.response;

import com.netty.protocol.Packet;
import com.netty.protocol.command.Command;
import lombok.Data;

/**
 * @Classname LoginResponsePacket
 * @Describe TODO
 * @Date 2020/6/12
 * @Auth whp
 * @Version 1.0
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String reason;
    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
