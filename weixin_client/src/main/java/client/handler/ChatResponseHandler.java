package client.handler;

import com.mudi.weixin.base.cmd.ChatRspCmd;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatResponseHandler extends SimpleChannelInboundHandler<ChatRspCmd> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRspCmd msg) throws Exception {
        if (!msg.isSuccess()) {
            System.out.println("消息发送失败:" + msg.getMsg());
            return;
        }
        System.out.println(msg.getFrom().getName() + " 说: " + msg.getContent());
    }
}
