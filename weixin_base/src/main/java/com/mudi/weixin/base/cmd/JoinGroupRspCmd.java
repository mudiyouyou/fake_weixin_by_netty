package com.mudi.weixin.base.cmd;

import com.mudi.weixin.base.model.Group;
import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
@CmdType(CmdType.JOIN_GROUP_RESPONSE)
public class JoinGroupRspCmd extends AbstractRspCmd {
    private User newone;
    private Group group;
    public JoinGroupRspCmd(boolean success, String msg) {
        super(success, msg);
    }

    public JoinGroupRspCmd() {
    }

    @Override
    public byte getCommand() {
        return CmdType.JOIN_GROUP_RESPONSE;
    }
}
