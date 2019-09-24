package com.fingard.xuesl.netty.share.heartbeat.server;

import com.fingard.xuesl.netty.share.heartbeat.protocol.request.HeartBeatRequest;
import com.fingard.xuesl.netty.share.heartbeat.protocol.response.HeartBeatResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 *服务端心跳处理器
 * @author xuesl
 * @date 2019/9/19
 */
@Slf4j
public class HeartBeatServerHandler extends SimpleChannelInboundHandler<HeartBeatRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequest msg) throws Exception {
        log.info("服务端接收到PING");
        ctx.writeAndFlush(new HeartBeatResponse());
    }
}
