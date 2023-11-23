package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // AppCompatActivity 继承了 Activity 类，拥有了窗口的特性，是一个可视化界面
    // MainActivity是一个可视化界面正是由于它继承了 AppCompatActivity

    // onCreate 来源于父类，它相对于activity而言就相当于 main 方法对于普通类
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置内容布局(视图)
        // setContentView 要求传入 布局id
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.chatting_layout);

        /* 用代码定义布局*/
       /* // 1.定义布局
        LinearLayout linearLayout = new LinearLayout(this);
        // 2.设置宽高
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        // 3.设置背景颜色
        linearLayout.setBackgroundColor(Color.GREEN);
        setContentView(linearLayout);*/
    }
}