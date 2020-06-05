package com.whp.netty;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Classname NettyServer
 * @Describe Nettry服务端，对接三个，线程模型，IO模型，连接读写处理逻辑
 * @Date 2020/5/25
 * @Auth whp
 * @Version 1.0
 */
public class NettyServer {
    public static void main(String[] args) {
        //服务端启动
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        //bossGroup两个线程组
        NioEventLoopGroup boss=new NioEventLoopGroup(  );
        NioEventLoopGroup worker=new NioEventLoopGroup(  );

        serverBootstrap.group( boss,worker )//指定线程模型
                .channel( NioServerSocketChannel.class )  //定义IO连接方式，另外一个为BIO，OioServerSocketChannel
                //.attr( AttributeKey.newInstance( "serverName" ),"nettyServer" )  //为channel定义属性
                //.childAttr( AttributeKey.newInstance( "clientKey" ),"clientValue" )  //为每条连接定义属性，通过channl.attr()取出
                //.childOption( ChannelOption.SO_KEEPALIVE,true )  //定义TCP底层连接属性，是否开启TCP底层心跳连接机制
               // .childOption( ChannelOption.TCP_NODELAY,true )  //是否开启Nagle算法，需要减少网络交互就开启
               // .option( ChannelOption.SO_BACKLOG,1024 )  //服务端channel属性，用于存放请求队列的最大长度，如果连接建立频繁，服务器处理创建新的连接较慢，则适当调大该参数
                .childHandler( new ChannelInitializer<NioSocketChannel>() {  //定义每条连接的读写处理逻辑
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast( new FirstServrHandler() );
/*                        ch.pipeline().addLast( new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
                                System.out.println( msg );
                            }
                        } );*/
                    }
                } );
        bind( serverBootstrap,1002 );
    }
    /***
     * @Author whp
     * @Description  应用回调函数进行动态接口绑定
     * @Date  2020/5/25
     * @Param [serverBootstrap, port]
     * @return void
     **/
    private static void bind(final ServerBootstrap serverBootstrap,final int port){
        serverBootstrap.bind(port).addListener( new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("端口["+port+"]绑定成功");
                }else{
                    System.err.println( "端口["+port+"]绑定失败" );
                    bind( serverBootstrap,port+1 );
                }
            }
        } );
    }
}
