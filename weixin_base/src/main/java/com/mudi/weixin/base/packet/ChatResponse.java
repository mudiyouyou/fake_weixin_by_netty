package com.mudi.weixin.base.packet;

import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
public class ChatResponse extends AbstractResponse {
    private User from;
    private User to;
    private String content;

    public ChatResponse(boolean success, String msg) {
        super(success, msg);
    }

    public ChatResponse(boolean success) {
        super(success, "");
    }

    public ChatResponse() {
    }

    @Override
    public byte getCommand() {
        return Command.CHAT_RESPONSE;
    }
}
