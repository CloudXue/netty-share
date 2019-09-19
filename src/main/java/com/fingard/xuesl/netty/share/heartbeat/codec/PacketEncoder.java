package com.fingard.xuesl.netty.share.heartbeat.codec;

import com.fingard.xuesl.netty.share.heartbeat.protocol.AbstractPacket;
import com.fingard.xuesl.netty.share.heartbeat.serialize.JSONSerializer;
import com.fingard.xuesl.netty.share.heartbeat.serialize.SerializerType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xuesl
 * @date 2019/9/19
 */
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket packet, ByteBuf byteBuf) throws Exception {

        JSONSerializer jsonSerializer = new JSONSerializer();

        byteBuf.writeInt(packet.getMagicNumber());
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializerType.JSON);
        byteBuf.writeByte(packet.getCommand());
        byte[] encode = jsonSerializer.serialize(packet);
        byteBuf.writeInt(encode.length);
        byteBuf.writeBytes(encode);
    }
}
