package com.example.androidstudiostudy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.androidstudiostudy.tabfragment.HomeFragment;
import com.example.androidstudiostudy.tabfragment.MineFragment;
import com.example.androidstudiostudy.tabfragment.SettingFragment;

public class TabViewFragAdapter extends FragmentStateAdapter {
    public TabViewFragAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new SettingFragment();
            case 2:
                return new MineFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    // 返回的项目数
    public int getItemCount() {
        return 3;
    }
}
