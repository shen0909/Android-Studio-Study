package com.example.androidstudiostudy.customview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidstudiostudy.R;

public class CustomView extends AppCompatActivity {

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
    }
}