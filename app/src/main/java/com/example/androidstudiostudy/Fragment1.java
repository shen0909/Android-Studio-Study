package com.example.androidstudiostudy;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1# newInstance} factory method to
 * create an instance of this fragment.
 */
// 新建的一个 Fragment 类
public class Fragment1 extends Fragment {


    @Override
    public void onAttach(@NonNull Context context) {
        Log.d("FragmentLife","onAttach-----Fragment与activity关联");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    // onCreateView -> Fragment 创建视图时调用的方法
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("FragmentLife","onCreateView-----Fragment创建好了");
        // Inflate the layout for this fragment
        // 将指定的布局文件填充到该Fragment的视图中，并返回该视图
        // 使用LayoutInflater将布局文件转换为View对象，并将该View对象添加到指定的ViewGroup中。最后，它返回该View对象作为Fragment的视图
        /* 注意！！
         * LayoutInflater 的 inflate() 方法，它的作用是把xml 布局转换为对应的 View 对象
         * findViewById则是从布局文件中查找一个控件*/
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("FragmentLife","onActivityCreated-----Activity已经建好了");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("FragmentLife","onStart-----Fragment启动");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("FragmentLife","onResume-----Fragment显示");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("FragmentLife","onPause-----Fragment暂停");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("FragmentLife","onStop-----Fragment停止");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("FragmentLife","onDestroyView-----销毁Fragment视图");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("FragmentLife","onDestroy-----销毁Fragment");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("FragmentLife","onDetach-----Fragment与activity解除关联");
        super.onDetach();
    }


}