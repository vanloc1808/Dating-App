package com.example.datingapp.UserObject;

import java.io.Serializable;

public class UserObject implements Serializable {
    private String email;
    private String name;
    private String phone;
    private String notificationKey;

    private Boolean selected = false;

    public UserObject(String email) {
        this.email = email;
    }

    public UserObject(String name, String phone, String notificationKey) {
        this.name = name;
        this.phone = phone;
        this.notificationKey = notificationKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }
}
