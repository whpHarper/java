package com.netty.client.handler;

import com.netty.protocol.request.LoginRequestPacket;
import com.netty.protocol.response.LoginResponsePacket;
import com.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Classname LoginResponseHandler
 * @Describe 登录Handler，应用系统向服务端发送登录信息
 * @Date 2020/6/18
 * @Auth whp
 * @Version 1.0
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket=new LoginRequestPacket();
        loginRequestPacket.setUserId( UUID.randomUUID().toString() );
        loginRequestPacket.setUsername( "flash" );
        loginRequestPacket.setPassword( "pwd" );

        ctx.channel().writeAndFlush( loginRequestPacket );
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.isSuccess()){
            System.out.println(new Date(  )+":登录成功" );
            LoginUtil.markAsLogin( ctx.channel() );
        }else{
            System.out.println(new Date(  )+":客户端登录失败，原因："+loginResponsePacket.getReason());
        }
    }
}
