package com.example.netty.server;

import com.example.netty.enjoy.User;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *    描述：TODO
 * </pre>
 *
 * @author HDY
 * @version v1.0
 * @date 2020/9/9 22:27
 */
@ChannelHandler.Sharable
public class MsgBackServerHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger count = new AtomicInteger(0);

    private Integer integer = new Integer(0);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("当前通道地址为"+this );
        super.channelRegistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        User user = (User)msg;
        System.out.println("服务器接收到[" +user+"] 并且数量是： " + integer++);
        System.out.println("当前线程为："+Thread.currentThread().getName() +"当前处理器实例为"+ this);
        //服务器应当
        String result = "我处理用户:"+ user.getName() + System.getProperty("line.separator");
        ctx.writeAndFlush(Unpooled.copiedBuffer(result.getBytes()));
        CompositeByteBuf byteBufs = Unpooled.compositeBuffer( );
        ByteBuf buffer = Unpooled.buffer(8);
        ByteBuf byteBuf = Unpooled.directBuffer(16);
        byteBufs.addComponents(buffer, byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
