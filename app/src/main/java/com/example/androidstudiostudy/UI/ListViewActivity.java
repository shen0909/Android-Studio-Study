package com.example.androidstudiostudy.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.androidstudiostudy.adapter.MyBaseAdapter;
import com.example.androidstudiostudy.R;
import com.example.androidstudiostudy.data.BaseMsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    // 准备数据源
    private List<Map<String, Object>> data = new ArrayList<>();

    BaseAdapter baseAdapter; // 将 BaseAdapter 设置成全局变量
    ListView listView; // 将 ListView 也设置成全局变量
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
        listView = findViewById(R.id.list1);
        String[] datas = {"aaa", "bbb", "ccc", "ddd", "eee", "fff"};
        if (id == R.id.buttonArray) {
            // 实例化适配器
            /* 这是数组适配器的第三种构造方法，可以在单项里放置许多其他内容，此时不会因为根布局不是TextView而报错*/
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.array_item_layout, R.id.arry_tx, datas);
            listView.setAdapter(arrayAdapter);
        } else if (id == R.id.buttonSimple) {
            // 准备数据源
            initData();
            // 实例化simpleAdapter适配器
            /* 参数1：环境上下文
             * 参数2：数据源
             * 参数3：每一项布局  R.layout.simpleadapeter_item
             * 参数4：数据来源的key数组
             * 参数5：数据去向的id数组
             * key所指代的数据会在to数组中id所代表的空间上显示出来
             * */
            String[] from = {"icon", "name", "age"}; // 参数4
            int[] to = {R.id.headIcon, R.id.itemName, R.id.itemAge}; // 参数5
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.simpleadapeter_item, from, to);
            listView.setAdapter(simpleAdapter);

            // 为listView设置item点击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    /* 数据源data是一个List<Map<String,Object>>的列表，
                     * onItemClick方法，返回的position就是当前点击的 item 的位序，通过它可以拿到item的数据源
                     * view 就是点击的item的视图*/
                    Map<String, Object> data1 = data.get(position);
                    Toast.makeText(ListViewActivity.this, "姓名" + data1.get("name") + ",年龄：" + data1.get("age"), Toast.LENGTH_SHORT).show();
                }
            });
        }
        // 跳转 BaseAdapter
        else if (id == R.id.buttonBase) {
            // 实例化适配器
            /* 1.准备布局（ListView每一项的显示样式布局）
             * 2.准备数据源
             * 3.实例化适配器
             * 4.为ListView设置适配器*/
            // 准备数据源
            initBaseListData();
            baseAdapter = new MyBaseAdapter(lists,this);
            listView.setAdapter(baseAdapter);
        }
    }

    private void initData() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("icon", R.mipmap.star);
        data1.put("name", "沈成林");
        data1.put("age", 23);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("icon", R.mipmap.star);
        data2.put("name", "杨滨溶");
        data2.put("age", 23);

        Map<String, Object> data3 = new HashMap<>();
        data3.put("icon", R.mipmap.star);
        data3.put("name", "朱一龙");
        data3.put("age", 23);

        data.add(data1);
        data.add(data2);
        data.add(data3);
    }


    private List<BaseMsg> lists = new ArrayList<>();  // 定义一个 BaseMsg 对象的列表
    private int[] touxiang ={R.mipmap.star,R.mipmap.study,R.mipmap.star,R.mipmap.study,R.mipmap.star,R.mipmap.study,R.mipmap.star,R.mipmap.study,}; // 定义一个int类型的数组，里面存放 图标的id

    // 初始化 BaseMsg 对象的列表
    public void initBaseListData() {
        for (int i = 0; i < 8; i++) {
            BaseMsg baseMsg = new BaseMsg(touxiang[i], "用户" + (i+1), "这是第" + (i+1) + "段代码", false);
            lists.add(baseMsg);
        }
    }

    // 动态添加 BaseAdapter 数据源中的数据
    public void addData(View view) {
        lists.add(new BaseMsg(R.mipmap.ic_launcher,"新name","这是新增的",false));
        // 通知适配器更新
        baseAdapter.notifyDataSetChanged();
        // 设置listView自动显示到最新的数据
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
    }
}