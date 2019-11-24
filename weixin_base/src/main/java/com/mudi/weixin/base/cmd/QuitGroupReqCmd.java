package com.mudi.weixin.base.cmd;

import lombok.Data;

@Data
@CmdType(CmdType.QUIT_GROUP_REQUEST)
public class QuitGroupReqCmd extends Cmd {
    private String groupId;
    @Override
    public byte getCommand() {
        return CmdType.QUIT_GROUP_REQUEST;
    }
}
