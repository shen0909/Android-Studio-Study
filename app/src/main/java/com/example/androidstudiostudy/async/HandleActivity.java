package com.example.androidstudiostudy.async;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.androidstudiostudy.R;
import com.example.androidstudiostudy.data.DataBean;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HandleActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    String httpDate = "";

    // 利用 handller 处理
    // 1.实例化一个 Handler
    Handler handler = new Handler(
            // 只要 handle 发了消息，就一定会触发 handleMessage 方法，并传入一个 Message 对象
            new Handler.Callback() {
                @Override
                // 3.由handle对象接收消息并处理，在Handler的内部类中处理
                public boolean handleMessage(@NonNull Message msg) {
                    Log.e("handler 处理消息", "这是handler发送的消息，此时是空");

                    // 根据 Message 的 what 属性，区分来源于哪个线程
                    if (msg.what == 1) {
                        textView1 = findViewById(R.id.t1);
                        textView1.setText(httpDate);
                    } else if (msg.what == 2) {
                        textView2 = findViewById(R.id.t2);
                        textView2.setText(httpDate);
                    } else if (msg.what == 3) {
                        textView3 = findViewById(R.id.t3);
                        textView3.setText("msg.what = "+msg.what+"\n"+msg.obj.toString());
                    }
                    return false;
                }
            }
    );

    Handler handler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);

        // 开一个新的线程，用于接收主线程向子线程传递消息
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 在子线程中开辟 handler
                // 但此时会报错 Can't create handler inside thread that has not called Looper.prepare()
                /* 这是由于在一个未调用Looper.prepare()的线程内尝试创建 Handler对象导致的。
                 * 在Android中，Handler对象必须在主线程中创建，如果想要在子线程中创建Handler对象,
                 * 必须确保在创建Handler对象之前在线程中调用了Looper.prepare()方法。*/

                // 系统会自动为主线程开启消息循环（自动调用这句代码），与此同时创建一个 Looper 对象，不断的从 MessageQue中读取消息，交给主线程处理
                Looper.prepare(); // 准备开启一个消息循环

                handler2 = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        Log.e("主线程向子线程中发送消息", "" + msg.what + "" + msg.arg1);
                        return false;
                    }
                });

                // 但此时点击虽然不报错，可是里面的代码依旧没有被执行
                /* 因为准备好一个消息循环后还要开启一个消息循环 -----> Looper.loop();
                 * 消息循环期间，线程会一直阻塞在这里，等待处理来自消息队列的消息。
                 * 它会不断地从消息队列中取出消息，并将其分发给对应的Handler对象进行处理。*/
                Looper.loop(); //开始消息循环，相当于 while(true) ,在这里等待消息
            }
        }).start();
    }

    // 此时有两个线程，这两线程发送的消息都能被 Handle接收，但如何区分不同线程发送的消息从而做不同处理呢？
    public void getDate(View view) {
        int id = view.getId();
        // 第一个线程
        if (id == R.id.handle1) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    getHttp();
                    // 此时会报错：Only the original thread that created a view hierarchy can touch its views.
                    /*TextView textView = findViewById(R.id.t1);
                    textView.setText(httpDate);*/

                    // 解决办法 1 runOnUiThread 方法 ==> 相当于在主线程中跑 （初学阶段）
                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = findViewById(R.id.t1);
                            textView.setText(httpDate);
                        }
                    });*/
                    // 解决办法 2 利用 Handle 处理
                    // 2.在子线程中发送(空)消息，此时发送的是空消息
                    handler.sendEmptyMessage(1);
                }
            }.start();
        }
        // 第二个线程
        else if (id == R.id.handle2) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    getHttp();
                    httpDate = httpDate + "\n第2个线程";
                    handler.sendEmptyMessage(2);
                }
            }.start();
        }
        // 第三个线程
        else if (id == R.id.handle3) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    getHttp();
                    // 当 handler 需要传送数据时，需要使用到 Message，使用 sendMessage 方法
                    /* 1. 实例化一个 Message
                     * 2. 参数
                     ** what:用于区分 handler 发送消息的不同线程来源
                     ** arg1/arg2: 子线程需要向主线程传递整型参数
                     ** obj: Object 任意对象*/
                    Message message = new Message();
                    message.what = 3;

                    DataBean dataBean = new DataBean(1, "157181@qq.com", "ybr", "scl", "asiudh");
                    message.obj = dataBean;
                    handler.sendMessage(message);
                }
            }.start();
        }
        // 第四个线程- 主线程向子线程发送消息
        else if (id == R.id.handle4) {
            Message message2 = new Message();
            message2.what = 999;
            message2.arg1 = 1234;
            handler2.sendMessage(message2);
        }
    }

    // 网络操作
    private void getHttp() {
        try {
            // 1. 实例化一个 URL 对象
            URL url = new URL("https://reqres.in/api/users");
            // 2. 获取 HttpURLConnection 实例，使用URL的
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 3. 设置请求相关属性
            httpURLConnection.setRequestMethod("GET"); // 请求方法
            httpURLConnection.setConnectTimeout(6000); // 超时时间
            // 4. 获取响应码 200 成功 404 未请求到指定资源 500 服务器异常（此时已经发起请求）
            // 5. 判断响应码并获取响应数据（响应的正文）
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream(); // 获取了服务器返回的输入流
                byte[] bytes = new byte[1024]; // bytes 数组，每次最多可以存放 1024 个字节
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();// 存储从输入流中读取的数据
                int len = 0;
                while ((len = inputStream.read(bytes)) > -1) {
                    // 将字节数组中的内容写入到缓存流
                    /* 参数1：要存入的字节数组
                     * 参数2：起点
                     * 参数3：要存入的长度*/
                    byteArrayOutputStream.write(bytes, 0, len);
                }
                httpDate = new String(byteArrayOutputStream.toByteArray());
                Log.e("GET返回的数据", httpDate);
            }
        } catch (ProtocolException ex) {
            throw new RuntimeException(ex);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}