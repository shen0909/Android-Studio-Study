package com.example.androidstudiostudy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.androidstudiostudy.data.Student;

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

        // 获取上一个页面传递过来的数据,获取数据时有些需要给出默认值
        Intent getIntent = getIntent();
        String dataString = getIntent.getStringExtra("传递的String类型参数");
        int dataInt = getIntent.getIntExtra("传递的int类型参数",1);
        double dataDouble = getIntent.getDoubleExtra("传递的double类型参数",2.1);
        boolean dataBool = getIntent.getBooleanExtra("传递的bool类型参数",true);

        TextView textView = findViewById(R.id.show);
        textView.setText("上一个页面传递是数据"+dataString+dataInt+dataDouble+dataBool);

        // 获取对象数据 - 强转成 Student 对象
        Student student = (Student) getIntent.getSerializableExtra("data_object");

        if (student != null) {
            TextView textView2 = findViewById(R.id.show2);
            textView2.setText("上一个页面传递是数据"+student.getName()+student.getAge()+student.getMoney()+student.isCheck());
        } else {
            // 处理student对象为空的情况，比如给出一个默认值或者显示错误信息
            TextView textView2 = findViewById(R.id.show2);
            textView2.setText("上一个页面未传递有效的Student对象");
        }

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
        // 打开界面对话框
        /* 1.创建一个 activity，和创建意图跳转
         * 2.在清单文件中设置当前activity的风格为对话框风格*/
        else if (id == R.id.btt3) {
            Intent intent2 = new Intent(this,DialogActivity.class);
            startActivity(intent2);
        }
        // 跳转去看fragment
        else if (id == R.id.toLook_fragment) {
            Intent intent = new Intent(this, FragmentActivity.class);
            startActivity(intent);
        }

    }

    // 通过startActivityForResult启动activity-返回结果
    public void backRes(View view) {
        // 设置结果
        Intent intent = new Intent();  //此时intent不作为跳转使用，而是用来传递返回的数据
        intent.putExtra("返回的数据","第二个界面返回的是10000000");
        /*参数1：请求码 参数2：返回的数据*/
        setResult(RESULT_OK,intent);
        finish();
    }
}