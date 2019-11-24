package client.console;

import com.mudi.weixin.base.cmd.QuitGroupReqCmd;
import io.netty.channel.Channel;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("准备退出群聊");
        System.out.print("输入群聊组ID");
        String line = scanner.nextLine();
        QuitGroupReqCmd req = new QuitGroupReqCmd();
        req.setGroupId(line);
        channel.writeAndFlush(req);
    }
}
