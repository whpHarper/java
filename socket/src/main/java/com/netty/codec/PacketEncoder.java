package com.netty.codec;

import com.netty.protocol.Packet;
import com.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Classname PacketEncoder
 * @Describe TODO
 * @Date 2020/6/18
 * @Auth whp
 * @Version 1.0
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encode( byteBuf,packet );
    }
}
