package com.mudi.weixin.base.model;

import com.google.common.collect.Maps;

import java.util.Map;

public class Session {
    public static final String USER = "__user";
    private Map<String,Object> data;

    private Session(Map<String,Object> data) {
        this.data = data;
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public boolean containsKey(Object key) {
        return data.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return data.containsValue(value);
    }

    public Object get(Object key) {
        return data.get(key);
    }

    public Object put(String key, Object value) {
        return data.put(key, value);
    }

    public Object remove(Object key) {
        return data.remove(key);
    }

    public void clear() {
        data.clear();
    }

    public static class Builder{
        private Map<String,Object> data = Maps.newConcurrentMap();

        public Builder add(String key,Object obj){
            data.put(key,obj);
            return this;
        }

        public Session build(){
            return new Session(data);
        }
    }
}
