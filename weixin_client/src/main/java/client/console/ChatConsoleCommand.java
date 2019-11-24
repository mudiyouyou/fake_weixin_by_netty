package client.console;

import client.service.UserLocalCacheMgr;
import com.mudi.weixin.base.cmd.ChatReqCmd;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ChatConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入聊天对象ID:");
        String idx = scanner.nextLine();
        try {
            int num = Integer.parseInt(idx);
            System.out.print("输入聊天的内容:");
            String content = scanner.nextLine();
            ChatReqCmd req = new ChatReqCmd();
            req.setToUserId(UserLocalCacheMgr.getUser(num).getId());
            req.setContent(content);
            channel.writeAndFlush(req);
        } catch (NumberFormatException e) {
            System.out.println("请输入有效数字");
            return;
        }
    }
}
