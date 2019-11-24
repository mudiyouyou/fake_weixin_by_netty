package com.mudi.weixin.base.cmd;

import com.mudi.weixin.base.model.Group;
import lombok.Data;

@Data
@CmdType(CmdType.CREATE_GROUP_RESPONSE)
public class CreateGroupRspCmd extends AbstractRspCmd {
    private Group group;
    public CreateGroupRspCmd(boolean success, String msg) {
        super(success, msg);
    }

    public CreateGroupRspCmd() {
    }

    @Override
    public byte getCommand() {
        return CmdType.CREATE_GROUP_RESPONSE;
    }
}
