package com.example.androidstudiostudy.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.androidstudiostudy.R;

/* 1.设置自定义属性，编写attrs.xml
 * 2.在布局中获取自定义属性
 * 3.onMeasure()
 * 4.画弧*/
public class QQStepView extends View {
    private int outerColor = Color.RED;
    private int innerColor = Color.RED;
    private int stepTextColor = Color.RED;
    private int stepTextSize = 15;
    private int borderWidth = 15;
    private int stepMax = 100;
    private int currentStep = 90;
    private Paint outPaint, innerPaint, textPaint;

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);

        // 配置自定义属性
        outerColor = typedArray.getColor(R.styleable.QQStepView_outerColor, outerColor);
        innerColor = typedArray.getColor(R.styleable.QQStepView_innerColor, innerColor);
        stepTextColor = typedArray.getColor(R.styleable.QQStepView_stepTextColor, stepTextColor);
        currentStep = typedArray.getInt(R.styleable.QQStepView_currentStep, currentStep);
        stepTextSize = typedArray.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, stepTextSize);
        borderWidth = typedArray.getDimensionPixelSize(R.styleable.QQStepView_borderWidth, borderWidth);
        typedArray.recycle();

        initPaint();
    }

    // 初始化画笔
    private void initPaint() {
        outPaint = new Paint();
        outPaint.setColor(outerColor);
        outPaint.setAntiAlias(true);
        outPaint.setStrokeWidth(borderWidth); //设置描边的宽度
        outPaint.setStyle(Paint.Style.STROKE);
        outPaint.setStrokeCap(Paint.Cap.ROUND);

        innerPaint = new Paint();
        innerPaint.setColor(innerColor);
        innerPaint.setAntiAlias(true);
        innerPaint.setStyle(Paint.Style.STROKE);
        innerPaint.setStrokeWidth(12);

        textPaint = new Paint();
        textPaint.setColor(stepTextColor);
        textPaint.setTextSize(stepTextSize);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        // 取宽高的最小值，确保是个正方形
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        // 画外圆弧
        // 为什么宽度要除以二？ 因为画笔设置描边宽度setStrokeWidth时要/2才能贴边
        RectF rectF = new RectF(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth / 2, getHeight() - borderWidth / 2);
        canvas.drawArc(rectF, 135, 270, false, outPaint);

        // 画内圆弧
        if (stepMax == 0) return;
        float innerAngle = (float) currentStep / stepMax;
        canvas.drawArc(rectF, 135, innerAngle * 270, false, innerPaint);

        // 画文字
        String currentStepStr = "" + currentStep + "";
        Rect textRect = new Rect();
        textPaint.getTextBounds(currentStepStr, 0, currentStepStr.length(), textRect);
        int dx = getWidth() / 2 - textRect.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = dy + (getHeight() / 2);
        canvas.drawText(currentStepStr, dx, baseLine, textPaint);
    }
}
