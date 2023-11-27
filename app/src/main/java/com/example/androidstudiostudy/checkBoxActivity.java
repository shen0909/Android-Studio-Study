package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class checkBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        CheckBox checkBox = findViewById(R.id.checkBox1);

        // 设置选中状态
        checkBox.setChecked(false);
        // checkBox.isChecked() 获取选中状态
        boolean isChecked = checkBox.isChecked();

        Log.e("isChecked","当前复选框选中状态："+isChecked);
        // 监听状态变化 setOnCheckedChangeListener() 方法
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("isChecked","当前复选框选中状态："+isChecked);
            }
        });
    }
}