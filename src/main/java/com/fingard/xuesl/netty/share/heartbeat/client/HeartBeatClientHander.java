package com.fingard.xuesl.netty.share.heartbeat.client;

import com.fingard.xuesl.netty.share.heartbeat.protocol.request.HeartBeatRequest;
import com.fingard.xuesl.netty.share.heartbeat.protocol.response.HeartBeatResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author xuesl
 * @date 2019/9/19
 */
@Slf4j
public class HeartBeatClientHander extends SimpleChannelInboundHandler<HeartBeatResponse> {
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.executor().scheduleAtFixedRate(() -> {
            log.info("发送心跳包");
            ctx.writeAndFlush(new HeartBeatRequest());
        }, HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);

        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponse msg) throws Exception {
        log.info("收到返回的PONG");
    }
}
