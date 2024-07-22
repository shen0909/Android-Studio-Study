package com.example.androidstudiostudy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidstudiostudy.R;

public class BroadcastStudy extends AppCompatActivity {

    public String SEND_STATIC_ACTION = "send_static_action";
    public String SEND_DYNAMIC_ACTION = "send_dynamic_action";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_study);

        // 动态注册广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SEND_DYNAMIC_ACTION);
        registerReceiver(dynamicBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dynamicBroadcastReceiver);
    }

    // 创建广播接收器
    private final BroadcastReceiver dynamicBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Log.d("BroadcastReceiver","动态广播接收器接受了一个 - "+bundle.getString("data"));

        }
    };

    public void sendBroadcast(View view) {
        if(view.getId() == R.id.send_static){
            Intent intent = new Intent();
            intent.setAction(SEND_STATIC_ACTION);
            intent.putExtra("data","静态广播");
            sendBroadcast(intent);
        }
        if(view.getId() == R.id.send_dynamic){
            Intent intent = new Intent();
            intent.setAction(SEND_DYNAMIC_ACTION);
            intent.putExtra("data","动态广播");
            sendBroadcast(intent);
        }
    }
}