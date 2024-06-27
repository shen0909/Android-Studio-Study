package com.example.androidstudiostudy.UI;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.androidstudiostudy.R;

public class ProgressBarActivyty extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ProgressBar progressBar1 = findViewById(R.id.progress1);
        // 设置progressBar进度
//        progressBar1.setProgress(80);
        // 通过代码控制进度--- 利用线程
        // 但是在Android 4.0 之后不能在线程中直接操纵空间，会崩溃。progressBar是一个特例
        new Thread(){
            public void run(){
                for (int i = 1; i <= 100 ; i++) {
                    progressBar1.setProgress(i);
                    try {
                        // 休眠一下
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }
}