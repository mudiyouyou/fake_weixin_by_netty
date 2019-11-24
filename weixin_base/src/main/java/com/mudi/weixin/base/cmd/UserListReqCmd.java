package com.mudi.weixin.base.cmd;

import lombok.Data;

@Data
@CmdType(CmdType.USER_LIST_REQUSET)
public class UserListReqCmd extends Cmd {
    @Override
    public byte getCommand() {
        return CmdType.USER_LIST_REQUSET;
    }
}
