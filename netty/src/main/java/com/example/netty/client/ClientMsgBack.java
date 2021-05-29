package com.example.netty.client;

import com.example.netty.server.ServerMsgBack;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.net.InetSocketAddress;

/**
 * <pre>
 *    描述：TODO
 * </pre>
 *
 * @author HDY
 * @version v1.0
 * @date 2020/9/9 22:22
 */
public class ClientMsgBack {

    private String host;

    public ClientMsgBack(String host) {
        this.host = host;
    }

    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap( );
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, ServerMsgBack.PORT))
                    .handler(new ChannerInitializerImp( ));
            ChannelFuture sync = bootstrap.connect( ).sync( );
            System.out.println("已连接到服务器");
            sync.channel( ).closeFuture( ).sync( );
        } catch (InterruptedException e) {
            group.shutdownGracefully().sync();
        }

    }

    private static class ChannerInitializerImp extends ChannelInitializer<Channel>{

        @Override
        protected void initChannel(Channel channel) throws Exception {
            //设置报文长度避免粘包
            channel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));

            //添加编码器
            channel.pipeline().addLast(new MsgPackEncoder());
            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            channel.pipeline().addLast(new MsgBackClientHandler(1));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i<1; i++){
            Thread thread = new Thread(new Runnable( ) {
                @Override
                public void run() {
                    try {
                        new ClientMsgBack("127.0.0.1").start( );
                    } catch (InterruptedException e) {
                        e.printStackTrace( );
                    }
                }
            });
            thread.start();
        }

    }
}
