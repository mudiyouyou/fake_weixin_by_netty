package client.handler;

import com.mudi.weixin.base.packet.ChatResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatResponseHandler extends SimpleChannelInboundHandler<ChatResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatResponse msg) throws Exception {
        if (!msg.isSuccess()) {
            System.out.println("消息发送失败:" + msg.getMsg());
            return;
        }
        System.out.println(msg.getFrom().getName() + " 说: " + msg.getContent());
    }
}
