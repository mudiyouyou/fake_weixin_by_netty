package com.mudi.weixin.server.handler;

import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.cmd.ChatReqCmd;
import com.mudi.weixin.base.cmd.ChatRspCmd;
import com.mudi.weixin.server.service.SessionMgr;
import com.mudi.weixin.server.service.UserMgr;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class ChatRequestHandler extends SimpleChannelInboundHandler<ChatReqCmd> {
    public static final ChatRequestHandler INSTANCE = new ChatRequestHandler();

    private ChatRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatReqCmd msg) throws Exception {
        User target = UserMgr.get(msg.getToUserId());
        if(target==null){
            ChatRspCmd res = new ChatRspCmd(false,"未找到改UserId");
            ctx.writeAndFlush(res);
            return;
        }
        ChatRspCmd res = new ChatRspCmd(true);
        User from = SessionMgr.getUser(ctx.channel());
        res.setFrom(from);
        res.setTo(target);
        res.setContent(msg.getContent());
        target.send(res);
    }
}
