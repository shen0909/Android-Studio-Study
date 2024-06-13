package com.example.androidstudiostudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

// 创建一个新的activity 绑定布局 R.layout.activity_fragment ,在该布局里添加 fragment 控件，进行展示
public class FragmentActivity extends AppCompatActivity implements Fragment2.CommitData{
    Handler oneHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        oneHandle = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                System.out.println("Activity Fragment使用Handle传递数据");
                TextView textView = findViewById(R.id.a_f_handle);
                textView.setText("传递了:"+msg.what);
                return false;
            }
        });
        // 用Java代码添加fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 使用add()方法添加一个fragment片段
        Fragment2 fragment2 = Fragment2.newInstance(null,null);
        fragmentTransaction.add(R.id.fragment2,fragment2);
        fragmentTransaction.commit();
    }

    // FragmentTransaction.replace() 方法替换 Fragment
    // 参数1：containerViewId 应放置片段的位置
    // 参数2：要替换的片段
    public void replaceFragment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment1 newFragment = new Fragment1();
        newFragment.setOneHandle(this.oneHandle);
        fragmentTransaction.replace(R.id.fragment2,newFragment);
        fragmentTransaction.commit();
    }

    // Activity 向 Fragment 传值
    // 法一.通过Bundle来传递参数
    public void commit(View view) {
        /* 注意！！ FragmentManager 和 FragmentTransaction 不能变成全局变量，会报错 */
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 1.实例化一个 fragment 对象,注意这里实例化的不是
        Fragment2 f2 = new Fragment2();
        // 2.实例化一个Bundle对象
        Bundle bundle = new Bundle();
        // 3.存入数据到Bundle对象中
        bundle.putString("AtoF1","这是activity向fragment传递的第一个消息");
        // 4.调用Fragment 的 setArgument方法，传入 Bundle 对象
        f2.setArguments(bundle);
        // 5.添加或者替换显示的Fragment
        fragmentTransaction.replace(R.id.fragment2,f2);
        fragmentTransaction.commit();
    }

    // 法二.利用context环境上下文 和 onAttach ，在 Fragment1中获取数据
    /* 在 Activity 和 Fragment 建立关系的时候，onAttach 方法会得到环境上下文 context，根据这个context可以获取宿主activity的方法和变量*/
    public String getTitles(){
        return "这是通过环境上下文 和 onAttach 进行传值的";
    }

    @Override
    // 2.让 接收数据的activity实现该接口，然后重写回调方法，目的：获取传入的值并做处理
    public void sedMSG(String msg) {
        TextView textView = findViewById(R.id.showData);
        textView.setText("传回的数据："+msg);
    }
}