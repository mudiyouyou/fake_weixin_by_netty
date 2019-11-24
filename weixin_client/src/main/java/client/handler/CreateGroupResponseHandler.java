package client.handler;

import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.cmd.CreateGroupRspCmd;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupRspCmd> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRspCmd msg) throws Exception {
        StringBuilder buf = new StringBuilder();
        for(User u:msg.getGroup().getMembers()) {
            buf.append(u.getName()).append(",");
        }
        System.out.println("欢迎加入本群,成员有" + buf.toString());
    }
}
