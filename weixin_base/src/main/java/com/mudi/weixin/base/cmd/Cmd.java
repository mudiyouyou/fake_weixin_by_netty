package com.mudi.weixin.base.cmd;

import lombok.Data;

@Data
public abstract class Cmd {
    private byte version = 1;
    public abstract byte getCommand();
}
