package com.example.netty.server;

import com.example.netty.client.ClientMsgBack;
import com.example.netty.client.MsgBackClientHandler;
import com.example.netty.client.MsgPackEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *    描述：TODO
 * </pre>
 *
 * @author HDY
 * @version v1.0
 * @date 2020/9/13 22:57
 */
public class ServerMsgBack {

    public final static int PORT = 9995;


    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(PORT))
                    .childHandler(new ServerMsgBack.ChannerInitializerImp( ));
            ChannelFuture sync = bootstrap.bind( ).sync( );
            System.out.println("服务器已启动，等待客户端连接");
            sync.channel( ).closeFuture( ).sync( );
        } catch (InterruptedException e) {
            group.shutdownGracefully().sync();
        }

    }

    private static class ChannerInitializerImp extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel channel) throws Exception {

            System.out.println("初始化通道" );
            channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,
                    0,2,0,2));
            channel.pipeline().addLast(new MsgPackDecoder());
            channel.pipeline().addLast(new MsgBackServerHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ServerMsgBack().start();
    }

}
