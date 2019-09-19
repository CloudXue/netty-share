package com.fingard.xuesl.netty.share.heartbeat.protocol;

import lombok.Data;

import java.io.Serializable;

/**
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
