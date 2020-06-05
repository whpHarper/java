package com.whp.login;

import com.whp.protocol.request.LoginPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @Classname ClientHandler
 * @Describe TODO
 * @Date 2020/5/26
 * @Auth whp
 * @Version 1.0
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println(new Date(  )+ ":客户端开始登录");
        LoginPacket loginPacket=new LoginPacket();
        loginPacket.setUserId( UUID.randomUUID().toString() );
        loginPacket.setPassword( "pwd" );
        loginPacket.setUserName( "flash" );

//        ByteBuf buf= PacketCode
    }
}
