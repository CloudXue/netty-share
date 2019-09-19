package com.fingard.xuesl.netty.share.heartbeat.protocol;

/**
 * @author xuesl
 * @date 2019/9/19
 */
public interface Command {
    byte HEARTBEAT_REQUEST = 1;

    byte HEARTBEAT_RESPONSE = 2;
}
