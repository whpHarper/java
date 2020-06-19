package com.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Classname NettyServer
 * @Describe TODO
 * @Date 2020/6/18
 * @Auth whp
 * @Version 1.0
 */
public class NettyServer {
    private static final int port=8000;

    public static void main(String[] args){
        NioEventLoopGroup boosGroup=new NioEventLoopGroup(  );
        NioEventLoopGroup workerGroup=new NioEventLoopGroup(  );

        final ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap
                .group( boosGroup,workerGroup )
                .channel( NioServerSocketChannel.class );

    }
}
