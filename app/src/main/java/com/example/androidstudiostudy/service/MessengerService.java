package com.example.androidstudiostudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;

public class MessengerService extends Service {
    static final int MSG_SAY_HELLO = 1;
    private static final String TAG = "MessengerService";

    // 用于接收从客户端传递过来的数据
    private static class ServiceReciveHandle extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == MSG_SAY_HELLO) {
                Log.i(TAG, "服务器接收到来自客户端的消息");

                Messenger replyMessenger = msg.replyTo;
                Message replyMessenge = Message.obtain(null, MessengerService.MSG_SAY_HELLO);
                Bundle bundle=new Bundle();
                bundle.putString("reply","ok~,I had receiver message from you! ");
                replyMessenge.setData(bundle);
                try {
                    replyMessenger.send(replyMessenge);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            } else {
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