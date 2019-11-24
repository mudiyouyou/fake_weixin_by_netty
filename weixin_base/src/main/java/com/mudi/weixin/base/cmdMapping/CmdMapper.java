package com.mudi.weixin.base.cmdMapping;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.reflect.ClassPath;
import com.mudi.weixin.base.cmd.Cmd;
import com.mudi.weixin.base.cmd.CmdType;
import com.mudi.weixin.base.cmd.LoginReqCmd;

import java.io.IOException;
import java.util.Map;

public class CmdMapper {
    private Map<Byte, Class<? extends Cmd>> mapping = Maps.newHashMap();

    public CmdMapper() throws IOException {
        ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
        ImmutableSet<ClassPath.ClassInfo> classInfos = classPath.getTopLevelClasses("com.mudi.weixin.base.cmd");
        classInfos.stream().forEach(classInfo -> {
            Class<?> aClass = classInfo.load();
            if (Cmd.class.isAssignableFrom(aClass)){
                if (aClass.isAnnotationPresent(CmdType.class)) {
                    CmdType type = aClass.getAnnotation(CmdType.class);
                    mapping.put(type.value(), (Class<? extends Cmd>)aClass);
                }
            }
        });
    }

    public Class<? extends Cmd> get(byte type) {
        return mapping.get(type);
    }
}
