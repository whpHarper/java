package com.netty.client.handler;

import com.netty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Classname MessageResponseHandler
 * @Describe TODO
 * @Date 2020/6/18
 * @Auth whp
 * @Version 1.0
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket responsePacket) throws Exception {
        System.out.println(new Date(  )+":收到服务端消息："+ responsePacket.getMessage());
    }
}
