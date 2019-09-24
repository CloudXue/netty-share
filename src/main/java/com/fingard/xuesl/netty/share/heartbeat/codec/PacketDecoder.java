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
 * 解码器
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
        // TODO 进入该解码器的ByteBuf是已经经过拆包的数据，一个ByteBuf就是一个包，请对该报文进行解码，解码后的对象放入list中即可
        // TODO 协议可以参考AbstractPacket中的注释，以及PacketEncoder中的编码代码






        //list.add(obj);
    }


}
