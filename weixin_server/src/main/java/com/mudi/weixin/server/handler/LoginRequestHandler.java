package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.Session;
import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.packet.LoginRequest;
import com.mudi.weixin.base.packet.LoginResponse;
import com.mudi.weixin.server.service.SessionMgr;
import com.mudi.weixin.server.service.UserMgr;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest msg) throws Exception {
        LoginResponse res = doLogin(ctx.channel(),msg);
        ctx.writeAndFlush(res);
    }

    private LoginResponse doLogin(Channel channel, LoginRequest msg) {
        User user = UserMgr.addUser(channel,msg.getUsername());
        SessionMgr.bindSession(new Session.Builder().add(Session.USER,user).build(),channel);
        log.info("{}登录成功",msg.getUsername());
        LoginResponse res = new LoginResponse(true, "登录成功");
        res.setUser(user);
        return res;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        UserMgr.offLineUser(ctx.channel());
    }
}
