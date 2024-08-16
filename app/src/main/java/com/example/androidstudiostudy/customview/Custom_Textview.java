package com.example.androidstudiostudy.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.androidstudiostudy.R;

public class Custom_Textview extends View {

    private String text;
    private int textColor = Color.BLACK;
    private int fontSize;

    // 构造函数1：会在代码中创建对象 new 时使用
    public Custom_Textview(Context context) {
        // super(context);
        // 修改构造函数，无论使用哪个构造函授都调用第三个
        this(context, null);
        Log.d("自定义textView", "new 对象");
    }

    // 构造函数2：会在layout布局中使用
    public Custom_Textview(Context context, @Nullable AttributeSet attrs) {
        // super(context, attrs);
        this(context, attrs, 0);
        // h
        Log.d("自定义textView", "在layout布局中使用");
    }

    // 构造函数3：会在layout布局中使用，但是有style
    public Custom_Textview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("自定义textView", "在layout布局中使用，但是有style");
        // 获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTextview);

        text = array.getString(R.styleable.CustomTextview_custom_text);
        textColor = array.getColor(R.styleable.CustomTextview_custom_textColor, textColor);
        fontSize = array.getDimensionPixelSize(R.styleable.CustomTextview_custom_textSize, fontSize);

        // 回收自定义属性
        array.recycle();
    }

    // 构造函数4：Android 5.0 (API 21) 之后新增的，用于支持更复杂的样式定制。
    public Custom_Textview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // 自定义view的测量方法，用于测量 View 大小的重要方法。在这个方法中，你需要根据传入的宽度和高度测量规格，计算并设置 View 的宽度和高度。
    // int widthMeasureSpec, int heightMeasureSpec 是父类传过来的
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // MeasureSpec.getMode(xxx) 获取宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // MeasureSpec.AT_MOST 表示 View 的大小可以最大到父 View 允许的尺寸，但不能超过这个尺寸。通常情况下，在使用 View.WRAP_CONTENT 且父 View 设置了最大尺寸时，会使用这种模式
        // MeasureSpec.EXACTLY  View 的大小已经被精确地确定了，通常是父 View 已经为它指定了确切的尺寸。例如，在使用 View.MATCH_PARENT 或者设置了精确的尺寸时，会使用这种模式
        // MeasureSpec.UNSPECIFIED 表示 View 的大小可以任意扩展，不受限制。通常情况下，在使用 View.WRAP_CONTENT 时，会使用这种模式
        if (widthMode == MeasureSpec.UNSPECIFIED) {

        }
    }

    // 用于绘制
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
    }

    // 用于处理用户交互：手指触摸等
    /* MotionEvent 事件分发/拦截 */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
