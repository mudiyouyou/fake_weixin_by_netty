package com.mudi.weixin.server;

import com.mudi.weixin.base.handler.CmdHandler;
import com.mudi.weixin.base.handler.Spliter;
import com.mudi.weixin.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class NettyServer {
    public static void start(int port) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup(1), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new Spliter())
                                .addLast(new CmdHandler())
                                .addLast(LoginRequestHandler.INSTANCE)
                                .addLast(AuthHandler.INSTANCE)
                                .addLast(ChatRequestHandler.INSTANCE)
                                .addLast(CreateGroupRequestHandler.INSTANCE)
                                .addLast(JoinGroupRequestHandler.INSTANCE)
                                .addLast(UserListRequestHandler.INSTANCE)
                                .addLast(QuitGroupRequestHandler.INSTANCE);
                    }
                });
        ChannelFuture f = bootstrap.bind(port).sync();
        f.addListener((ChannelFutureListener) future -> {
            if(future.isSuccess()){
                log.info("服务器绑定端口:{}",port);
            }
        });
    }

}
