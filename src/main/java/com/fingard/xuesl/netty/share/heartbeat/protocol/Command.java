package com.fingard.xuesl.netty.share.heartbeat.protocol;

/**
 * 指令类型
 * @author xuesl
 * @date 2019/9/19
 */
public interface Command {
    //心跳包请求
    byte HEARTBEAT_REQUEST = 1;

    ////心跳包响应
    byte HEARTBEAT_RESPONSE = 2;
}
