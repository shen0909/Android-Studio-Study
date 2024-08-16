package com.example.androidstudiostudy.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidstudiostudy.R;

public class Custom_Textview extends View {

    private String text;
    private int textColor = Color.BLACK;
    private int fontSize;
    private Paint myPaint;

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
        fontSize = array.getDimensionPixelSize(R.styleable.CustomTextview_custom_textSize, sp2px(fontSize));

        // 回收自定义属性
        array.recycle();

        // 初始化画笔
        myPaint = new Paint();
        myPaint.setColor(textColor);
        myPaint.setTextSize(fontSize);
        myPaint.setAntiAlias(true); // 抗锯齿-边缘清晰
    }

    private int sp2px(int fontSize) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, fontSize, getResources().getDisplayMetrics());
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
        int width, height;

        // MeasureSpec.getMode(xxx) 获取宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // MeasureSpec.AT_MOST 表示 View 的大小可以最大到父 View 允许的尺寸，但不能超过这个尺寸。通常情况下，在使用 View.WRAP_CONTENT 且父 View 设置了最大尺寸时，会使用这种模式
        // MeasureSpec.EXACTLY  View 的大小已经被精确地确定了，通常是父 View 已经为它指定了确切的尺寸。例如，在使用 View.MATCH_PARENT 或者设置了精确的尺寸时，会使用这种模式
        // MeasureSpec.UNSPECIFIED 表示 View 的大小可以任意扩展，不受限制。通常情况下，在使用 View.WRAP_CONTENT 时，会使用这种模式

        // 计算宽度
        // 1. 确定的值，这时候不需要计算，给多少是多少
        width = MeasureSpec.getSize(widthMeasureSpec);

        // 2.给的是wrap_content 需要计算
        if (widthMode == MeasureSpec.AT_MOST) {
            Log.e("测量模式", "AT_MOST");

            // 计算的宽度与字体长度、字体大小有关 ----- 需要使用画笔来测量
            // Android的Rect类是形成一个矩形的区域。区域在Android整个界面中的位置由left,top,right,bottom数值来控制.
            Rect bounds = new Rect();
            myPaint.getTextBounds(text, 0, text.length(), bounds); // 获取文本的rect
            width = bounds.width();

        } else if (widthMode == MeasureSpec.EXACTLY) {
            Log.e("测量模式", "EXACTLY");
        } else if (widthMode == MeasureSpec.UNSPECIFIED) {
            Log.e("测量模式", "UNSPECIFIED");
        }

        // 计算高度
        // 1. 确定的值，这时候不需要计算，给多少是多少
        height = MeasureSpec.getSize(heightMeasureSpec);

        // 2.给的是wrap_content 需要计算
        if (heightMode == MeasureSpec.AT_MOST) {
            Rect bounds = new Rect();
            myPaint.getTextBounds(text, 0, text.length(), bounds); // 获取文本的rect
            height = bounds.height();
        }

        // 设置控件的宽高
        setMeasuredDimension(width, height);

    }

    // 用于绘制
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        // 绘制文本
        /* 参数---- text:文本 x:开始的位置 y:基线 paint:画笔*/
        // 如何计算基线？
        // top: baseLine到顶部的距离 bottom：baseLine到底部的距离 高度的一半 = （top-bottom）/2
        // dy = 高度/2 - bottom
        int baseLine, dy;
        Paint.FontMetricsInt fontMetricsInt = myPaint.getFontMetricsInt();
        dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        baseLine = dy + (getHeight() / 2);
        canvas.drawText(text, 0,baseLine, myPaint);

    }

    // 用于处理用户交互：手指触摸等
    /* MotionEvent 事件分发/拦截 */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
