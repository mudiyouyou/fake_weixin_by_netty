package com.mudi.weixin.base.cmd;

import lombok.Data;

@Data
@CmdType(CmdType.LOGIN_REQUEST)
public class LoginReqCmd extends Cmd {
    private String username;
    @Override
    public byte getCommand() {
        return CmdType.LOGIN_REQUEST;
    }
}
