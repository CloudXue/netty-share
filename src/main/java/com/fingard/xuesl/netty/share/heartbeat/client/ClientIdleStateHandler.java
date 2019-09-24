package com.fingard.xuesl.netty.share.heartbeat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 客户端超时处理
 * @author xuesl
 * @date 2019/9/19
 */
@Slf4j
public class ClientIdleStateHandler extends IdleStateHandler {
    private static final int READER_IDLE_TIME = 15;

    public ClientIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent event) {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        try {
            log.info("尝试重新连接...");
            HeartBeatClient.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ctx.channel().close();
    }
}
