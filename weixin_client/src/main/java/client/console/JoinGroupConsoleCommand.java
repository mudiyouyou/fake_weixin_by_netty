package client.console;

import com.mudi.weixin.base.cmd.JoinGroupReqCmd;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要加入的组ID:");
        String groupId = scanner.nextLine();
        JoinGroupReqCmd req = new JoinGroupReqCmd();
        req.setGroupId(groupId);
        channel.writeAndFlush(req);
    }
}
