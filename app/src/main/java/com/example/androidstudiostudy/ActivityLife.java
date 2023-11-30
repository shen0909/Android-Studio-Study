package com.example.androidstudiostudy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActivityLife extends AppCompatActivity {

    /* 单个 activity 生命周期的变化
     * 1.正常启动：OnCreate() -> OnStart() -> OnResume()
     * 2.正常退出：OnPause() -> OnStop() -> OnDestory()
     * 3.再次启动：OnCreate() -> OnStart() -> OnResume()
     * 4.已经处于前台的activity，点击主页按钮离开当前activity，OnPause() -> OnStop() 回到当前activity OnStart() -> OnResume()
     * 5.屏幕熄屏或打开了其他activity，当前的activity由于优先级的原因被强行杀死了，再回到当前activity时：OnCreate() -> OnStart() -> OnResume()*/

    // 创建
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        Log.e("Life","onCreate--创建");
    }

    // 启动
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life","onStart--启动");
    }

    // 恢复
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life","onResume--恢复");
    }

    // 暂停
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life","onPause--暂停");
    }

    // 停止
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life","onStop--停止");
    }

    // 销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life","onDestroy--销毁");
    }

    // 重启
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Life","onRestart--重启");
    }

    /* 多个activity切换时，
     * 当启动另一个activity时，当前activity：OnPause() -> OnStop()
     * 当点击返回按钮，另一个activity退出时：OnCreate() -> OnStart() -> OnResume()*/
    // 页面跳转方法
    public void toJump(View view) {
        int id = view.getId();
        if (id== R.id.btt1) {
            Intent intent = new Intent(this,ConstraintActivity.class);
            startActivity(intent);
        }
        // 观察打开普通对话框生命周期的变化
        else if(id == R.id.btt2){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("对话框提示")
                    .setMessage("请认真观察生命周期的变化")
                    .setPositiveButton("确定",null)
                    .show();
        }

    }
}