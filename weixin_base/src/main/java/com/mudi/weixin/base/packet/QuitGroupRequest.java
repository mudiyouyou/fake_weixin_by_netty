package com.mudi.weixin.base.packet;

import lombok.Data;

@Data
public class QuitGroupRequest extends Packet{
    private String groupId;
    @Override
    public byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
