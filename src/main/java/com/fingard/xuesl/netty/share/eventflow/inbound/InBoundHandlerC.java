package com.fingard.xuesl.netty.share.eventflow.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class InBoundHandlerC extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandlerC: " + msg);

        //TODO 演示一下ctx和channel调用读写事件的区别
//        ctx.writeAndFlush(msg);
        ctx.channel().writeAndFlush(msg);
    }
}
