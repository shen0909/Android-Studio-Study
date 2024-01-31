package com.example.androidstudiostudy.data;

import java.io.Serializable;

public class UserBean implements Serializable {
    private String name,passWord;
    private String  phone;
    private int id;

    public UserBean(String name, String passWord, String phone) {
        this.name = name;
        this.passWord = passWord;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
