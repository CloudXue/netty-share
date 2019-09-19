package com.fingard.xuesl.netty.share.heartbeat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author xuesl
 * @date 2019/9/19
 */
public class ServerIdleStateHandler extends IdleStateHandler {
    private static final int READER_IDLE_TIME = 15;

    public ServerIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent event) {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");

        ctx.channel().close();
    }
}
