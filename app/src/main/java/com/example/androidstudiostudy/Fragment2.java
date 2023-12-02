package com.example.androidstudiostudy;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Activity 向 Fragment 传值 - getArguments() 得到传回来的值，返回的实际是一个 Bundle
        Bundle bundle = getArguments();
        // 和Activity之间传值一样，是什么类型就get什么类型
        String msg1 = bundle.getString("AtoF1");
        // 根据布局id得到视图
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        // 设置视图
        TextView textView = v.findViewById(R.id.textView2);
        textView.setText(msg1);
        // 返回设置好的视图
        return v ;
    }

    // fragment 向 activity 传值（接口回调）
    // 1.定义一个接口，在这个接口中声明一个用于传递数据的方法
    // 2.让 接收数据的activity实现该接口，然后重写回调方法，目的：获取传入的值并做处理
    // 3.在自定义fragment中，声明一个回调接口的引用
    // 4.在onAttach()方法中，为第三步的引用赋值（可以把activity的对象赋值给它）
    // 5.用引用调用传递数据的方法，本质上调用的是activity中的那个方法，此时就可以把数据传递进去

    /* 本质是：在 Fragment 中定义接口和传递参数的方法
     * 在activity中实现接口并重写接口中的方法
     * 在 Fragment 中的声明一个回调接口的引用
     * 因为 Activity 实现了 CommitData 接口的，所以可以将 Activity 转换为 CommitData 类型的对象
     * 在 onAttach 方法中获取这个对象，并调用其中的方法并传入数据*/

    // 1.定义一个接口，在这个接口中声明一个用于传递数据的方法
    public interface CommitData{
        public void sedMSG(String msg);
    }
    // 3.在自定义fragment中，声明一个回调接口的引用
    private CommitData commitData;

    @Override
    // 4.在onAttach()方法中，为第三步的引用赋值（可以把activity的对象赋值给它）
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 获取到的 Activity 对象强制转换为 CommitData 接口类型的对象,
        // 因为 Activity 实现了 CommitData 接口的，所以可以将其转换为 CommitData 类型的对象
        commitData = (CommitData) getActivity(); // 注意强转
        commitData.sedMSG("传递的");
    }
}