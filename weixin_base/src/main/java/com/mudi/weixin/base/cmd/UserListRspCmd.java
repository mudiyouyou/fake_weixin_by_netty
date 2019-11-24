package com.mudi.weixin.base.cmd;

import com.mudi.weixin.base.model.User;
import lombok.Data;

import java.util.Collection;

@Data
@CmdType(CmdType.USER_LIST_RESPONSE)
public class UserListRspCmd extends AbstractRspCmd {
    private Collection<User> users;
    public UserListRspCmd(boolean success, String msg) {
        super(success, msg);
    }

    public UserListRspCmd() {
    }

    @Override
    public byte getCommand() {
        return CmdType.USER_LIST_RESPONSE;
    }
}
