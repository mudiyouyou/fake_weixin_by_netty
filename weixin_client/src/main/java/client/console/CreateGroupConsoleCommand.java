package client.console;

import client.service.UserLocalCacheMgr;
import com.google.common.base.Splitter;
import com.mudi.weixin.base.cmd.CreateGroupReqCmd;
import io.netty.channel.Channel;

import java.util.List;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("准备建立群聊");
        System.out.print("输入聊天成员ID,使用英文逗号分隔:");
        String line = scanner.nextLine();
        CreateGroupReqCmd req = new CreateGroupReqCmd();
        List<String> userIds = UserLocalCacheMgr.findUsers(Splitter.on(";").splitToList(line));
        req.setMemberIds(userIds);
        channel.writeAndFlush(req);
    }
}
