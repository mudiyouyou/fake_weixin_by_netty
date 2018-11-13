package com.mudi.weixin.base.packet;

import com.google.common.collect.Maps;
import com.mudi.weixin.base.serializer.Serializer;
import com.mudi.weixin.base.serializer.SerializerFactory;
import io.netty.buffer.ByteBuf;

import javax.activation.UnsupportedDataTypeException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Map;

public class PacketCodecUtil {
    private static final int MAGIC_NUM = 0x100782;
    private static final String PASSWORD = "vCb01hOGGj7W7z49B/4OFsEB2hzmE22J";
    public static final String KEY_ALGORITHM = "DESede";

    private static Map<Byte, Class> mapping = Maps.newHashMap();
    private static SecretKey secret = null;
    private static SecureRandom random = null;

    static {
        mapping.put(Command.LOGIN_REQUEST, LoginRequest.class);
        mapping.put(Command.LOGIN_RESPONSE, LoginResponse.class);
        mapping.put(Command.CHAT_REQUEST, ChatRequest.class);
        mapping.put(Command.CHAT_RESPONSE, ChatResponse.class);
        mapping.put(Command.LOGOUT_REQUEST, LogoutRequest.class);
        mapping.put(Command.LOGOUT_RESPONSE, LogoutResponse.class);
        mapping.put(Command.USER_LIST_REQUSET, UserListRequest.class);
        mapping.put(Command.USER_LIST_RESPONSE, UserListResponse.class);
        mapping.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequest.class);
        mapping.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponse.class);
        mapping.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequest.class);
        mapping.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponse.class);
        mapping.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequest.class);
        mapping.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponse.class);

        try {
            random = new SecureRandom();
            DESedeKeySpec spec = new DESedeKeySpec(Base64.getDecoder().decode(PASSWORD));
            SecretKeyFactory facotory = SecretKeyFactory.getInstance("DESede");
            secret = facotory.generateSecret(spec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }




    public static void encode(Packet packet, ByteBuf byteBuf, Serializer serializer) throws Exception {
        byteBuf.writeInt(MAGIC_NUM);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byte[] data = serializer.serialize(packet);
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        data = cipher.doFinal(data);
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
    }

    public static Packet decode(ByteBuf data) throws Exception {
        // 魔法数
        data.skipBytes(4);
        // 版本
        data.skipBytes(1);
        // 序列类型
        byte serializerType = data.readByte();
        // 命令
        byte command = data.readByte();
        // 长度
        int length = data.readInt();
        Serializer serializer = SerializerFactory.create(serializerType);
        byte[] content = new byte[length];
        // 读取内容
        data.readBytes(content);
        // 解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secret);
        content = cipher.doFinal(content);
        Class<? extends Packet> requestType = getRequestTyep(command);
        return serializer.deserialize(requestType, content);
    }

    private static Class<? extends Packet> getRequestTyep(byte command) throws UnsupportedDataTypeException {
        Class clazz = mapping.get(command);
        if (clazz == null) {
            throw new UnsupportedDataTypeException("不支持该命令[" + command + "]");
        }
        return clazz;
    }


    public static String generateKey() throws Exception {
        //实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        //DESede 要求密钥长度为 112位或168位
        kg.init(168);
        //生成密钥
        SecretKey secretKey = kg.generateKey();
        //获得密钥的字符串形式
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

}
