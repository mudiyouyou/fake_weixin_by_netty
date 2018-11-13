package client.console;

import com.mudi.weixin.base.packet.LoginRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入用户名:");
        String username = scanner.nextLine();
        LoginRequest req = new LoginRequest();
        req.setUsername(username);
        channel.writeAndFlush(req);
    }
}
