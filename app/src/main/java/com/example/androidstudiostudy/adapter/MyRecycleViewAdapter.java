package com.example.androidstudiostudy.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstudiostudy.R;

// 创建 RecyclerView适配器
/* 1. 新建一个类继承 RecyclerView.Adapter<VH>的Adapter类（VH是ViewHolder的类名） ---- MyRecycleViewAdapter
 * 2. 创建ViewHolder：在Adapter中创建一个继承RecyclerView.ViewHolder的静态内部类，记为VH。ViewHolder的实现和ListView的ViewHolder实现几乎一样（为了减少每一个item布局找控件的时间）
 * 3. 在 Adapter中实现3个方法：
 *    onCreateViewHolder() 为每个Item inflater出一个View，View直接封装在ViewHolder中，每一个item布局里的控件就在ViewHolder中找到
 *    onBindViewHolder() 这个方法主要用于适配渲染数据到View中->item View中
 *    getItemCount() 获取item长度
 * 可以看出，RecyclerView将ListView中getView()的功能拆分成了onCreateViewHolder()和onBindViewHolder()。*/


public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    // 创建静态类 ViewHolder
    // 可以视为 onCreate 方法，在这里获取行布局中的所有控件
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private Color color;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout1111);
        }
    }

    private final int[] colors;
    // 构造函数 获取数据
    public MyRecycleViewAdapter(int[] colors) {
        this.colors = colors;
    }

    @NonNull
    @Override
    //膨胀布局 为每一行提供外观的地方
    public MyRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_itemt,parent,false);
        return new ViewHolder(view);
    }

    @Override
    // 将每一行的数据值和每一行的布局进行绑定
    public void onBindViewHolder(@NonNull MyRecycleViewAdapter.ViewHolder holder,int position) {
        holder.linearLayout.setBackgroundColor(colors[position]);
        holder.itemView.setOnClickListener(v -> System.out.print("点击了颜色:"));
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }


}
