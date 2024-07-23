package com.example.androidstudiostudy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.example.androidstudiostudy.R;
import com.example.androidstudiostudy.broadcastreceiver.receiver.LocalBroadcastReceiver;

public class BroadcastStudy extends AppCompatActivity {

    public String SEND_STATIC_ACTION = "send_static_action";
    public String SEND_DYNAMIC_ACTION = "send_dynamic_action";
    public String SEND_LOCAL_ACTION = "send_local_action";
    public String SEND_GLOBAL_ACTION = "send_global_action";
    private LocalBroadcastManager localBroadcastManager ;
    private final LocalBroadcastReceiver localBroadcastReceiver = new LocalBroadcastReceiver(); // 本地广播接收器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_study);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        // 动态注册广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SEND_DYNAMIC_ACTION);
        intentFilter.setPriority(100);
        registerReceiver(dynamicBroadcastReceiver, intentFilter);

        // 注册有序广播
        // 有序广播设置优先级，优先级越大越先收到
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(SEND_DYNAMIC_ACTION);
        intentFilter1.setPriority(101);
        registerReceiver(broadcastReceiver2, intentFilter1);

        // 注册本地广播 - 只能动态注册
        IntentFilter local_intentFilter = new IntentFilter();
        local_intentFilter.addAction(SEND_LOCAL_ACTION);
        localBroadcastManager.registerReceiver(localBroadcastReceiver,local_intentFilter);
    }

    // 创建广播接收器
    private final BroadcastReceiver dynamicBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Bundle bundle = intent.getExtras();

            // 下游使用 getResultExtras  接收 Bundle，使用 getResultData 接收字符串
            Bundle bundle = getResultExtras(true);
            String newdata = getResultData();
            Log.d("BroadcastReceiver", "动态广播接收器1接受了一个 - " + bundle.getString("new_data")+"\t"+newdata);
        }
    };

    private final BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Log.d("BroadcastReceiver", "动态广播接收器2接受了一个 - " + bundle.getString("data"));
            // 截断广播
            // abortBroadcast();

            // 修改广播
            // broadcastReceiver2 优先级高，所以是上游广播接收器，可以使用 setResultExtras 传递一个新的 Bundle 或者使用 setResultData 传递一个字符串
            bundle.putString("new_data","新信息");
            setResultExtras(bundle);
            setResultData("上游传递了一个字符串");
        }
    };

    public void sendBroadcast(View view) {
        if (view.getId() == R.id.send_static) {
            Intent intent = new Intent();
            intent.setAction(SEND_STATIC_ACTION);
            intent.putExtra("data", "静态广播");
            sendBroadcast(intent);
        }
        if (view.getId() == R.id.send_dynamic) {
            Intent intent = new Intent();
            intent.setAction(SEND_DYNAMIC_ACTION);
            intent.putExtra("data", "广播-11111111");
            // sendBroadcast(intent);

            // 发送有序广播
            sendOrderedBroadcast(intent, null);
        }
        if (view.getId() == R.id.send_local) {
            Intent intent = new Intent();
            intent.setAction(SEND_LOCAL_ACTION);
            intent.putExtra("data", "本地广播");
            localBroadcastManager.sendBroadcast(intent);
        }
        if (view.getId() == R.id.send_global) {
            Intent intent = new Intent();
            intent.setAction(SEND_GLOBAL_ACTION);
            intent.putExtra("data_global", "发送了一条全局广播");
            sendBroadcast(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dynamicBroadcastReceiver);
        unregisterReceiver(broadcastReceiver2);
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }
}