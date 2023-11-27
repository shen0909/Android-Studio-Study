package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        /* Java知识补充-内部类
         **定义在类的内部的类
         * -匿名内部类 常作为参数传递给方法
         **本质是一个子类，new的是一个类，在定义时会立即创建一个子类对象，根据多态性，可以将这个子类对象交给父类变量使用
         **如果new的是一个接口，那就会创建一个实现类对象交给接口变量使用 */
        /* 点击事件：被点击时触发的事件  */
        // 一、通过自定义内部类实现点击事件（当有多个相似的点击事件时适用）
        // 1.根据id找到按钮
        Button button1 = findViewById(R.id.btn_1);
        // 2.添加事件监听器 -> OnClickListener 对象，这个监听器是一个接口
        MyClickListener onc1 = new MyClickListener();
        button1.setOnClickListener(onc1);

        //二、匿名内部类 - 当有唯一操作的按钮时通常使用这种方法
        Button button2 = findViewById(R.id.btn_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("btn_niming_tags","====通过匿名内部类实现点击方法=====");
            }
        });

        // 三、通过当前Activity实现点击事件接口,让当前的activity类继承 View.OnClickListener 接口并实现里面的方法
        Button button3 = findViewById(R.id.btn_3);
        /* setOnClickListener 需要一个 OnClickListener 对象 作为参数，this可以指向本类对象，本类就是一个继承View.OnClickListener 接口的类*/
        button3.setOnClickListener(this);

    }

    // 让当前的activity类继承 View.OnClickListener 接口并实现里面的方法
    @Override
    public void onClick(View v) {
        Log.e("activity","====通过当前activity实现事件接口=====");
    }


    // 四、在xml布局文件中绑定添加点击事件属性
    /* 在Button的onclick属性中定义一个方法名，并在对应的activity中实现该方法
     * 参数 View view 表示被点击的控件对象，可以获取当前点击视图的id,根据id的不同实现不同的方法*/
    public void myClick(View view) {
        // 常规
        // Log.e("xml","====在xml布局文件中绑定添加点击事件属性=====");
        if(view.getId() == R.id.btn4){
            Log.e("xml_viewId4","====点击btn_4=====");
        }else if(view.getId() == R.id.btn5){
            Log.e("xml_viewId5","====点击btn_5=====");
        }
    }

    // 页面跳转方法
    public void jumoActivity(View view) {
        Intent intent = new Intent(ButtonActivity.this,MainActivity.class);
        startActivity(intent);
    }

    // 自定义一个 继承 View.OnClickListener 接口的类，实现里面的方法
    static class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 在控制台输入语句 Log.e 输入错误类型，也就是红色语句，不管错误与否
            Log.e("btn_tags","通过自定义内部类实现点击方法");
        }
    }
}