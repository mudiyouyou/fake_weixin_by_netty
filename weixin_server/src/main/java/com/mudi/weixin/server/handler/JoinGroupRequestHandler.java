package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.Group;
import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.cmd.JoinGroupReqCmd;
import com.mudi.weixin.base.cmd.JoinGroupRspCmd;
import com.mudi.weixin.base.cmd.NewComerRspCmd;
import com.mudi.weixin.server.service.GroupMgr;
import com.mudi.weixin.server.service.SessionMgr;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupReqCmd> {
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupReqCmd msg) throws Exception {
        String groupId = msg.getGroupId();
        Group group = GroupMgr.get(groupId);
        final User user = SessionMgr.getUser(ctx.channel());
        group.getMembers().add(user);
        JoinGroupRspCmd res = new JoinGroupRspCmd(true, "");
        res.setGroup(group);
        user.send(res);
        final NewComerRspCmd response = new NewComerRspCmd(true);
        group.send(response);
    }
}
