package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.Session;
import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.cmd.LoginReqCmd;
import com.mudi.weixin.base.cmd.LoginRspCmd;
import com.mudi.weixin.server.service.SessionMgr;
import com.mudi.weixin.server.service.UserMgr;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginReqCmd> {
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginReqCmd msg) throws Exception {
        LoginRspCmd res = doLogin(ctx.channel(),msg);
        ctx.writeAndFlush(res);
    }

    private LoginRspCmd doLogin(Channel channel, LoginReqCmd msg) {
        User user = UserMgr.addUser(channel,msg.getUsername());
        SessionMgr.bindSession(new Session.Builder().add(Session.USER,user).build(),channel);
        log.info("{}登录成功",msg.getUsername());
        LoginRspCmd res = new LoginRspCmd(true, "登录成功");
        res.setUser(user);
        return res;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        UserMgr.offLineUser(ctx.channel());
    }
}
