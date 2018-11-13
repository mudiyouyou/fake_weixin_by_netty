package client.console;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleCommandManager implements ConsoleCommand {
    private Map<String,ConsoleCommand> cmds = Maps.newHashMap();

    public ConsoleCommandManager() {
        cmds.put("chat",new ChatConsoleCommand());
        cmds.put("create group",new CreateGroupConsoleCommand());
        cmds.put("quit group",new QuitGroupConsoleCommand());
        cmds.put("join group",new JoinGroupConsoleCommand());
        cmds.put("logout",new LogoutConsoleCommand());
        cmds.put("login",new LoginConsoleCommand());
        cmds.put("users",new UserListConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        while (!Thread.interrupted()) {
            System.out.print("输入要执行的命令: ");
            String line = scanner.nextLine();
            if("help".equals(line)){
                help();
                continue;
            }
            ConsoleCommand cmd = cmds.get(line);
            if(cmd==null){
                System.out.println("不支持该命令["+line+"]");
                continue;
            }

            cmd.exec(scanner,channel);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void help(){
        cmds.keySet().stream().forEach(cmd-> System.out.println(cmd));
    }
}
