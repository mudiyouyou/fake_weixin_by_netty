package client.handler;

import com.mudi.weixin.base.packet.JoinGroupResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponse msg) throws Exception {
        System.out.println(msg.getNewone().getName() + " 加入了 " + msg.getGroup().getOwner().getName() + " 的群");
    }
}
