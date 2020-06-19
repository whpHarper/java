package com.netty.protocol;

import com.netty.protocol.request.LoginRequestPacket;
import com.netty.protocol.request.MessageRequestPacket;
import com.netty.protocol.response.LoginResponsePacket;
import com.netty.protocol.response.MessageResponsePacket;
import com.netty.serialize.Serializer;
import com.netty.serialize.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.netty.protocol.command.Command.*;

/**
 * @Classname PacketCodeC
 * @Describe 协议打包工具，支持打包和解包
 * @Date 2020/6/12
 * @Auth whp
 * @Version 1.0
 */
public class PacketCodeC {
    public static final int MAGIC_NUMBER=0x12345678;
    public static final  PacketCodeC INSTANCE=new PacketCodeC();

    private final Map<Byte,Class<? extends Packet>> packetMap;
    private final Map<Byte, Serializer> serializerMap;
    /***
     * @Author whp
     * @Description 封装命令和序列化参数到Map中，供给后期编码和解码调用
     * @Date  2020/6/12
     * @return 
     **/
    private PacketCodeC(){
        packetMap=new HashMap<>(  );
        packetMap.put( LOGIN_REQUEST, LoginRequestPacket.class );
        packetMap.put( LOGIN_RESPONSE, LoginResponsePacket.class );
        packetMap.put( MESSAGE_REQUEST, MessageRequestPacket.class );
        packetMap.put( MESSAGE_RESPONSE, MessageResponsePacket.class );

        serializerMap=new HashMap<>(  );
        Serializer jsonSerializer=new JsonSerializer();
        serializerMap.put( jsonSerializer.getSerializeAlgrithmm(),jsonSerializer );
    }

    /***
     * @Author whp
     * @Description  编码过程，将packet中数据进行序列化，封装byteBuf
     * @Date  2020/6/12
     * @Param [byteBuf, packet]
     * @return void
     **/
    public void encode(ByteBuf byteBuf,Packet packet){
        byte[] bytes=Serializer.DEFAULT.serialize( packet );
        byteBuf.writeInt( MAGIC_NUMBER );
        byteBuf.writeByte( packet.getVersion() );
        byteBuf.writeByte( Serializer.DEFAULT.getSerializeAlgrithmm() );
        byteBuf.writeByte( packet.getCommand() );
        byteBuf.writeByte( bytes.length );
        byteBuf.writeBytes( bytes );
    }
    /***
     * @Author whp
     * @Description  解码，将bytebuf中的数据重新放到应用层的packet中
     * @Date  2020/6/12
     * @Param [bytebuf]
     * @return com.netty.protocol.Packet
     **/
    public Packet decode(ByteBuf bytebuf){
        bytebuf.skipBytes( 4  );
        bytebuf.skipBytes( 1 );
        Serializer serializer= serializerMap.get(bytebuf.readByte());
        Class<? extends Packet> packet=packetMap.get( bytebuf.readByte() );
        int length=bytebuf.readInt();
        byte[] bytes=new byte[length];
        if(packet!=null && serializer!=null){
            return serializer.desrialize( packet,bytes );
        }
        return null;
    }
}
