package client.handler;

import client.service.UserLocalCacheMgr;
import com.mudi.weixin.base.packet.UserListResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class UserListResponseHandler extends SimpleChannelInboundHandler<UserListResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserListResponse res) throws Exception {
        if (res.isSuccess()) {
            UserLocalCacheMgr.update(res.getUsers());
            UserLocalCacheMgr.list();
        }
    }
}
