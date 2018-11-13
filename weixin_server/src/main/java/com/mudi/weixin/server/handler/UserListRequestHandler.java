package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.packet.UserListRequest;
import com.mudi.weixin.base.packet.UserListResponse;
import com.mudi.weixin.server.service.UserMgr;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Collection;

@ChannelHandler.Sharable
public class UserListRequestHandler extends SimpleChannelInboundHandler<UserListRequest> {
    public static final UserListRequestHandler INSTANCE = new UserListRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserListRequest req) throws Exception {
        UserListResponse res = new UserListResponse(true,"");
        Collection<User> user = UserMgr.list();
        res.setUsers(user);
        ctx.writeAndFlush(res);
    }
}
