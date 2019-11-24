package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.cmd.UserListReqCmd;
import com.mudi.weixin.base.cmd.UserListRspCmd;
import com.mudi.weixin.server.service.UserMgr;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Collection;

@ChannelHandler.Sharable
public class UserListRequestHandler extends SimpleChannelInboundHandler<UserListReqCmd> {
    public static final UserListRequestHandler INSTANCE = new UserListRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserListReqCmd req) throws Exception {
        UserListRspCmd res = new UserListRspCmd(true,"");
        Collection<User> users = UserMgr.listOnLine();
        res.setUsers(users);
        ctx.writeAndFlush(res);
    }
}
