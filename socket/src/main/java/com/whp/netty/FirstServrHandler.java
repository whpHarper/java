package com.whp.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Classname FirstServerHandler
 * @Describe TODO
 * @Date 2020/5/25
 * @Auth whp
 * @Version 1.0
 */
public class FirstServrHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf byteBuf=(ByteBuf)msg;
        System.out.println(new Date(  )+":服务端读取数据->"+byteBuf.toString( Charset.forName("utf-8")) );
        System.out.println( new Date(  )+":服务端写出数据" );
        ByteBuf out =getByteBuf(ctx);
        ctx.channel().writeAndFlush( out );
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        byte[] bytes="你好，欢迎关注我的微信公众号，《闪电侠的博客》！".getBytes( Charset.forName("utf-8"));
        ByteBuf buffer=ctx.alloc().buffer();
        buffer.writeBytes( bytes );
        return buffer;
    }
}
