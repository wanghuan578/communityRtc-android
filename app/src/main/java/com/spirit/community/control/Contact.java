package com.spirit.community.control;

import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private int type;
    private UserInfo userInfo;

    public Contact(String name, int type, UserInfo userInfo) {
        this.name = name;
        this.type = type;
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}