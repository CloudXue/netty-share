package com.fingard.xuesl.netty.share.heartbeat.serialize;

/**
 * @author xuesl
 * @date 2019/9/19
 */
public interface Serializer {

    byte getSerializerType();

    byte[] serialize(Object object);

    <T> T deserialize(byte[] bytes, Class<T> clazz);

}
