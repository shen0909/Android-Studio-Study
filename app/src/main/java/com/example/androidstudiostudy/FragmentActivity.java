package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

// 创建一个新的activity 绑定布局 R.layout.activity_fragment ,在该布局里添加 fragment 控件，进行展示
public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // 用Java代码添加fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 使用add()方法添加一个fragment片段
        Fragment2 fragment2 = Fragment2.newInstance(null,null);
        fragmentTransaction.add(R.id.fragment2,fragment2);
        fragmentTransaction.commit();
    }
}