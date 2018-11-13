package com.mudi.weixin.base.packet;

import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
public class NewComerResponse extends AbstractResponse {
    private String groupId;
    private User comer;
    public NewComerResponse(boolean sucess) {
        this.setSuccess(sucess);
    }

    @Override
    public byte getCommand() {
        return Command.NEW_COMER_RESPONSE;
    }
}
