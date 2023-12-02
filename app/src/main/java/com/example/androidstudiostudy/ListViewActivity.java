package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
    }

    // 展示listView
    /* 1.准备布局（ListView每一项的显示样式布局）
     * 2.准备数据源
     * 3.实例化适配器
     * 4.为ListView设置适配器*/
    public void showListView(View view) {
        int id = view.getId();
        ListView listView = findViewById(R.id.list1);
        String[] datas = {"aaa","bbb","ccc","ddd","eee","fff"};
        if (id == R.id.buttonArray){
            // 实例化适配器
            /* 这是数组适配器的第三种构造方法，可以在单项里放置许多其他内容，此时不会因为根布局不是TextView而报错*/
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.array_item_layout,R.id.arry_tx,datas);
            listView.setAdapter(arrayAdapter);
        } else if (id == R.id.buttonSimple) {
        } else if (id == R.id.buttonBase) {
        }
    }
}