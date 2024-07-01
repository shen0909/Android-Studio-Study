package com.example.androidstudiostudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import androidx.annotation.NonNull;

public class MessengerService extends Service {
    static final int MSG_SAY_HELLO = 1;
    private static final String TAG = "MessengerService";

    // 用于接收从客户端传递过来的数据
    class ServiceReciveHandle extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Log.i(TAG, "服务器接收到来自客户端的消息");

                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    final Messenger messenger = new Messenger(new ServiceReciveHandle());

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "服务绑定");
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "服务onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "服务Destroy");
        super.onDestroy();
    }
}