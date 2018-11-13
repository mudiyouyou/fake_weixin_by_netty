package client.console;

import com.mudi.weixin.base.packet.UserListRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

public class UserListConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        UserListRequest req = new UserListRequest();
        channel.writeAndFlush(req);
    }
}
