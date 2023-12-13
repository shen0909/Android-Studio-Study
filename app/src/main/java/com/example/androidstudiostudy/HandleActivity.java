package com.example.androidstudiostudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HandleActivity extends AppCompatActivity {

    TextView textView ;
    String httpDate = "";

    // 利用 handller 处理
    // 1.实例化一个 Handler
    Handler handler = new Handler(
            new Handler.Callback() {
                @Override
                // 3.由handle对象接收消息并处理，在Handler的内部类中处理
                public boolean handleMessage(@NonNull Message msg) {
                    Log.e("handler 处理消息", "这是handler发送的消息，此时是空");
                    textView = findViewById(R.id.t1);
                    textView.setText(httpDate);
                    return false;
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);
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
                    handler.sendEmptyMessage(1);
                }
            }.start();
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