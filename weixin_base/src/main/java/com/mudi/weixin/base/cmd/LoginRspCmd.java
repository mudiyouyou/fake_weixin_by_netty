package com.mudi.weixin.base.cmd;

import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
@CmdType(CmdType.LOGIN_RESPONSE)
public class LoginRspCmd extends AbstractRspCmd {
    private User user;
    public LoginRspCmd(boolean success, String msg) {
        super(success,msg);
    }

    public LoginRspCmd() {
    }

    @Override
    public byte getCommand() {
        return CmdType.LOGIN_RESPONSE;
    }
}
