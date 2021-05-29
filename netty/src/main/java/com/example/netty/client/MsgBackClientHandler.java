package com.example.netty.client;

import com.example.netty.enjoy.User;
import com.example.netty.enjoy.UserContact;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *    描述：TODO
 * </pre>
 *
 * @author HDY
 * @version v1.0
 * @date 2020/9/9 22:01
 */
public class MsgBackClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private Integer sendNumber;

    public MsgBackClientHandler(Integer sendNumber) {
        this.sendNumber = sendNumber;
    }

    private User[] makeUser(){
        User[] users = new User[sendNumber];
        for(int i=0; i<sendNumber; i++){
            User user = new User();
            user.setId(String.valueOf(i));
            user.setAge(i+2);
            user.setName("万小二---->"+i);
            user.setUserContact(new UserContact("4534@qq.com", "13199537583"));
            users[i] = user;
        }
        return users;
    }

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("客户端接收["+byteBuf.toString(CharsetUtil.UTF_8)+"] 和数量是 :"+ count.incrementAndGet() );
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        User[] users = makeUser();
        for(User u: users){
            System.out.println("发送中"+u);
            ctx.write(u);
        }
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
