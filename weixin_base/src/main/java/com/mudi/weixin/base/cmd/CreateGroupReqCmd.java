package com.mudi.weixin.base.cmd;

import lombok.Data;

import java.util.List;

@Data
@CmdType(CmdType.CREATE_GROUP_REQUEST)
public class CreateGroupReqCmd extends Cmd {
    private List<String> memberIds;
    @Override
    public byte getCommand() {
        return CmdType.CREATE_GROUP_REQUEST;
    }
}
