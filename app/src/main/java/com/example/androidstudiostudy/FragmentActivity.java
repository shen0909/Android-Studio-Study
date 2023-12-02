package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

// 创建一个新的activity 绑定布局 R.layout.activity_fragment ,在该布局里添加 fragment 控件，进行展示
public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

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
}