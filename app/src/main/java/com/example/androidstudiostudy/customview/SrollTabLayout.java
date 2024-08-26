package com.example.androidstudiostudy.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.androidstudiostudy.R;
import java.util.ArrayList;
import java.util.List;

public class SrollTabLayout extends AppCompatActivity {
    private String[] list = {"直播", "听课", "做饭", "直播", "听课", "做饭"};
    private List<OneTextTwoColor> tabList = new ArrayList<>();
    private LinearLayout tabLayoutView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sroll_tab_layout);
        mViewPager = findViewById(R.id.view_page);
        initTabLayout(); // 初始化 tabLayout
        initViewPage();

    }

    private void initViewPage() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                ItemFragment itemFragment = ItemFragment.newInstance(list[position], "sss");
                return itemFragment;
            }

            @Override
            public int getCount() {
                return list.length;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                OneTextTwoColor leftItem = tabList.get(position);
                leftItem.setCurrentDirection(OneTextTwoColor.Direction.rightToLeft);
                leftItem.setMiddleNum(1 - positionOffset);
                OneTextTwoColor rightItem = tabList.get(position+1);
                rightItem.setCurrentDirection(OneTextTwoColor.Direction.leftToRight);
                rightItem.setMiddleNum(positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabLayout() {
        tabLayoutView = findViewById(R.id.tab_view);
        for (int i = 0; i < list.length; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            OneTextTwoColor oneTextTwoColor = new OneTextTwoColor(this);

            Log.d("初始化tabLayout", list[i]);
            oneTextTwoColor.setText(list[i]);
            oneTextTwoColor.setTextSize(20);
            oneTextTwoColor.setCustomColor(Color.BLACK);
            oneTextTwoColor.setChangeColor(Color.RED);
            oneTextTwoColor.setLayoutParams(params);
            oneTextTwoColor.invalidate(); // 触发重绘

            tabLayoutView.addView(oneTextTwoColor);
            tabList.add(oneTextTwoColor);
        }
    }
}