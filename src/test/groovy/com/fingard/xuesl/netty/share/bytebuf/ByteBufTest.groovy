package com.fingard.xuesl.netty.share.bytebuf

import io.netty.buffer.ByteBuf
import io.netty.buffer.PooledByteBufAllocator
import io.netty.buffer.UnpooledByteBufAllocator
import io.netty.util.ReferenceCountUtil
import spock.lang.Specification

/**
 * @author xuesl* @date 2019/9/18
 */
class ByteBufTest extends Specification {

    void setup(){

    }

    void cleanup(){

    }

    def "ByteBuf interface"() {
        given:
        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer()

        when:
        byteBuf.writeInt(1)

        then:
        byteBuf.readableBytes() == 4
        println("readIndex = " + byteBuf.readerIndex())
        println("writeIndex =  " + byteBuf.writerIndex())
        println("capacity = " + byteBuf.capacity())
        println("maxCapacity = " + byteBuf.maxCapacity())
        println("buffer type is direct:" + byteBuf.isDirect())
    }

    def "ByteBuf auto expansion"() {
        given:
        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer()

        when:
        for (int i = 0; i< 100; i++) {
            byteBuf.writeInt(1)
        }

        then:
        byteBuf.readableBytes() == 400
        println("readIndex = " + byteBuf.readerIndex())
        println("writeIndex =  " + byteBuf.writerIndex())
        println("capacity = " + byteBuf.capacity())
        println("maxCapacity = " + byteBuf.maxCapacity())
    }

    def "pooled direct bytebuf"() {
        given:
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer()

        when:
        ReferenceCountUtil.release(byteBuf)

        then:
        1==1
    }
}
