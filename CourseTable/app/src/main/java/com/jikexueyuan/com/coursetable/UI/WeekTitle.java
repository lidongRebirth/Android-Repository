package com.jikexueyuan.com.coursetable.UI;

/**
 * Created by asus tp on 2018/2/12.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wan on 2016/10/12.
 * 自定义标题栏，用来显示周一到周日
 */
public class WeekTitle extends View {

    //保存当前的日期
    private int day;

    private Paint mPaint;

    private String[] days = {"一", "二", "三", "四", "五", "六", "日"};

    public WeekTitle(Context context)
    {
        super(context);
    }

    public WeekTitle(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        day = 0;
        mPaint = new Paint();
    }

    /**
     * 重写测量函数，否则在设置wrap_content的时候默认为match_parent
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setCurrentDay(int day)
    {
        this.day = day;
    }

    public void onDraw(Canvas canvas)
    {
        //获得当前View的宽度
        int width = getWidth();
        int offset = width / 8;
        int currentPosition = offset;
        //设置要绘制的字体
        mPaint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        mPaint.setTextSize(30);
        mPaint.setColor(Color.rgb(0, 134, 139));
        for(int i = 0; i < 7 ; i++)
        {
            //圈出当前的日期
            if( day == i)
            {
                System.out.println("画出当前的日期!");

            }
            canvas.drawText(days[i], currentPosition, 30, mPaint);
            currentPosition += offset;
        }
        //调用父类的绘图方法
        super.onDraw(canvas);
    }

}