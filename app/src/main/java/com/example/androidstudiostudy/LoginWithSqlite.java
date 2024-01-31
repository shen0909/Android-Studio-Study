package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidstudiostudy.adapter.recyclerViewAdapter;
import com.example.androidstudiostudy.data.UserBean;

import java.util.ArrayList;

public class LoginWithSqlite extends AppCompatActivity {

    EditText name, phone, passwaord;
    Button add, delete,change;
    ArrayList<UserBean> items = new ArrayList<>();
    DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginWithSqlite.this);
    RecyclerView recyclerView;
    recyclerViewAdapter adapter;
    int i = -1; // 默认当前显示的数据在数据库中的id
    UserBean currentUserBean; // 当前显示的userbean
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_sqlite);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        change = findViewById(R.id.change);
        name = findViewById(R.id.nameTxt);
        phone = findViewById(R.id.phoneTxt);
        passwaord = findViewById(R.id.pwdTxt);
        recyclerView = findViewById(R.id.recycleView11);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        initRecycleView();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserBean userBean = new UserBean(name.getText().toString(), passwaord.getText().toString(), phone.getText().toString());
                boolean success = dataBaseHelper.addOne(userBean);
                if (success) {
                    Toast.makeText(LoginWithSqlite.this, userBean.getName() + userBean.getPhone() + userBean.getPassWord(), Toast.LENGTH_LONG).show();
                    initRecycleView();
                } else {
                    Toast.makeText(LoginWithSqlite.this, "添加失败", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i != -1){
                    boolean success = dataBaseHelper.deleteOne(currentUserBean);
                    if(success){
                        Toast.makeText(LoginWithSqlite.this, "删除成功", Toast.LENGTH_LONG).show();
                        initRecycleView();
                    }else{
                        Toast.makeText(LoginWithSqlite.this, "删除失败", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LoginWithSqlite.this, "当前没有数据可删除", Toast.LENGTH_SHORT).show();
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i != -1) {
                    boolean success = dataBaseHelper.updataInfo(currentUserBean);
                    if (success) {
                        Toast.makeText(LoginWithSqlite.this, "修改成功", Toast.LENGTH_LONG).show();
                        initRecycleView();
                    } else {
                        Toast.makeText(LoginWithSqlite.this, "修改失败", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LoginWithSqlite.this, "当前没有数据可修改", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //声明一个自定义广播接收器的实例对象
    private MyBroadcastRecevier myBroadcastRecevier;
    @Override
    // 注册广播接收器一般在onStart和onResume方法中注册
    protected void onStart() {
        super.onStart();
        // 创建一个广播接收器
        myBroadcastRecevier = new MyBroadcastRecevier();
        // 创建一个意图过滤器，只处理指定事件来源的广播
        IntentFilter intentFilter = new IntentFilter(recyclerViewAdapter.EVENT);
        // 注册广播接收器，注册之后才能正常接收广播
        LocalBroadcastManager.getInstance(this).registerReceiver(myBroadcastRecevier,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myBroadcastRecevier);
    }

    private void initRecycleView() {
        items = dataBaseHelper.getList();
        adapter = new recyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);
    }


    public class MyBroadcastRecevier extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null){
                currentUserBean = (UserBean) intent.getSerializableExtra("checkView");
                name.setText(currentUserBean.getName());
                phone.setText(currentUserBean.getPhone());
                passwaord.setText(currentUserBean.getPassWord());
                i = currentUserBean.getId();
                Toast.makeText(context, "广播接收器接收到了"+currentUserBean.getName()+currentUserBean.getPhone(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}