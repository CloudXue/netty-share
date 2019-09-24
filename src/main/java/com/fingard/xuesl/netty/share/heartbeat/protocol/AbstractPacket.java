package com.fingard.xuesl.netty.share.heartbeat.protocol;

import lombok.Data;

import java.io.Serializable;


/**
 * 协议基类，以下为协议内容及长度
 * +---------------------------------------------------------------+
 * | magic number| version | serializer| command | length | content|
 * +---------------------------------------------------------------+
 * |      4      |    1    |      1    |    1    |    4   | length |
 *
 * @author xuesl
 * @date 2019/9/19
 */
@Data
public abstract class AbstractPacket implements Serializable {
    private final int magicNumber = 0x34547621;
    private static final long serialVersionUID = -8649049591657378902L;


    /**
     * 版本号
     */
    private int version;

    public abstract byte getCommand();
}
