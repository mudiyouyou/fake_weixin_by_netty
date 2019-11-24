package client;

import client.console.ConsoleCommandManager;
import client.handler.*;
import com.mudi.weixin.base.handler.CmdHandler;
import com.mudi.weixin.base.handler.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NettyClient {
    private static final int MAX_TRYIES = 5;
    private final Bootstrap bootstrap;

    public NettyClient() {
        bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new Spliter())
                                .addLast(new CmdHandler())
                                .addLast(new LoginResponseHandler())
                                .addLast(new ChatResponseHandler())
                                .addLast(new CreateGroupResponseHandler())
                                .addLast(new JoinGroupResponseHandler())
                                .addLast(new UserListResponseHandler());
                    }
                });
    }

    private void connect(String ip, int port, int tryies) throws InterruptedException {
        ChannelFuture connectFuture = bootstrap.connect(ip, port);
        connectFuture.addListener((ChannelFutureListener) future -> {
            if (!future.isSuccess()) {
                if (tryies > 0) {
                    TimeUnit.SECONDS.sleep(Math.round(Math.pow(2, MAX_TRYIES - tryies)));
                    connect(ip, port, MAX_TRYIES - 1);
                }
            }else {
                startConsole(future.channel());
            }
        });
    }

    public void connect(String ip, int port) throws InterruptedException {
        connect(ip, port, MAX_TRYIES);
    }

    private void startConsole(Channel channel) {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            new ConsoleCommandManager().exec(scanner,channel);
        }).start();
    }

}
