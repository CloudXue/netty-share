package com.fingard.xuesl.netty.share.bigflow;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author xuesl
 * @date 2018/12/13
 */
public class Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("进入Decoder");
        in.skipBytes(4);
        System.out.println(in.toString(Charset.forName("UTF-8")));
        in.skipBytes(in.readableBytes());
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(-5<<1);
    }
}
