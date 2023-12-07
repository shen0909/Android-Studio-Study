package com.example.androidstudiostudy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.example.androidstudiostudy.data.Student;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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

    //点击弹出对话框按钮之后的按钮方法都在这
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
        // 点击含有数组适配器的弹窗
        else if (id == R.id.adpter_btn) {
            showArrayDialog();
        }
        // 跳转生命周期页
        else if (id == R.id.toShow_life) {
            Intent intent = new Intent(this,ActivityLife.class);
            // 添加参数
            intent.putExtra("传递的String类型参数","这是上一个页面传递过来的String类型参数");
            intent.putExtra("传递的double类型参数",24.99);
            intent.putExtra("传递的int类型参数",24);
            intent.putExtra("传递的bool类型参数",false);
            startActivity(intent);
        }
        // 隐式启动 - 打开系统activity
        else if (id == R.id.toOpen_sysAc) {
            /* 隐式启动的两种构造方法
             * public Intent(String action, Uri uri)
             * public Intent(String action)
             * action：Activity的别名 ，编译阶段无论写什么的都不会报错
             * uri: Uri对象，打开的路径*/
            Intent intentS = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
            startActivity(intentS);
        }
        // 隐式启动 - 打开普通activity
        else if (id == R.id.toOpen_comAc) {
            /* 给activity配置action*/
            Intent intentC = new Intent("activityName");
            // activity传递对象内容
            // 实例化一个新建的 Student 对象
            Student student1 = new Student("沈成林",23,200000.999,true);
            // 参数1：String name - 本次数据的名称
            // 参数2：@Nullable Serializable value - 序列化数据对象
            intentC.putExtra("data_object",student1);
            startActivity(intentC);
        }
        // startActivityForResult 带结果返回的方式启动 activity
        else if (id == R.id.toOpen_AcwithResult) {
            Intent intent = new Intent(this,ActivityLife.class);
            // 参数2：请求码
            startActivityForResult(intent,1000);
        }
        // 点击跳转去学校ListView
        else if (id == R.id.toLook_ListView) {
            startActivity(new Intent(this, ListViewActivity.class));
        }
        // 点击跳转去学习ViewPage2
        else if (id == R.id.toLook_ViewPage2) {
            startActivity(new Intent(this, ViewPage2Activity.class));
        }
        // 点击跳转去学习TabLayout 页面切换
        else if (id == R.id.toLook_TabLayout) {
            startActivity(new Intent(this,TabLayoutActivity.class));
        }
    }

    // 如果通过 startActivityForResult 启动了第二个activity，当第二个activity处理结束后，再回到当前activity时，一定会自动回调 onActivityResult 方法
    // 在 onActivityResult 方法中我们可以处理第二个activity返回的结果。（如。拍照后得到的照片，从图库中选取的图片）
    /* 参数1 requestCode：请求码。当有多个 startActivityForResult 时，可以用来判断该结果来自于哪个activity，从而进行什么操作
     * 参数2 resultCode：结果码 0 = RESULT_CANCEL->取消 | -1 = RESULT_OK  正确处理后返回。判断它是为了判断新开的activity有没有处理完这些事
     * 参数3 Intent (可以为空)：返回的结果存放在这里。通过 getStringExtra() 获取数据(此时已经知道结果是String类型的数据)*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 判断新开的activity返回的结果
        /* 当返回结果都是成功时，请求码==1000则进行操作 */
        if(resultCode == -1){
            if(requestCode == 1000){
                assert data != null;
                Log.e("ActivityWithResults","自动进入onActivityResult requestCode："+requestCode+",resultCode："+resultCode+"，返回的数据"+data.getStringExtra("返回的数据"));
            }
        }
    }

    // 包含数组适配器的对话框
    private void showArrayDialog() {
        final String[] items= {"java","c++","android","flutter","dart"};
        /* 创建一个对话框构造对象 AlertDialog.Builder*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        /* 创建一个数组适配器 - 这里使用的是第三个构造方法
         * 参数1 Context：环境上下文
         * 参数2 resource：布局资源索引 每一项数据显示的样式布局 android.R.layout.xx 要求该布局资源的根元素必须是TextView
         * 参数3 objects：数据源*/
        // ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,items);

        /* R.layout.array_item_layout 布局是自己设置的布局，根元素不是TextView，因此出错，所以此时需要另一种构造方法
         * 参数1 Context：环境上下文
         * 参数2 resource：布局资源索引
         * 参数3 int textViewResourceId：指定数据中文本需要放在布局中的文本控件的id
         * 参数4 objects：数据源*/
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.array_item_layout,R.id.arry_tx,items);
        builder.setTitle("请选择")
                /* .setAdapter() 设置适配器
                 * 参数1 - ListAdapter 适配器对象(对数据显示样式的规则制定器)：The ListAdapter to supply the list of items
                 * 参数2 - OnClickListener 监听器 (可给可不给，不给则设为null)*/
               .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                   @Override
                   /*int which 当前点击的item索引*/
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(ButtonActivity.this,items[which],Toast.LENGTH_SHORT).show();
                   }
               })
               .show();

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
         * inflate(R.layout.popuop_layout, null)是LayoutInflater类的方法，它用于将布局文件R.layout.popuop_layout转换为一个View对象。*/
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

        // 设置弹窗动画
        /* 1.创建动画资源 -- 先创建文件夹 再创建资源文件
         * 2.创建风格，应用动画资源
         * 3.将当前弹窗的的动画风格设置为第2步的风格 */
        popupWindow.setAnimationStyle(R.style.popupStyle);


        // 3.显示
        /* View anchor, int xoff, int yoff
         * 参数一 在哪里显示
         * x偏移
         * y偏移*/
        popupWindow.showAsDropDown(anchorView);
    }

    // 网络操作方法
    public void netWork(View view) {
        int id = view.getId();
        // get 请求
        if(id == R.id.netWork_GET){
            // 请求网络不能在主线程中进行,所以要开一个新的线程
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    GetHttp();
                }
            }.start();
        }
        // post 请求
        else if (id == R.id.netWork_POST) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    PostHttp();
                }
            }.start();
        }
    }
    // POST 请求方法
    private void PostHttp() {
        try {
            // 1. 实例化一个 URL 对象
            URL url = new URL("https://www.wanandroid.com/user/login");

            // 2. 获取 HttpURLConnection 实例，使用URL的
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // 3. 设置请求相关属性
            httpURLConnection.setRequestMethod("POST"); // 请求方法
            httpURLConnection.setConnectTimeout(6000); // 超时时间
            httpURLConnection.setDoOutput(true); // 设置允许输出
            httpURLConnection.setRequestProperty("Content-Type","application/json;Charset=UTF-8");// 设置提交数据的类型

            // 4.获取输出流（请求正文）
            OutputStream out = httpURLConnection.getOutputStream();
            // 5.写数据
            out.write(("username="+"akshfalwhfaina"+"&password="+"123456").getBytes());

            if( httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                // 6.判断响应码并获取响应数据（响应的正文）
                InputStream inputStream = httpURLConnection.getInputStream(); // 获取了服务器返回的输入流
                byte[] bytes = new byte[1024]; // bytes 数组，每次最多可以存放 1024 个字节
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 存储从输入流中读取的数据
                int len = 0;
                // 输入流中读取数据，然后将数据写入ByteArrayOutputStream中
                /* inputStream.read(bytes) ：从输入流中读取数据，并将读取的内容存储到bytes数组中
                 * 这个方法的返回值是读取的字节数，如果已经到达流的末尾，它会返回-1
                 * len = inputStream.read(bytes) 是用来判断是否已经读到了末尾*/
                while((len = inputStream.read(bytes)) >-1){
                    // 将字节数组中的内容写入到缓存流
                    /* 参数1：要存入的字节数组
                     * 参数2：起点
                     * 参数3：要存入的长度*/
                    byteArrayOutputStream.write(bytes,0,len);
                }
                String s = new String(byteArrayOutputStream.toByteArray()) ;
                Log.e("POST_NetWork",s);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // get 请求方法
    public void GetHttp(){
        try {
            // 1. 实例化一个 URL 对象
            URL url = new URL("https://reqres.in/api/users");

            // 2. 获取 HttpURLConnection 实例，使用URL的
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // 3. 设置请求相关属性
            httpURLConnection.setRequestMethod("GET"); // 请求方法
            httpURLConnection.setConnectTimeout(6000); // 超时时间

            // 4. 获取响应码 200 成功 404 未请求到指定资源 500 服务器异常（此时已经发起请求）
            if( httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                // 5.判断响应码并获取响应数据（响应的正文）
                InputStream inputStream = httpURLConnection.getInputStream(); // 获取了服务器返回的输入流
                byte[] bytes = new byte[1024]; // bytes 数组，每次最多可以存放 1024 个字节
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 存储从输入流中读取的数据
                int len = 0;
                // 输入流中读取数据，然后将数据写入ByteArrayOutputStream中
                /* inputStream.read(bytes) ：从输入流中读取数据，并将读取的内容存储到bytes数组中
                 * 这个方法的返回值是读取的字节数，如果已经到达流的末尾，它会返回-1
                 * len = inputStream.read(bytes) 是用来判断是否已经读到了末尾*/
                while((len = inputStream.read(bytes)) >-1){
                    // 将字节数组中的内容写入到缓存流
                    /* 参数1：要存入的字节数组
                     * 参数2：起点
                     * 参数3：要存入的长度*/
                    byteArrayOutputStream.write(bytes,0,len);
                }
                String s = new String(byteArrayOutputStream.toByteArray()) ;
                Log.e("GET_NetWork",s);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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