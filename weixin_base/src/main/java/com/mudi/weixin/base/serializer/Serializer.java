package com.mudi.weixin.base.serializer;

public interface Serializer {
    byte JSON_SERIALIZER = 1;
    byte getSerializerAlgorithm();
    byte[] serialize(Object obj);
    <T> T deserialize(Class<T> clazz,byte[] data);
}
