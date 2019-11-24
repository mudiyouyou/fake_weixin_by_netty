package com.mudi.weixin.base.cmd;

import lombok.Data;

@Data
@CmdType(CmdType.CHAT_REQUEST)
public class ChatReqCmd extends Cmd {
    private String toUserId;
    private String content;
    @Override
    public byte getCommand() {
        return CmdType.CHAT_REQUEST;
    }
}
