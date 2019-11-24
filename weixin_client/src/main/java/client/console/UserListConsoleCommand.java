package client.console;

import com.mudi.weixin.base.cmd.UserListReqCmd;
import io.netty.channel.Channel;

import java.util.Scanner;

public class UserListConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        UserListReqCmd req = new UserListReqCmd();
        channel.writeAndFlush(req);
    }
}
