package com.fingard.xuesl.netty.share.heartbeat.codec;

import com.fingard.xuesl.netty.share.heartbeat.protocol.Command;
import com.fingard.xuesl.netty.share.heartbeat.protocol.request.HeartBeatRequest;
import com.fingard.xuesl.netty.share.heartbeat.protocol.response.HeartBeatResponse;
import com.fingard.xuesl.netty.share.heartbeat.serialize.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuesl
 * @date 2019/9/19
 */
public class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {

    private Map<Byte, Class<?>> commandClasses = new HashMap<>(16);

    public PacketDecoder() {
        super();
        commandClasses.put(Command.HEARTBEAT_REQUEST, HeartBeatRequest.class);
        commandClasses.put(Command.HEARTBEAT_RESPONSE, HeartBeatResponse.class);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        JSONSerializer jsonSerializer = new JSONSerializer();
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);
        byte serializerType = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
//        System.out.println(length);
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Object obj = jsonSerializer.deserialize(bytes, commandClasses.get(command));
        list.add(obj);
    }


}
