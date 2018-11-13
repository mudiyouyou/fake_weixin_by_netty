package client.service;

import com.google.common.collect.Lists;
import com.mudi.weixin.base.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserLocalCacheMgr {
    private static List<User> userList = Lists.newCopyOnWriteArrayList();
    private static User self;

    public static User getSelf() {
        return self;
    }

    public static void setSelf(User self) {
        UserLocalCacheMgr.self = self;
    }

    public static void list() {
        System.out.println("在线用户列表");
        System.out.println("=======================");
        if (userList.isEmpty()) {
            System.out.println("空");
        }
        int idx = 0;
        for (User s : userList) {
            System.out.println(idx + " : " + s.getName());
            idx++;
        }
        System.out.println("=======================");
    }

    public static User getUser(int idx) {
        return userList.get(idx);
    }

    public static void update(Collection<User> toUpdate) {
        userList.clear();
        userList.addAll(toUpdate);
    }

    public static List<String> findUsers(List<String> idx) {
        return idx.stream()
                .filter(id -> {
                    try {
                        Integer.parseInt(id);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .map(id -> getUser(Integer.parseInt(id)).getId())
                .collect(Collectors.toList());
    }
}
