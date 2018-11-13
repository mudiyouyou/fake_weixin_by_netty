package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.Group;
import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.packet.CreateGroupRequest;
import com.mudi.weixin.base.packet.CreateGroupResponse;
import com.mudi.weixin.server.service.GroupMgr;
import com.mudi.weixin.server.service.SessionMgr;
import com.mudi.weixin.server.service.UserMgr;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.stream.Collectors;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequest> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequest msg) throws Exception {
        User self = SessionMgr.getUser(ctx.channel());
        Group group = GroupMgr.createGroup(self);
        List<User> members = msg.getMemberIds().stream()
                .map(userId -> UserMgr.get(userId))
                .collect(Collectors.toList());
        group.getMembers().addAll(members);
        CreateGroupResponse res = new CreateGroupResponse(true,"");
        res.setGroup(group);
        group.send(res);
    }
}
