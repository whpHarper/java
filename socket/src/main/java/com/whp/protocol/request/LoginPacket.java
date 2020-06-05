package com.whp.protocol.request;

import com.whp.protocol.Packet;
import com.whp.protocol.command.Command;
import lombok.Data;

/**
 * @Classname LoginPacket
 * @Describe TODO
 * @Date 2020/5/26
 * @Auth whp
 * @Version 1.0
 */
@Data
public class LoginPacket extends Packet {
    private String userName;
    private String password;
    private String userId;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
