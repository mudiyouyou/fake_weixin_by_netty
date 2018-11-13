package com.mudi.weixin.base.packet;

public interface Command {
    byte LOGIN_REQUEST = 1;
    byte LOGIN_RESPONSE = 2;
    byte CHAT_REQUEST = 3;
    byte CHAT_RESPONSE = 4;
    byte CREATE_GROUP_REQUEST = 5;
    byte CREATE_GROUP_RESPONSE = 6;
    byte QUIT_GROUP_REQUEST = 7;
    byte LOGOUT_REQUEST = 8;
    byte JOIN_GROUP_REQUEST = 9;
    byte JOIN_GROUP_RESPONSE = 10;
    byte LOGOUT_RESPONSE = 11;
    byte QUIT_GROUP_RESPONSE = 12;
    byte ERROR_RESPONSE = 13;
    byte USER_LIST_REQUSET = 14;
    byte USER_LIST_RESPONSE = 15;
    byte NEW_COMER_RESPONSE = 16;
    byte SOMEONE_QUIT_RESPONSE = 17;
}
