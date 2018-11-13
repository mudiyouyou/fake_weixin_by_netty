package com.mudi.weixin.base.packet;

import com.mudi.weixin.base.model.User;
import lombok.Data;

import java.util.Collection;

@Data
public class UserListResponse extends AbstractResponse {
    private Collection<User> users;
    public UserListResponse(boolean success, String msg) {
        super(success, msg);
    }

    public UserListResponse() {
    }

    @Override
    public byte getCommand() {
        return Command.USER_LIST_RESPONSE;
    }
}
