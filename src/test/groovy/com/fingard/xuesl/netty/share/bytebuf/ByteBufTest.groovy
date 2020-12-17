package com.fingard.xuesl.netty.share.bytebuf

import io.netty.buffer.ByteBuf
import io.netty.buffer.PooledByteBufAllocator
import io.netty.buffer.UnpooledByteBufAllocator
import io.netty.util.ReferenceCountUtil
import spock.lang.Specification

import java.nio.charset.Charset

/**
 * @author xuesl* @date 2019/9/18
 */
class ByteBufTest extends Specification {

    void setup() {

    }

    void cleanup() {

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
        for (int i = 0; i < 100; i++) {
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
        1 == 1
    }

    def "test"() {
        given:
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer()

        when:
        byteBuf.writeBytes("0001".getBytes(Charset.forName("UTF-8")))
        byteBuf.writeInt(1)

        def byteCount = byteBuf.readableBytes()
        for (int i = 0; i < byteCount; i++) {
            println(byteBuf.readByte())
        }

        then:
        byteBuf.readableBytes() == 8
        byteBuf.toString(Charset.forName("UTF-8")) == "00011"
    }

}
