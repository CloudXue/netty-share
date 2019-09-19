package com.fingard.xuesl.netty.share.heartbeat.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author xuesl
 * @date 2019/9/19
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerType() {
        return SerializerType.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
