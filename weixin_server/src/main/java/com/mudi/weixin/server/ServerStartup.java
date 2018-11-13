package com.mudi.weixin.server;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerStartup {

    public static void main(String[] args) {
        try {
            NettyServer.start(8090);
        } catch (InterruptedException e) {
            log.error("服务端启动失败",e);
        }
    }
}
