package com.mudi.weixin.base.packet;

import lombok.Data;

@Data
public abstract class AbstractResponse extends Packet {
    private boolean success;
    private String msg;

    public AbstractResponse(boolean success,String msg) {
        this.success = success;
        this.msg = msg;
    }

    public AbstractResponse() {
    }
}
