package com.example.androidstudiostudy.customview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidstudiostudy.R;

public class CustomView extends AppCompatActivity {

    private Button btn_ltr,btn_rtl,btn_change_color;
    private OneTextTwoColor two_color_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        QQStepView qqStepView = findViewById(R.id.qqstepview);
        qqStepView.setStepMax(4000);

        // 属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,3000);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float currentStep = (float)animation.getAnimatedValue();
                qqStepView.setCurrentStep((int)currentStep);
            }
        });
        valueAnimator.start();

        btn_ltr = findViewById(R.id.btn_ltr);
        btn_rtl = findViewById(R.id.btn_rtl);
        btn_change_color = findViewById(R.id.btn_change_color);
        two_color_view = findViewById(R.id.two_color_view);
    }

    public void changeLefToRight(View view) {
        two_color_view.setCurrentDirection(OneTextTwoColor.Direction.leftToRight);
        // 属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float currentStep = (float)animation.getAnimatedValue();
                two_color_view.setMiddleNum(currentStep);
            }
        });
        valueAnimator.start();
    }

    public void changeRightToLef(View view) {
        two_color_view.setCurrentDirection(OneTextTwoColor.Direction.rightToLeft);
        // 属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float currentStep = (float)animation.getAnimatedValue();
                two_color_view.setMiddleNum(currentStep);
            }
        });
        valueAnimator.start();
    }

    public void toLookChangeColor(View view) {
        startActivity(new Intent(this, SrollTabLayout.class));
    }
}