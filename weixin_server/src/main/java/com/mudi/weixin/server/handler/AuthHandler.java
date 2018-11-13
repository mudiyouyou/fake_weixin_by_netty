package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.packet.LoginResponse;
import com.mudi.weixin.server.service.SessionMgr;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class AuthHandler extends SimpleChannelInboundHandler<Object> {
    public static final AuthHandler INSTANCE= new AuthHandler();

    private AuthHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        System.out.println("认证检查");
        if (!SessionMgr.hasLogin(ctx.channel())) {
            LoginResponse res = new LoginResponse(false, "请先登录再进行其他操作");
            ctx.channel().writeAndFlush(res);
            return;
        }
        ctx.pipeline().remove(this);
        ctx.fireChannelRead(o);
    }
}
