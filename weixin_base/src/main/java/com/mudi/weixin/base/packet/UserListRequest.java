package com.mudi.weixin.base.packet;

public class UserListRequest extends Packet {
    @Override
    public byte getCommand() {
        return Command.USER_LIST_REQUSET;
    }
}
