package com.netty.protocol.request;

import com.netty.protocol.Packet;
import com.netty.protocol.command.Command;
import lombok.Data;

/**
 * @Classname LoginRequestPacket
 * @Describe TODO
 * @Date 2020/6/12
 * @Auth whp
 * @Version 1.0
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String username;
    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
