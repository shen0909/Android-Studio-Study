package com.example.androidstudiostudy.data;

// 2.1 准备数据源 - BaseAdapter 的数据源类
public class BaseMsg {
    private int icon;
    private String nickName;
    private String content;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    private boolean isLike;

    public BaseMsg(int icon, String nickName, String content, boolean isLike) {
        this.icon = icon;
        this.nickName = nickName;
        this.content = content;
        this.isLike = isLike;
    }
}
