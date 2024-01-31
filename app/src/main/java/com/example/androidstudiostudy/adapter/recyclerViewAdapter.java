package com.example.androidstudiostudy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidstudiostudy.R;
import com.example.androidstudiostudy.data.UserBean;
import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {
    ArrayList<UserBean> items;
    Context context;

    //声明一个广播事件的表示串
    public final static String EVENT = "guangboshijianbiaoshichuan";

    public recyclerViewAdapter(ArrayList<UserBean> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public recyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listview_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.phone.setText(items.get(position).getPhone());
        holder.password.setText(items.get(position).getPassWord());
        int i = position;

        // 单击列表项将对象传递到下一个页面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 点击一条项目，在页面上显示LoginWithSqlite上显示 用什么呢？广播？
                // 创建一个广播事件的意图
                Intent intent = new Intent(EVENT);
                intent.putExtra("checkView",items.get(i));
                // 通过本地广播管理器来发送广播
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, password;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            password = itemView.findViewById(R.id.passWord);
        }
    }
}