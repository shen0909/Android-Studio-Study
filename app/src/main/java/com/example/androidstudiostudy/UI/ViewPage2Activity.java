package com.example.androidstudiostudy.UI;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidstudiostudy.R;
import com.example.androidstudiostudy.adapter.MyViewPage2Adapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPage2Activity extends AppCompatActivity {

    // 1.添加依赖
    // 2.在xml布局中引入 viewpage2
    // 3.利用集合存入图片,准备数据
    // 4.创建适配器，完成资源配置
    // 5.为 ViewPage2 设置适配器
    List<Integer> photos = new ArrayList<>();
    List<String> content = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_viewpage2);

        // 数据源
        photos.add(R.mipmap.star);
        photos.add(R.mipmap.study);
        photos.add(R.mipmap.star);
        photos.add(R.mipmap.study);

        content.add("1");
        content.add("2");
        content.add("3");
        content.add("4");
        // 找到 ViewPager2 控件
        ViewPager2 viewPager2 = findViewById(R.id.vp2);
        // 实例化适配器 - MyViewPage2Adapter
        RecyclerView.Adapter adapter = new MyViewPage2Adapter(photos,content);
        viewPager2.setAdapter(adapter);
    }
}
