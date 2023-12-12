package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        Intent data = getIntent();
        // 获取上一个页面传过来的json数据
        String jsonData = data.getStringExtra("jsonData");

        TextView textView2 = findViewById(R.id.json2);
        TextView textView3 = findViewById(R.id.json3);
        TextView textView4 = findViewById(R.id.json4);

        // 对 json 数据进行解析
        try {
            /* 1. 创建 JSONObject 对象，传入满足 json 格式的字符串*/
            JSONObject jsonObject = new JSONObject(jsonData);

            /* 2. 根据 json数据的key键值 获取其中的数据,是什么类型的数据就写getxx()*/
            String Jsonspage = jsonObject.getString("page");
            String Jsonstotal = jsonObject.getString("total");
            textView3.setText("page="+Jsonspage);
            textView4.setText("total="+Jsonstotal);

            // data 里面是一个 json 对象，获取 data 里面的json对象,由于此时的链接 data 中是数组，所以只做展示
            /*JSONObject Jsonsdata = jsonObject.getJSONObject("data");
            String text1 = Jsonsdata.getString("email");
            textView2.setText(text1);
            */

            // data 里面是 json 数组，数组里面放的是多个 json 对象
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i); // 获取数组中的第 i 个对象
                String text1 = jo.getString("email");
                Log.e("第"+(i+1)+"个邮箱",text1);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}