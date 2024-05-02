package com.example.gimstsckho_iot.model;

import com.google.firebase.Timestamp;

public class userModel {
    private String phoneNumber, ngaysinh, diachi, gioitinh;
    private String userName;
    private Timestamp creatTimer;

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

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
    public userModel(String phoneNumber, String userName, Timestamp creatTimer, String ngaysinh, String diachi, String gioitinh) {
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
