package com.example.androidstudiostudy.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

// 静态广播接收器
public class StaticBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d("BroadcastReceiver","静态广播接收器接受了一个 - "+bundle.getString("data"));
    }
}
