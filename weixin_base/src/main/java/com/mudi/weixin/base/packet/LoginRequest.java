package com.mudi.weixin.base.packet;

import lombok.Data;

@Data
public class LoginRequest extends Packet {
    private String username;
    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
