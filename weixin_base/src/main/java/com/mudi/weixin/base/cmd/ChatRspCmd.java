package com.mudi.weixin.base.cmd;

import com.mudi.weixin.base.model.User;
import lombok.Data;

@Data
@CmdType(CmdType.CHAT_RESPONSE)
public class ChatRspCmd extends AbstractRspCmd {
    private User from;
    private User to;
    private String content;

    public ChatRspCmd(boolean success, String msg) {
        super(success, msg);
    }

    public ChatRspCmd(boolean success) {
        super(success, "");
    }

    public ChatRspCmd() {
    }

    @Override
    public byte getCommand() {
        return CmdType.CHAT_RESPONSE;
    }
}
