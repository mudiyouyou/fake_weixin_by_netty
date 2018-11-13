package client.console;

import com.mudi.weixin.base.packet.JoinGroupRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要加入的组ID:");
        String groupId = scanner.nextLine();
        JoinGroupRequest req = new JoinGroupRequest();
        req.setGroupId(groupId);
        channel.writeAndFlush(req);
    }
}
