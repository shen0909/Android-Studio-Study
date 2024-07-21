package com.example.androidstudiostudy.UI;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;

import com.example.androidstudiostudy.R;

// 创建一个自定义对话框类
public class MyDialog extends Dialog {
    // 只有一个参数的构造方法。传递需要添加自定义对话框的环境上下文
    /*public MyDialog(@NonNull Context context) {
        super(context);
        // 1.为对话框设置布局
        setContentView(R.layout.dialog_layout);
    }*/
    // 参数1：需要添加自定义对话框的环境上下文
    // 参数2: 设置的样式id
    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        // 自定义对话框-3.将布局应用到当前自定义的对话框
        setContentView(R.layout.dialog_layout);

        // 为对话框的两个按钮设置点击方法
        findViewById(R.id.yes_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击确认 -退出
                System.exit(0);
            }
        });
        findViewById(R.id.no_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击取消 -取消弹窗
                dismiss();
            }
        });
    }
}
