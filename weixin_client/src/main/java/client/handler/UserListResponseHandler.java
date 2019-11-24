package client.handler;

import client.service.UserLocalCacheMgr;
import com.mudi.weixin.base.cmd.UserListRspCmd;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class UserListResponseHandler extends SimpleChannelInboundHandler<UserListRspCmd> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserListRspCmd res) throws Exception {
        if (res.isSuccess()) {
            UserLocalCacheMgr.update(res.getUsers());
            UserLocalCacheMgr.list();
        }
    }
}
