package com.example.androidstudiostudy.data;

import java.util.List;

// 一个json 的数据对象（来自于GET返回的 json 字符串）
public class OneJsonBean {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "OneJsonBean对象{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                '}';
    }
}

