package com.example.androidstudiostudy.asyncTask;

import android.os.SystemClock;

public class liveDataExample {
    public interface listener {
        public void deal(String data);
    }

    public void startThread(listener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                listener.deal("异步回来的数据");
            }
        }).start();
    }
}
