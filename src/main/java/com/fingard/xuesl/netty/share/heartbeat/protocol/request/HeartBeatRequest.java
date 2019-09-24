package com.fingard.xuesl.netty.share.heartbeat.protocol.request;

import com.fingard.xuesl.netty.share.heartbeat.protocol.AbstractPacket;
import com.fingard.xuesl.netty.share.heartbeat.protocol.Command;

/**
 * 心跳请求报文
 * @author xuesl
 * @date 2019/9/19
 */
public class HeartBeatRequest extends AbstractPacket {
    @Override
    public byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
