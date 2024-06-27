package com.example.androidstudiostudy.async;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidstudiostudy.R;

public class TimerActivity extends AppCompatActivity {

    private TextView title, context, text;
    private ImageView button;
    private boolean isStart = false;

    // 实例化一个handle
    Handler handler = new Handler(
            new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    context.setText(msg.arg1);
                    return false;
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        title = findViewById(R.id.timer_title);
        context = findViewById(R.id.timer_context);
        text = findViewById(R.id.timer_text);
        button = findViewById(R.id.timer_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = !isStart;
                System.out.println("当前正在工作？" + isStart);
                text.setText("工作中");
                button.setImageResource(R.mipmap.stop);
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        int i = 0;
                        while (true){
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            // 利用 handle 更新UI
                            Message message = new Message();
                            message.arg1 = i;
                            handler.sendMessage(message);
                            i++;

                        }
                    }
                }.start();
                /*if (isStart) {
                    text.setText("工作中");
                    button.setImageResource(R.mipmap.stop);
                    // 开一个新的线程
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            int i = 0;
                            while (isStart){
                                try {
                                    sleep(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                i++;
                                // 利用 handle 更新UI
                                Message message = new Message();
                                message.arg1 = i;
                                handler.sendMessage(message);
                            }
                        }
                    }.start();
                }else{
                    text.setText("已停止");
                    button.setImageResource(R.mipmap.start);
                }*/

            }
        });
    }
}