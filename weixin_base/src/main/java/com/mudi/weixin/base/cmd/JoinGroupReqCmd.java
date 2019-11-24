package com.mudi.weixin.base.cmd;

import lombok.Data;

@Data
@CmdType(CmdType.JOIN_GROUP_REQUEST)
public class JoinGroupReqCmd extends Cmd {
    private String groupId;
    @Override
    public byte getCommand() {
        return CmdType.JOIN_GROUP_REQUEST;
    }
}
