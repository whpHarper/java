package com.netty.client;

import com.netty.client.handler.LoginResponseHandler;
import com.netty.client.handler.MessageResponseHandler;
import com.netty.codec.PacketDecoder;
import com.netty.codec.PacketEncoder;
import com.netty.codec.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Classname NettyClient
 * @Describe Netty客户端，通过发送
 * @Date 2020/6/12
 * @Auth whp
 * @Version 1.0
 */
public class NettyClient {
    private static int MAX_RETRY=5;
    private static final String HOST="127.0.0.1";
    private static final int PORT=8000;

    public static void main(String[] args) {
        NioEventLoopGroup workerLoopGroup=new NioEventLoopGroup(  );
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group( workerLoopGroup )
                .channel( NioServerSocketChannel.class )
                .option( ChannelOption.SO_BACKLOG,1024)
                .option( ChannelOption.SO_KEEPALIVE,true )
                .option( ChannelOption.TCP_NODELAY,true )
                .handler( new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        ch.pipeline().addLast( new Spliter() );
                        ch.pipeline().addLast( new PacketDecoder() );
                        ch.pipeline().addLast( new LoginResponseHandler() );
                        ch.pipeline().addLast( new MessageResponseHandler() );
                        ch.pipeline().addLast( new PacketEncoder() );
                    }
                } );
        connect( bootstrap,HOST,PORT,MAX_RETRY );
    }

    private static void connect(Bootstrap bootstrap,String host,int port,int retry){
        bootstrap.connect(host,port).addListener( future -> {
            if(future.isSuccess()){
                System.out.println(new Date(  )+":连接成功，启动控制台线程……" );
                Channel channel=((ChannelFuture)future).channel();
                startConsoleThread( channel );
            }else if(retry==0){
                System.out.println("重试次数已用完，放弃连接！");
            }else{
                int order=(MAX_RETRY-retry)+1;
                int delay=1<<order;
                System.out.println(new Date(  )+":连接失败，第"+order+"次重连");
                bootstrap.config().group().schedule( ()->connect( bootstrap,host ,port,retry-1),delay, TimeUnit.SECONDS );
            }
        } );
    }

    private static void startConsoleThread(Channel channel){
        new Thread( ()->{
            while(!Thread.interrupted()){
                System.out.println("输入信息发送至服务端");
                Scanner sc=new Scanner( System.in );
                String line=sc.nextLine();
                channel.writeAndFlush( line );
            }
        } ).start();
    }
}
