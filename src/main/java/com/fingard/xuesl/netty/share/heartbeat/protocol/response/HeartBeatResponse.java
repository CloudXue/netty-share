package com.fingard.xuesl.netty.share.heartbeat.protocol.response;

import com.fingard.xuesl.netty.share.heartbeat.protocol.AbstractPacket;
import com.fingard.xuesl.netty.share.heartbeat.protocol.Command;

/**
 * 心跳响应报文
 * @author xuesl
 * @date 2019/9/19
 */
public class HeartBeatResponse extends AbstractPacket {
    @Override
    public byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
