package com.mudi.weixin.base.packet;

import com.mudi.weixin.base.model.Group;
import lombok.Data;

@Data
public class CreateGroupResponse extends AbstractResponse {
    private Group group;
    public CreateGroupResponse(boolean success, String msg) {
        super(success, msg);
    }

    public CreateGroupResponse() {
    }

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
