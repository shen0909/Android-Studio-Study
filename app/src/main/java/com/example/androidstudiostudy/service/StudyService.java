package com.example.androidstudiostudy.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidstudiostudy.R;

public class StudyService extends AppCompatActivity {

    private ServiceConnection serviceConnection;
    private OneService myService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_service);

        serviceConnection = new ServiceConnection() {
            // 绑定成功时调用
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d("绑定服务","成功绑定服务");
                OneService.OneServiceBinder oneServiceBinder = (OneService.OneServiceBinder) iBinder;
                myService = oneServiceBinder.getService();
            }
            // Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d("绑定服务","与服务的连接意外中断");
                myService = null;
            }
        };

    }

    public void serviceAction(View view) {
        int id = view.getId();
        Intent intent = new Intent(this,OneService.class);
        if(id == R.id.bindService){
            // 绑定service
            bindService(intent,serviceConnection,Service.BIND_AUTO_CREATE);
            if (myService != null) {
                // 通过绑定服务传递的Binder对象，获取Service暴露出来的数据

                Log.d("获取绑定数据", "从服务端获取数据：" + myService.getCount());
            } else {

                Log.d("获取绑定数据", "还没绑定呢，先绑定,无法从服务端获取数据");
            }
        } else if (id == R.id.stopService) {
            stopService(intent);
        } else {
            startService(intent);
        }
    }
}