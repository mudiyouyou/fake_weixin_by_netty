package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.Group;
import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.packet.JoinGroupRequest;
import com.mudi.weixin.base.packet.JoinGroupResponse;
import com.mudi.weixin.base.packet.NewComerResponse;
import com.mudi.weixin.server.service.GroupMgr;
import com.mudi.weixin.server.service.SessionMgr;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequest> {
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequest msg) throws Exception {
        String groupId = msg.getGroupId();
        Group group = GroupMgr.get(groupId);
        final User user = SessionMgr.getUser(ctx.channel());
        group.getMembers().add(user);
        JoinGroupResponse res = new JoinGroupResponse(true, "");
        res.setGroup(group);
        user.send(res);
        final NewComerResponse response = new NewComerResponse(true);
        group.send(response);
    }
}
