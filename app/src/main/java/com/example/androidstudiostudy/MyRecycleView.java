package com.example.androidstudiostudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MyRecycleView extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycle_view);
        LinearLayout linearLayout = new LinearLayout(this);
        recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // 设置适配器 重点！！！
        int[] colors = {R.color.p1, R.color.p2, R.color.p3, R.color.p4, R.color.p5, R.color.p6};
        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(colors);
        recyclerView.setAdapter(adapter);
    }
}