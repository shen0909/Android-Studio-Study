package com.example.androidstudiostudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

//创建 Service 的子类（或使用它的一个现有子类如IntentService）,重写一些回调方法
public class OneService extends Service {
    public OneService() {
    }

    // 当另一个组件想通过调用 bindService() 与服务绑定（例如执行 RPC）时，系统将调用此方法。
    // 在此方法的实现中，必须返回 一个IBinder 接口的实现类，供客户端用来与服务进行通信。
    // 无论是启动状态还是绑定状态，此方法必须重写，但在启动状态的情况下直接返回 null。
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或onBind() 之前）。
    // 如果服务已在运行，则不会调用此方法，该方法只调用一次
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("服务","首次创建服务调用此方法来执行一次性设置程序,该方法只调用一次");
    }

    // 当另一个组件（如 Activity）通过调用 startService() 请求启动服务时，系统将调用此方法。
    // 一旦执行此方法，服务即会启动并可在后台无限期运行。 如果自己实现此方法，则需要在服务工作完成后，通过调用 stopSelf() 或 stopService() 来停止服务。
    // （在绑定状态下，无需实现此方法。）
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("服务","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    // 当服务不再使用且将被销毁时，系统将调用此方法。服务应该实现此方法来清理所有资源，如线程、注册的侦听器、接收器等，这是服务接收的最后一个调用。
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("服务","销毁服务");
    }
}