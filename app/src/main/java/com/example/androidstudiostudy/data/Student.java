package com.example.androidstudiostudy.data;

import java.io.Serializable;

// 将对象序列化，序列化的作用
/* 1.想把内存中的对象保存到一个文件活数据库中时
 * 2.想利用套接字Socket在网络中传递对象*/
public class Student implements Serializable {
    private String name;
    private  int age;
    private  double money;
    private  boolean check;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Student(String name, int age, double money, boolean check) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.check = check;
    }
}
