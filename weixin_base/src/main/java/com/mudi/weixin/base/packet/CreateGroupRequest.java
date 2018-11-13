package com.mudi.weixin.base.packet;

import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequest extends Packet{
    private List<String> memberIds;
    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
