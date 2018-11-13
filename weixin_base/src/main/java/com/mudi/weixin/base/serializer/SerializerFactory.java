package com.mudi.weixin.base.serializer;

import com.google.common.collect.Maps;

import javax.activation.UnsupportedDataTypeException;
import java.util.Map;

public class SerializerFactory {
    private static Map<Byte,Serializer> mapping = Maps.newHashMap();
    static {
        mapping.put(Serializer.JSON_SERIALIZER,new JsonSerializer());
    }
    public static Serializer create(byte type) throws UnsupportedDataTypeException {
        Serializer serializer = mapping.get(type);
        if(serializer==null){
            throw new UnsupportedDataTypeException("不支持该序列化方式["+type+"]");
        }
        return serializer;
    }
}
