package com.mudi.weixin.server.service;

import com.google.common.collect.Maps;
import com.mudi.weixin.base.model.User;
import com.mudi.weixin.base.utils.IdGenerator;
import io.netty.channel.Channel;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMgr {
    private static Map<String, User> users = Maps.newConcurrentMap();

    public static User addUser(Channel channel, String userName) {
        final String id = IdGenerator.next();
        User user = new User(channel, id, userName);
        users.put(id, user);
        return user;
    }

    public static void removeUser(Channel channel) {
        Iterator<Map.Entry<String, User>> it = users.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, User> entry = it.next();
            if (entry.getValue().getChannel().id().equals(channel.id())) {
                it.remove();
            }
        }
    }

    public static User offLineUser(Channel channel) {
        User user = SessionMgr.getUser(channel);
        User u = UserMgr.get(user.getId());
        u.setStatus(User.OFF_LINE);
        return u;
    }

    public static User get(String id) {
        return users.get(id);
    }

    public static Collection<User> listOnLine() {
        return Collections.unmodifiableCollection(
                users.values()
                        .stream()
                        .filter(u -> u.getStatus() == User.ON_LINE)
                        .collect(Collectors.toList()));
    }
}
