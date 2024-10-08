package com.example.androidstudiostudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // AppCompatActivity 继承了 Activity 类，拥有了窗口的特性，是一个可视化界面
    // MainActivity是一个可视化界面正是由于它继承了 AppCompatActivity
    // onCreate 来源于父类，它相对于activity而言就相当于 main 方法对于普通类
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置内容布局(视图)
        // setContentView 要求传入 布局id
        // setContentView(R.layout.chatting_layout);
        setContentView(R.layout.activity_main);

        /* 用代码定义布局*/
       /* // 1.定义布局
        LinearLayout linearLayout = new LinearLayout(this);
        // 2.设置宽高
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        // 3.设置背景颜色
        linearLayout.setBackgroundColor(Color.GREEN);
        setContentView(linearLayout);*/

        // ContextMenu 上下文菜单的使用
        // 1.注册
        registerForContextMenu(findViewById(R.id.ctx_btn));
        // 2.创建 - 覆盖父类的 onCreateContextMenu 方法
        // 3。菜单项的操作 - 覆盖父类的 onContextItemSelected 方法
    }

    // 2.创建contextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context,menu);
    }

    // 3.contextMenu 菜单项选项操作
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    // 创建optionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载菜单资源
        /* 通过xml设计 */
        // getMenuInflater().inflate(R.menu.option,menu);
        /* 纯代码设计
         * Menu 类 SubMenu 子菜单类*/
        /* Menu 类参数：组id 菜单项 id 菜单项序号 菜单项名称 */
        menu.add(1,1,3,"菜单1");
        menu.add(1,2,2,"菜单2");
        SubMenu subMenu = menu.addSubMenu(1,3,1,"子菜单的主菜单");
        subMenu.add(2,22,1,"子菜单2");
        subMenu.add(2,11,2,"子菜单1");
        return true;
    }

    // optionMenu 的选中方法
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.m1) {
        if (item.getItemId() == 1) {
            Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.m2) {
            Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    // 注册方法
    /* 1. 判断姓名密码输入内容是否为空
     *   是 则提示
     *   否 出现进度条运转*/
    public void register(View view) {
        /* EditText 继承自 TextView  getText()方法获取文本
         * 此时获取到的是可编辑的文本，需要将它转换成String类型的文本*/
        ProgressBar progressBar = findViewById(R.id.reg_prb);
        EditText numberEt = findViewById(R.id.number);
        EditText pwdEt = findViewById(R.id.password);
        EditText phoneEt = findViewById(R.id.phone);
        String number = numberEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        String phone = phoneEt.getText().toString();
        if( number.equals("") || pwd.equals("")){
            // 采用无焦点提示
            /* 参数
             * Context 环境上下文 就是当前的activity
             * CharSequence 提示文本
             * duration 持续时间 */
            Toast.makeText(this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
        }else {
            // 设置进度条可见
            progressBar.setVisibility(View.VISIBLE);
            new Thread(){
                public void run(){
                    for (int i = 1; i <=10 ; i++) {
                        progressBar.setProgress(i);
                        // 休眠一下
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }.start();
        }
    }

    public void jumpe(View view) {
        if(view.getId() == R.id.toLook_UIComponent ){
            Intent intent = new Intent(MainActivity.this,ComponentActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.jumpPage) {
            Intent intent = new Intent(MainActivity.this,ButtonActivity.class);
            startActivity(intent);
        }
    }

}