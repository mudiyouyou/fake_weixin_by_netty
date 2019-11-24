package com.mudi.weixin.base.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class DataSectionSpliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_OFFSET = 7;
    private static final int LENGTH_LENGTH = 4;
    private static final int MAGIC_NUM = 0x100782;

    public DataSectionSpliter() {
        super(Integer.MAX_VALUE, LENGTH_OFFSET, LENGTH_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.readableBytes() < 4 || MAGIC_NUM != in.getInt(in.readerIndex())) {
            ctx.close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
