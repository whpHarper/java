package com.whp.netty;

import com.whp.login.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Classname NettyClient
 * @Describe netty 客户端代码
 * @Date 2020/5/25
 * @Auth whp
 * @Version 1.0
 */
public class NettyClient {
    private static final int MAX_ENTRY = 5;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group( group ) //指定线程模型
                .option( ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000 ) //连接超时时间
                .channel( NioSocketChannel.class )  //2.指定IO模型
                .handler( new ChannelInitializer<Channel>() {  //3. IO处理逻辑
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
//                        channel.pipeline().addLast( new FirstClientHandler() );
                        channel.pipeline().addLast( new ClientHandler() );
                    }
                } );
        Channel channel = bootstrap.connect( "127.0.0.1", 1002 ).addListener( future -> {
            if (future.isSuccess()) {
                System.out.println( "连接成功！" );
            } else {
                System.out.println( "连接失败！" );
            }
        } ).channel();

//        connect( bootstrap,"127.0.0.1",1000,MAX_ENTRY );
        /*Channel channel=bootstrap.connect().channel( );
        while (true){
            channel.writeAndFlush( new Date(  )+":hello world!" );
            Thread.sleep( 2000 );
        }*/
    }

    /**
     * @return void
     * @Author whp
     * @Description 尝试重连，第一次失败后，以1,2,4,6，次重连
     * @Date 2020/5/25
     * @Param [bootstrap, host, port, retry]
     **/
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect( host, port ).addListener( future -> {
            if (future.isSuccess()) {
                System.out.println( "连接成功" );
            } else if (retry == 0) {
                System.err.println( "重试次数已经用完" );
            } else {
                System.err.println( "连接失败，开始重连" );
                int order = (MAX_ENTRY - retry) + 1; //第几次重连
                int delay = 1 << order; //每次以2的n次幂秒进行重连
                System.err.println( new Date() + ":连接失败，第" + order + "次重连..." );
                bootstrap.config().group().schedule( () -> connect( bootstrap, host, port + 1, retry - 1 ), delay, TimeUnit.SECONDS );
            }
        } );
    }
}
