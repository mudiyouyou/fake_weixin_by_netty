package com.mudi.weixin.server.service;

import com.google.common.collect.Maps;
import com.mudi.weixin.base.model.Group;
import com.mudi.weixin.base.model.User;

import java.util.Map;

public class GroupMgr {
    private static Map<String, Group> groups = Maps.newConcurrentMap();
    public static Group createGroup(User owner){
        Group group = new Group(owner);
        groups.put(group.getId(),group);
        return group;
    }

    public static void removeGroup(String gid){
        Group group = groups.get(gid);
        group.close();
        groups.remove(gid);
    }

    public static void addMember(String gid,User member){
        groups.get(gid).getMembers().add(member);
    }

    public static void removeMember(String gid,User member){
        final Group group = groups.get(gid);
        group.getMembers().remove(member);
        if(group.getMembers().isEmpty()){
            group.close();
            groups.remove(gid);
        }
    }

    public static Group get(String id) {
        return groups.get(id);
    }
}
