package com.mudi.weixin.base.serializer;

import com.alibaba.fastjson.JSON;

public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return JSON_SERIALIZER;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) {
        return JSON.parseObject(data,clazz);
    }
}
