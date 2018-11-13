package com.mudi.weixin.base.packet;

import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
public class QuitGroupResponse extends AbstractResponse {
    private User user;
    public QuitGroupResponse(boolean success, String msg) {
        super(success, msg);
    }

    public QuitGroupResponse() {
    }

    @Override
    public byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
