package com.mudi.weixin.server.service;

import com.mudi.weixin.server.constant.ChannelAttributeKey;
import com.mudi.weixin.base.model.Session;
import com.mudi.weixin.base.model.User;
import io.netty.channel.Channel;

public class SessionMgr {

    public static void bindSession(Session session, Channel channel) {
        channel.attr(ChannelAttributeKey.SESSION).set(session);
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(ChannelAttributeKey.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(ChannelAttributeKey.SESSION).get();
    }

    public static User getUser(Channel channel) {
        return (User) getSession(channel).get(Session.USER);
    }
}
