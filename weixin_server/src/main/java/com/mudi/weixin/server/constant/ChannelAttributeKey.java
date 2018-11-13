package com.mudi.weixin.server.constant;

import com.mudi.weixin.base.model.Session;
import io.netty.util.AttributeKey;

public class ChannelAttributeKey {
    public static AttributeKey<Session> SESSION = AttributeKey.newInstance("SESSION");
}
