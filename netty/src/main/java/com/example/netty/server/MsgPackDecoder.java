package com.example.netty.server;

import com.example.netty.enjoy.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * <pre>
 *    描述：TODO
 * </pre>
 *
 * @author HDY
 * @version v1.0
 * @date 2020/9/9 22:48
 */
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("解码器执行次数："+Thread.currentThread().getName() );
        int length = byteBuf.readableBytes();
        byte[] bytes = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, length);
        MessagePack messagePack = new MessagePack();
        list.add(messagePack.read(bytes, User.class));
        User user = new User();
        user.setId("3");
        user.setName("hhhjhjj");
        user.setAge(4);
        list.add(user);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("解码器"+this );
        super.channelRegistered(ctx);
    }
}
