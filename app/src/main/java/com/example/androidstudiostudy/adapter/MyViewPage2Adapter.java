package com.example.androidstudiostudy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstudiostudy.R;

import java.util.List;

// MyViewPage2Adapter 继承自 RecyclerView.Adapter 的自定义适配器,需要实现继承方法
public class MyViewPage2Adapter extends RecyclerView.Adapter {

    // 数据源
    private List<Integer> photos;
    private List<String> content;

    // 构造函数
    public MyViewPage2Adapter(List<Integer> photos,List<String> content) {
        this.photos = photos;
        this.content = content;
    }

    @NonNull
    @Override
    // 创建
    /* 需要返回一个 RecyclerView.ViewHolder 对象,而自带的 RecyclerView.ViewHolder 对象不足以完成功能，
     * 所以需要新建一个继承 RecyclerView.ViewHolder 的类 ====> myViewHolder*/
    /* ViewGroup parent:新的ViewHolder将会被添加到哪个ViewGroup中，可以使用这个parent参数来获取RecyclerView所在的ViewGroup*/
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 获取 item 布局的视图

        // LayoutInflater.from(parent.getContext()),这一步是为了获取正确的 LayoutInflater 对象
        /* 在一般情况下，你可以传入任何有效的Context对象来获取LayoutInflater。
         * 但为了保持一致性和避免潜在的问题，建议在RecyclerView的Adapter中使用RecyclerView的父布局的上下文来获取LayoutInflater。*/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_page2_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    // 绑定
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 获取自定义的 ViewHolder ,并为它的控件设置资源
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.imageView.setImageResource(photos.get(position));
        myViewHolder.textView.setText(content.get(position));
    }

    @Override
    // 资源数量
    public int getItemCount() {
        return photos.size();
    }

    // MyViewHolder 继承自 RecyclerView.ViewHolder 的自定义 ViewHolder
    /* RecyclerView.ViewHolder是用来持有每个item的视图元素的，它通常包含了每个item视图中会用到的控件的引用。
     * 当RecyclerView需要展示新的item时，它会使用已经创建好的ViewHolder，并更新ViewHolder中的控件来展示新的数据。*/
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.vp2_item_ph);
            textView = itemView.findViewById(R.id.vp2_item_tv);
        }
    }
}
