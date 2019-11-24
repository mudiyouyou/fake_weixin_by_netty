package com.mudi.weixin.base.cmd;

import lombok.Data;

@Data
public abstract class AbstractRspCmd extends Cmd {
    private boolean success;
    private String msg;

    public AbstractRspCmd(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public AbstractRspCmd() {
    }
}
