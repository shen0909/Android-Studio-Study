package com.example.androidstudiostudy.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.example.androidstudiostudy.R;

// 一段文字两种颜色，文字变色
@SuppressLint("AppCompatCustomView")
public class OneTextTwoColor extends TextView {
    // 自定义属性
    private int customColor = Color.BLACK;
    private int changeColor = Color.BLACK;
    private Paint customPaint, changePaint;
    private double middleNum = 0.5; //给定一个默认的中间值，用来划分
    private int baseLine; //基线
    private int text_x; // 原点x坐标
    private String text;

    public OneTextTwoColor(Context context) {
        this(context, null);
    }

    public OneTextTwoColor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OneTextTwoColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs); // 获取自定义属性
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.OneTextTwoColor);
        changeColor = array.getColor(R.styleable.OneTextTwoColor_changeColor, Color.BLACK);
        customColor = array.getColor(R.styleable.OneTextTwoColor_customColor, Color.BLACK);
        array.recycle(); //回收

        text = getText().toString();

        customPaint = new Paint();
        customPaint.setColor(customColor);
        customPaint.setAntiAlias(true);
        customPaint.setTextSize(getTextSize());

        changePaint = new Paint();
        changePaint.setColor(changeColor);
        changePaint.setAntiAlias(true);
        changePaint.setTextSize(getTextSize());
    }

    public OneTextTwoColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // 绘制文字
    /* 1.一段文字，两种颜色，给定一个值，从此处开始划分，前后两段用不同的画笔绘制 */
    @Override
    protected void onDraw(Canvas canvas) {
        calculateBaseLine();

        // 分前后两部分绘制文字
        // 用 clipOutRect 将画布裁剪成两部分，分别用不同的画笔绘制
        int middleWidth = (int) (middleNum * getWidth());
        canvasRect(canvas, 0, middleWidth, customPaint);
        canvasRect(canvas, middleWidth, getWidth(), changePaint);
    }

    /// 绘制裁剪区域
    public void canvasRect(Canvas canvas, int left, int middleWidth, Paint paint) {
        canvas.save(); // 保存画布
        canvas.clipRect(left, 0, middleWidth, getHeight());
        canvas.drawText(text, text_x, baseLine, paint);
        canvas.restore(); // 释放画布
    }

    /// 计算基线y坐标 原点x坐标
    public void calculateBaseLine() {
        Rect bound = new Rect(); // 创建一个 Rect 对象,用于存储文本的边界信息
        changePaint.getTextBounds(text, 0, text.length(), bound); // 用 getTextBounds 方法，计算出指定文本 text 的边界，并将结果存储到 bound 对象中

        /* x：文本绘制起始点横坐标 */
        // 宽度的一半 - 文字的一半
        text_x = getWidth() / 2 - bound.width() / 2;

        /* y: 文本绘制基线纵坐标 */
        // top: 基线到文字顶部的距离
        // bottom:基线到文字底部的距离
        // 中线 高度的一半 = (bottom - top)/2
        // 设 中线到 基线的距离为 dz,dz = 中线 - bottom
        // 最后得出 基线 = 中线高度 + dz
        Paint.FontMetricsInt fontMetricsInt = changePaint.getFontMetricsInt();
        int middle = (fontMetricsInt.bottom - fontMetricsInt.top) / 2;
        int dz = middle - fontMetricsInt.bottom;
        baseLine = middle + dz;
    }
}
