package com.fingard.xuesl.netty.share.heartbeat.serialize;

/**
 * 序列化接口
 * @author xuesl
 * @date 2019/9/19
 */
public interface Serializer {

    /**
     * 获取序列化类型
     * @return 序列化类型
     */
    byte getSerializerType();

    /**
     * 序列化
     * @param object 待序列化对象
     * @return 序列化后的字节数组
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     * @param bytes 待反序列化的字节数组
     * @param clazz 待反序列化的对象类型Class
     * @param <T> 待反序列化的对象类型泛型
     * @return 反序列化后的对象
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);

}
