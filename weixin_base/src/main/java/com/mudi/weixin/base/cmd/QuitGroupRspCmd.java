package com.mudi.weixin.base.cmd;

import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
@CmdType(CmdType.QUIT_GROUP_RESPONSE)
public class QuitGroupRspCmd extends AbstractRspCmd {
    private User user;
    public QuitGroupRspCmd(boolean success, String msg) {
        super(success, msg);
    }

    public QuitGroupRspCmd() {
    }

    @Override
    public byte getCommand() {
        return CmdType.QUIT_GROUP_RESPONSE;
    }
}
