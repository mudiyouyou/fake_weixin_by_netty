package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.Group;
import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.packet.QuitGroupRequest;
import com.mudi.weixin.base.packet.QuitGroupResponse;
import com.mudi.weixin.server.service.GroupMgr;
import com.mudi.weixin.server.service.SessionMgr;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequest> {
    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequest msg) throws Exception {
        Group group = GroupMgr.get(msg.getGroupId());
        if(group!=null){
            User self = SessionMgr.getUser(ctx.channel());
            group.getMembers().remove(self);
            QuitGroupResponse res = new QuitGroupResponse(true,"");
            res.setUser(self);
            ctx.writeAndFlush(res);
            group.send(res);
        }
    }
}
