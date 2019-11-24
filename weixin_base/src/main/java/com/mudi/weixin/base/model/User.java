package com.mudi.weixin.base.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.mudi.weixin.base.cmd.AbstractRspCmd;
import io.netty.channel.Channel;
import lombok.Data;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class User {
    public static final int OFF_LINE = 0;
    public static final int ON_LINE = 1;
    private Channel channel;
    @JSONField
    private String id;
    @JSONField
    private String name;
    @JSONField
    private int status;  // 1:online , 0:offLine
    private Queue<AbstractRspCmd> message = new ConcurrentLinkedQueue<>();
    public User(Channel channel, String id, String name) {
        this.channel = channel;
        this.id = id;
        this.name = name;
        this.status=1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    public void send(AbstractRspCmd response){
        if(status==1){
            channel.writeAndFlush(response);
        }else {
            message.add(response);
        }
    }

    public void offLine() {
        this.setStatus(OFF_LINE);
        this.setChannel(null);
    }
}
