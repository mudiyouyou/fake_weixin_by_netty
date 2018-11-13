package com.mudi.weixin.base.packet;

import lombok.Data;

@Data
public class ChatRequest extends Packet {
    private String toUserId;
    private String content;
    @Override
    public byte getCommand() {
        return Command.CHAT_REQUEST;
    }
}
