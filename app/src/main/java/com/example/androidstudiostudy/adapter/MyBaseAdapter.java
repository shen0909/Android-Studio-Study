package com.example.androidstudiostudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidstudiostudy.R;
import com.example.androidstudiostudy.data.BaseMsg;
import java.util.List;

// 新建一个继承自 BaseAdapter 的类
// 根据准备好的数据源和子项布局完成 ListView 效果的一一设置
public class MyBaseAdapter extends BaseAdapter {

    private List<BaseMsg> baseMsgList;
    private Context context;

    // 构造方法
    public MyBaseAdapter(List<BaseMsg> baseMsgList, Context context) {
        this.baseMsgList = baseMsgList;
        this.context = context;
    }

    // 获取数量（设置listView的长度）
    @Override
    public int getCount() {
        return baseMsgList.size();
    }

    @Override
    // 获取视图（设置 listView 每一项的显示效果）
    /* 参数1：当前Item的下标 --- 和数据源的下标相同，可以由此获取数据源配置item
     * 参数2：当前Item的view
     * 参数3：当前视图的父视图（可调整当前视图的宽高）*/
    // 每个视图出现时都会执行，有多少个item就会调用多少次 getView() 如果item太多就会很浪费资源！！
    public View getView(int position, View convertView, ViewGroup parent) {

        // 优化1：利用进入 RecyclerBin 中的view,减少对view的赋值
        /* 当视图第一次构建后，上下滑动到看不见时就会进入 RecyclerBin，此时 convertView 就不为null,
         * 我们就可以复用这个 convertView */

        // 即使经过优化1的步骤，可以减少view的构建，但是每出现一个view已经会寻找一次控件id,findViewById 依旧十分浪费资源,因此可以进行第二步优化
        // 1.定义一个 内部类 ViewHolder，并声明需要用到的控件属性
        // 2.当 convertView == null 当前视图没有被创建时，初始化一个 ViewHolder 对象，并为其中的控件赋值
        // 3.调用 contvertView.setTag(ViewHolder) 将这个 HolderView 保存到这个view中
        // 4.当 convertView != null时，通过 contvertView.getTag() 找到保存的ViewHolder
        ViewHolder holder;
        if (convertView == null) {
            // 完成对view的设置
            // 将设置好的 item 布局资源转换成view
            // convertView = LayoutInflater.from(context).inflate(R.layout.baseadapter_item, null); // 此时得到的是最初的item布局，没有添加的数据
            // 可以使用 parent.getContext() 获取父视图的位置,这一步是为了获取正确的 LayoutInflater 对象
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.baseadapter_item, null); // 此时得到的是最初的item布局，没有添加的数据

            holder = new ViewHolder();
            holder.icon = convertView.findViewById(R.id.base_icon);
            holder.nickName = convertView.findViewById(R.id.base_name);
            holder.content = convertView.findViewById(R.id.base_content);
            convertView.setTag(holder);

            System.out.println("当前显示的视图" + (position + 1));
        } else {
            holder = (ViewHolder) convertView.getTag(); // 此时要强转成 ViewHolder 类型
        }
        // 获取数据源[position] 的数据，并将他们设置到item视图中的控件中
        BaseMsg m = baseMsgList.get(position); // 获取item的数据列表

        /*ImageView imageView = convertView.findViewById(R.id.base_icon);
        imageView.setImageResource(m.getIcon());*/
        holder.icon.setImageResource(m.getIcon());

        /*TextView tN = convertView.findViewById(R.id.base_name);
        tN.setText(m.getNickName());*/
        holder.nickName.setText(m.getNickName());

        /*TextView tC = convertView.findViewById(R.id.base_content);
        tC.setText(m.getContent());*/
        holder.content.setText(m.getContent());

        // 可以给item的单个控件设置点击事件
        holder.nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "你点击了" + m.getNickName(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public ImageView icon;
        public TextView nickName, content;
    }

}
