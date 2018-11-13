package com.mudi.weixin.base.packet;

import lombok.Data;

@Data
public class JoinGroupRequest extends Packet {
    private String groupId;
    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
