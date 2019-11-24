package com.mudi.weixin.base.cmd;

import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
@CmdType(CmdType.NEW_COMER_RESPONSE)
public class NewComerRspCmd extends AbstractRspCmd {
    private String groupId;
    private User comer;
    public NewComerRspCmd(boolean sucess) {
        this.setSuccess(sucess);
    }

    @Override
    public byte getCommand() {
        return CmdType.NEW_COMER_RESPONSE;
    }
}
