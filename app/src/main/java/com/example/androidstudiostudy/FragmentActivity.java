package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

// 创建一个新的activity 绑定布局 R.layout.activity_fragment ,在该布局里添加 fragment 控件，进行展示
public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}