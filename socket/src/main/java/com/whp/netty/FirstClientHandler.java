package com.whp.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Classname FirstClientHandler
 * @Describe TODO
 * @Date 2020/5/25
 * @Auth whp
 * @Version 1.0
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date()+":客户端写出数据");
        ByteBuf buffer=getByteBuf( ctx );
        ctx.channel().writeAndFlush( buffer );
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf buffer=(ByteBuf)msg;
        System.out.println(new Date(  )+":客户端读取数据->"+buffer.toString(Charset.forName( "utf-8" )));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf buffer=ctx.alloc().buffer();
        byte[] bytes="你好，闪电侠".getBytes( Charset.forName("utf-8"));
        buffer.writeBytes( bytes );
        return buffer;
    }
}
