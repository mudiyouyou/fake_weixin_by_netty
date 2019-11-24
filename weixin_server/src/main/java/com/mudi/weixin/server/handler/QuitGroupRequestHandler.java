package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.Group;
import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.cmd.QuitGroupReqCmd;
import com.mudi.weixin.base.cmd.QuitGroupRspCmd;
import com.mudi.weixin.server.service.GroupMgr;
import com.mudi.weixin.server.service.SessionMgr;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupReqCmd> {
    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupReqCmd msg) throws Exception {
        Group group = GroupMgr.get(msg.getGroupId());
        if(group!=null){
            User self = SessionMgr.getUser(ctx.channel());
            group.getMembers().remove(self);
            QuitGroupRspCmd res = new QuitGroupRspCmd(true,"");
            res.setUser(self);
            ctx.writeAndFlush(res);
            group.send(res);
        }
    }
}
