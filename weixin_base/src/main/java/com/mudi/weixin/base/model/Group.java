package com.mudi.weixin.base.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.mudi.weixin.base.cmd.AbstractRspCmd;
import com.mudi.weixin.base.utils.IdGenerator;
import lombok.Data;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class Group {
    @JSONField
    private String id;
    @JSONField
    private User owner;
    @JSONField
    private Set<User> members = new HashSet<>();

    public Group(User owner) {
        this.id = IdGenerator.next();
        this.owner = owner;
        this.members.add(owner);
    }

    public void send(AbstractRspCmd response) {
        for (User user : members) {
            user.send(response);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id);
    }

    public void close() {
        for(User user:members){
            if(user.getChannel()!=null && user.getChannel().isActive()){
                user.getChannel().close();
            }
        }
    }
}
