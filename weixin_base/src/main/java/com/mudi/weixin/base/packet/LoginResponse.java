package com.mudi.weixin.base.packet;

import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
public class LoginResponse extends AbstractResponse{
    private User user;
    public LoginResponse(boolean success,String msg) {
        super(success,msg);
    }

    public LoginResponse() {
    }

    @Override
    public byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
