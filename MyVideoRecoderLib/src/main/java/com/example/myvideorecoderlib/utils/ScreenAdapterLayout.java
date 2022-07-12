package com.example.myvideorecoderlib.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
 /**
  *
  * @ClassName:      ScreenAdapterLayout
  * @Description:    屏幕适配的布局文件
  * @Author:         lyx25301
  * @Version:        1.0
  */
public class ScreenAdapterLayout extends FrameLayout {
    private boolean flag;
    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("scale Measure", "onMeasure: ");
        int wid = ScaleUtil.getInstance(getContext()).getmDisplayWidth();
        int hei = ScaleUtil.getInstance(getContext()).getmDIsplayHeight();
        if (!flag){
            float scalex = ScaleUtil.getInstance(getContext()).getHorizaontalScale();//获取横向缩放比例
            float scaley = ScaleUtil.getInstance(getContext()).getVerticalScale();//获取竖向缩放比例
            Log.d("scale", "scalex: "+scalex+"======"+"scaley: "+scaley);
            float scaleRate = scalex<scaley?scalex:scaley;
//            for (int i = 0; i < getChildCount(); i++){
//                View child = getChildAt(i);//重新设置子View的布局属性，再进行View的测量
//                LayoutParams lp = (LayoutParams)child.getLayoutParams();
//                lp.width = (int) (lp.width * scalex);//换算宽度目标值
//                lp.height = (int) (lp.height * scaley);//换算高度目标值
//                lp.topMargin = (int) (lp.topMargin * scaley);//换算四周间距的目标值
//                lp.bottomMargin = (int) (lp.bottomMargin * scaley);//换算四周间距的目标值
//                lp.leftMargin = (int) (lp.leftMargin * scalex);//换算四周间距的目标值
//                lp.rightMargin = (int) (lp.rightMargin * scalex);//换算四周间距的目标值
//            }



            //调整缩放比例
            for (int i = 0; i < getChildCount(); i++){
                View child = getChildAt(i);//重新设置子View的布局属性，再进行View的测量
                LayoutParams lp = (LayoutParams)child.getLayoutParams();
                int mWid = lp.width; int mHei = lp.height;
                Log.d("scale2", "onMeasure: wid  "+mWid+"----   hei: "+mHei);
                if(mHei>0 && mWid>0){
                    scalex = wid*1.0f/mWid; scaley = hei*1.0f/mHei;
                    Log.d("scale2", "scalex: "+scalex+"======"+"scaley: "+scaley);
                    scaleRate = scalex<scaley?scalex:scaley;
                    lp.width = (int) (lp.width * scaleRate);//换算宽度目标值
                    lp.height = (int) (lp.height * scaleRate);//换算高度目标值
                    lp.topMargin = (int) (lp.topMargin * scaleRate);//换算四周间距的目标值
                    lp.bottomMargin = (int) (lp.bottomMargin * scaleRate);//换算四周间距的目标值
                    lp.leftMargin = (int) (lp.leftMargin * scaleRate);//换算四周间距的目标值
                    lp.rightMargin = (int) (lp.rightMargin * scaleRate);//换算四周间距的目标值
                }
            }
            flag = true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
