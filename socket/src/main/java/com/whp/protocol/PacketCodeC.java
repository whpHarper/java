package com.whp.protocol;

import com.whp.protocol.command.Command;
import com.whp.protocol.request.LoginPacket;
import com.whp.serialize.impl.JsonSerializer;
import com.whp.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname PacketCodeC
 * @Describe 封装二进制
 * @Date 2020/5/26
 * @Auth whp
 * @Version 1.0
 */
public class PacketCodeC {
    public static final int MAGIC_NUMBER=0x12345678;
    public static final PacketCodeC INSTANCE=new PacketCodeC();

    private static Map<Byte,Class<? extends Packet>> packetTypeMap;
    private static Map<Byte, Serializer> serializerMap;
    static {
        packetTypeMap=new HashMap<>(  );
        packetTypeMap.put( Command.LOGIN_REQUEST, LoginPacket.class );

        serializerMap=new HashMap<>(  );
        Serializer serializer=new JsonSerializer();
        serializerMap.put( serializer.getSerialAlgorithm(),serializer );
    }
    public void  encode(ByteBuf buf,Packet packet){
        /*ByteBuf buf= ByteBufAllocator.DEFAULT.ioBuffer();*/
        byte[] bytes=Serializer.DEFAULT.serialize( packet );
        buf.writeInt( MAGIC_NUMBER );
        buf.writeByte( packet.getVersion() );
        buf.writeByte( Serializer.DEFAULT.getSerialAlgorithm() );
        buf.writeByte( packet.getCommand() );
        buf.writeInt( bytes.length );
        buf.writeBytes( bytes );
    }

    public Packet decode(ByteBuf byteBuf){
        byteBuf.skipBytes( 4 );//1. 跳过模数
        byteBuf.skipBytes( 1 ); //2. 跳过版本号
        byte serializeAlgorithm=byteBuf.readByte(); //3. 获取算法
        byte command=byteBuf.readByte();  //4. 获取指令

        int length=byteBuf.readInt(); //5. 获取长度
        byte[] bytes=new byte[length];   //6. 获取数据
        byteBuf.readBytes( bytes );
        Class<? extends Packet> requestType=getRequestType(command);
        Serializer serializer=getSerializer( serializeAlgorithm );
        if(requestType!=null && serializer!=null){
            return serializer.deSerialize( requestType,bytes );
        }
        return null;
    }

    public Class<? extends Packet> getRequestType(byte command){
        return packetTypeMap.get( command );
    }

    public Serializer getSerializer(byte algorizhm){
        return serializerMap.get( algorizhm );
    }
}
