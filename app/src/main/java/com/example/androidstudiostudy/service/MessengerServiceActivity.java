package com.example.androidstudiostudy.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidstudiostudy.R;

public class MessengerServiceActivity extends AppCompatActivity {
    private static final String TAG = "MessengerService-Activity";

    // 与服务端交互的Messenger
    private Messenger myService = null;
    // 是否绑定
    boolean mBound = false;
    // 实现与服务端链接的对象
    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // 通过服务端传递的IBinder对象,创建相应的Messenger
            // 通过该Messenger对象与服务端进行交互
            Log.i(TAG, "服务链接绑定");
            myService = new Messenger(iBinder);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "服务链接绑定取消");
            myService = null;
            mBound = false;
        }
    };

    private Button sendMsg, bindService, unbindService, createService, destoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_service);
        sendMsg = findViewById(R.id.sendMessageToService);
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mBound)
                    return;
                Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
                try {
                    // 发送消息
                    myService.send(msg);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        Intent intent = new Intent(MessengerServiceActivity.this, MessengerService.class);

        unbindService = findViewById(R.id.unbindMessengerService);
        unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(mConnection);
            }
        });

        bindService = findViewById(R.id.bindMessengerService);
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        createService = findViewById(R.id.startMessengerService);
        createService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
            }
        });

        destoryService = findViewById(R.id.destoreyMessengerService);
        destoryService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });
    }
}