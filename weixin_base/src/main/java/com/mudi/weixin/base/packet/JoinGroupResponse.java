package com.mudi.weixin.base.packet;

import com.mudi.weixin.base.model.Group;
import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
public class JoinGroupResponse extends AbstractResponse {
    private User newone;
    private Group group;
    public JoinGroupResponse(boolean success, String msg) {
        super(success, msg);
    }

    public JoinGroupResponse() {
    }

    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
