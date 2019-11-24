package com.mudi.weixin.base.handler;

import com.google.common.collect.Maps;
import com.mudi.weixin.base.cipher.CmdCipher;
import com.mudi.weixin.base.cmd.Cmd;
import com.mudi.weixin.base.cmdMapping.CmdMapper;
import com.mudi.weixin.base.serializer.Serializer;
import com.mudi.weixin.base.serializer.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import javax.activation.UnsupportedDataTypeException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class CmdHandler extends ByteToMessageCodec<Cmd> {
    private static final int MAGIC_NUM = 0x100782;
    private  Map<Byte, Class> mapping = Maps.newHashMap();
    private  CmdCipher cipher = new CmdCipher();
    private  CmdMapper cmdMapper = new CmdMapper();

    public CmdHandler() throws IOException {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Cmd msg, ByteBuf out) throws Exception {
        log.info("报文编码");
        doEncode(msg, out, SerializerFactory.create(Serializer.JSON_SERIALIZER));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("报文解码");
        out.add(doDecode(in));
    }

    public  void doEncode(Cmd packet, ByteBuf byteBuf, Serializer serializer) throws Exception {
        byteBuf.writeInt(MAGIC_NUM);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byte[] data = serializer.serialize(packet);
        data = cipher.encrypt(data);
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
    }

    public  Cmd doDecode(ByteBuf data) throws Exception {
        // 魔法数
        data.skipBytes(4);
        // 版本
        data.skipBytes(1);
        // 序列类型
        byte serializerType = data.readByte();
        // 命令
        byte command = data.readByte();
        // 长度
        int length = data.readInt();
        Serializer serializer = SerializerFactory.create(serializerType);
        byte[] content = new byte[length];
        // 读取内容
        data.readBytes(content);
        // 解密
        content = cipher.decrypt(content);
        Class<? extends Cmd> requestType = getRequestTyep(command);
        return serializer.deserialize(requestType, content);
    }

    private Class<? extends Cmd> getRequestTyep(byte command) throws UnsupportedDataTypeException {
        Class clazz = cmdMapper.get(command);
        if (clazz == null) {
            throw new UnsupportedDataTypeException("不支持该命令[" + command + "]");
        }
        return clazz;
    }
}
