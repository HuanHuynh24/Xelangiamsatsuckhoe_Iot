package com.example.gimstsckho_iot.model;

import com.google.firebase.Timestamp;

public class userModel {
    private String phoneNumber;
    private String userName;
    private Timestamp creatTimer;
    private String avatarUser;

    public String getAvatarUser() {
        return avatarUser;
    }

    public void setAvatarUser(String avatarUser) {
        this.avatarUser = avatarUser;
    }

    public userModel() {
    }

    public userModel(String phoneNumber, String userName, Timestamp creatTimer) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.creatTimer = creatTimer;
//        this.avatarUser = avatarUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreatTimer() {
        return creatTimer;
    }

    public void setCreatTimer(Timestamp creatTimer) {
        this.creatTimer = creatTimer;
    }
}
