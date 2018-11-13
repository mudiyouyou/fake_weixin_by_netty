package com.mudi.weixin.base.handler;

import com.mudi.weixin.base.packet.Packet;
import com.mudi.weixin.base.packet.PacketCodecUtil;
import com.mudi.weixin.base.serializer.Serializer;
import com.mudi.weixin.base.serializer.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PacketCodec extends ByteToMessageCodec<Packet> {
    public PacketCodec() {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        log.info("报文编码");
        PacketCodecUtil.encode(msg, out, SerializerFactory.create(Serializer.JSON_SERIALIZER));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("报文解码");
        out.add(PacketCodecUtil.decode(in));
    }
}
