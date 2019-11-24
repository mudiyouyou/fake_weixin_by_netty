package client.handler;

import com.mudi.weixin.base.cmd.LoginRspCmd;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginRspCmd> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRspCmd msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("欢迎你," + msg.getUser().getName());
        } else {
            System.out.println("失败原因是:" + msg.getMsg());
        }
    }
}
