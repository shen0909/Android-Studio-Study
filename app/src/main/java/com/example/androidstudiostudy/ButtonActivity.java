package com.example.androidstudiostudy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {

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
                Log.e("btn_niming_tags", "====通过匿名内部类实现点击方法=====");
            }
        });

        // 三、通过当前Activity实现点击事件接口,让当前的activity类继承 View.OnClickListener 接口并实现里面的方法
        Button button3 = findViewById(R.id.btn_3);
        /* setOnClickListener 需要一个 OnClickListener 对象 作为参数，this可以指向本类对象，本类就是一个继承View.OnClickListener 接口的类*/
        button3.setOnClickListener(this);
        // 为按钮设置上下文操作模式
        //1. 实现接口 ActonMode.CallBack 中不同的回调方法
        //2. 在View的长按事件中启动上下文操作模式
        findViewById(R.id.ctx_opbtn).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActionMode(ac);
                return false;
            }
        });

        // 将弹出式菜单绑定在按钮的点击事件上
        Button popbtn = findViewById(R.id.pop_btn);
        popbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1.实例化 PopupMenu 对象
                /* 参数一 上下文环境
                 * 参数二 被锚定的(绑定)的button*/
                PopupMenu popupMenu = new PopupMenu(ButtonActivity.this, popbtn);
                // 2.加载菜单资源：利用MenuInflater将Menu资源加载到PopMenu.getMenu() 所返回的Menu对象中
                popupMenu.getMenuInflater().inflate(R.menu.context, popupMenu.getMenu());
                // 3.菜单子项点击操作
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(ButtonActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                // 显示菜单
                popupMenu.show();
            }
        });
    }

    ActionMode.Callback ac = new ActionMode.Callback() {
        // 创建：在启动上下文操作模式(startActionMode(Callback))时调用
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.e("上下文操作模式", "--创建--");
            getMenuInflater().inflate(R.menu.context, menu);
            return true;
        }

        // 准备：在创建方法后进行调用
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.e("上下文操作模式", "--准备--");
            return false;
        }

        // 菜单项被点击
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            /* 环境变量此时需要这样是因为这是一个内部类 ButtonActivity.this */
            Log.e("上下文操作模式", "--点击--");
            Toast.makeText(ButtonActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }

        // 结束：上下文操作模式结束时调用
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.e("上下文操作模式", "--结束--");
        }
    };

    // 让当前的activity类继承 View.OnClickListener 接口并实现里面的方法
    @Override
    public void onClick(View v) {
        Log.e("activity", "====通过当前activity实现事件接口=====");
    }


    // 四、在xml布局文件中绑定添加点击事件属性
    /* 在Button的onclick属性中定义一个方法名，并在对应的activity中实现该方法
     * 参数 View view 表示被点击的控件对象，可以获取当前点击视图的id,根据id的不同实现不同的方法*/
    public void myClick(View view) {
        // 常规
        // Log.e("xml","====在xml布局文件中绑定添加点击事件属性=====");
        if (view.getId() == R.id.btn4) {
            Log.e("xml_viewId4", "====点击btn_4=====");
        } else if (view.getId() == R.id.btn5) {
            Log.e("xml_viewId5", "====点击btn_5=====");
        }
    }

    // 页面跳转方法
    public void jumoActivity(View view) {
        Intent intent = new Intent(ButtonActivity.this, ConstraintActivity.class);
        startActivity(intent);
    }

    //点击弹出对话框方法
    public void popAlert(View view) {
        int id = view.getId();
        // 弹出普通对话框
        if (id == R.id.alt_btn1) {
            // 方法一：用构建器创建对话框：先创建对话框再显示
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示").setMessage("您确定要退出程序吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).setNegativeButton("取消", null).show();
        }
        // 弹出自定义对话框方法
        else if (id == R.id.alt_btn2) {
            // 自定义对话框-4.实例化对话框并显示
            /* 传入当前环境变量和设置的样式id*/
            MyDialog myDialog = new MyDialog(this,R.style.Mydialog);
            myDialog.show();
        }
        // 点击弹出弹窗
        else if (id == R.id.popw_btn) {
            showPopupWindow(view);
        }
    }

    //设置PopupWindow
    public void showPopupWindow(View anchorView){

        // 1.实例化 PopupWindow 对象
        /* 参数一 用在弹窗中的View(注意不是布局资源id,所以要根据布局资源id转换成view)
         * 参数二/三 长宽
         * 参数四 是否获取焦点*/
        // 1.1 准备弹窗需要的视图
        /* 根据 id = R.layout.popuop_layout 布局文件创建一个视图View,在 环境上下文 中使用*/
        /* 布局文件xml和视图之间的关系
         * 布局文件(.xml文件)定义了UI元素的结构和外观，而View对象则代表了布局中的一个具体的UI元素。
         * 通过使用LayoutInflater类，我们可以将布局文件解析成一个View对象，然后将布局文件转换为实际可见的 UI 元素
         * 通过指定上下文(this)来获取当前上下文环境的LayoutInflater实例
         * nflate(R.layout.popuop_layout, null)是LayoutInflater类的方法，它用于将布局文件R.layout.popuop_layout转换为一个View对象。*/
        View v = LayoutInflater.from(this).inflate(R.layout.popuop_layout,null);
        PopupWindow popupWindow = new PopupWindow(v,400,70,false);

        // 2.设置
        // 2.1 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 透明色
        // 2.2 设置能响应外部的点击事件
        popupWindow.setOutsideTouchable(true);
        // 2.3 设置弹窗能响应点击事件
        popupWindow.setTouchable(true);
        // 2.4 为弹窗中的文本设计点击事件
        /* 一般设计点击事件需要用 findViewById() 去找到控件的id,从而找到控件，再为控件绑定点击事件
         * 但此时我们想要找的控件不在当前视图中,它在 R.layout.popuop_layout 布局中，所以我们要在由它创建的视图中找控件
         * 当前activity绑定的布局是 R.layout.activity_button */
        v.findViewById(R.id.sele).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ButtonActivity.this,"点击了选择",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

        v.findViewById(R.id.all_sele).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ButtonActivity.this,"点击了全选",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

        v.findViewById(R.id.cope).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ButtonActivity.this,"点击了复制",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

        // 3.显示
        /* View anchor, int xoff, int yoff
         * 参数一 在哪里显示
         * x偏移
         * y偏移*/
        popupWindow.showAsDropDown(anchorView);
    }

    // 自定义一个 继承 View.OnClickListener 接口的类，实现里面的方法
    static class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 在控制台输入语句 Log.e 输入错误类型，也就是红色语句，不管错误与否
            Log.e("btn_tags", "通过自定义内部类实现点击方法");
        }
    }
}