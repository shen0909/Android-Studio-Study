package com.example.androidstudiostudy.UI;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidstudiostudy.R;
import com.example.androidstudiostudy.adapter.TabViewFragAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        TabLayout tabLayout = findViewById(R.id.tb);
        ViewPager2 viewPager2 = findViewById(R.id.tb_vp2);
        viewPager2.setAdapter(new TabViewFragAdapter(this));
        // 如何将 TabLayout 和页面切换连接到一起 --- addOnTabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            // 选择了 tab ,viewPager2就设置到 该tab的位序所在页
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // 但此时 ViewPager2 滑动，tablayout并不会对应改变 --- registerOnPageChangeCallback 中的 onPageSelected
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
}
