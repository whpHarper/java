package com.netty.codec;

import com.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Classname PacketDecoder
 * @Describe TODO
 * @Date 2020/6/18
 * @Auth whp
 * @Version 1.0
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add( PacketCodeC.INSTANCE.decode( byteBuf ) );
    }
}
