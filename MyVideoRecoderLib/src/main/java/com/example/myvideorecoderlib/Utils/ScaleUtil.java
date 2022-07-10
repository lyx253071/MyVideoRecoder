package com.example.myvideorecoderlib.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class ScaleUtil {
    private static ScaleUtil scaleUtil;
    //默认参考宽高
    private static final float STANDARD_WIDTH = 720;
    private static final float STANDARD_HEIGHT = 1080;

    //屏幕显示的宽高
    private int mDisplayWidth;
    private int mDIsplayHeight;

    private ScaleUtil(Context context){
        //获取屏幕的宽高
        if (mDisplayWidth == 0 || mDIsplayHeight == 0){
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if(manager != null){
                DisplayMetrics displayMetrics = new DisplayMetrics();
                manager.getDefaultDisplay().getMetrics(displayMetrics);

                if (displayMetrics.widthPixels > displayMetrics.heightPixels){
                    //横屏
                    Log.d("scale", "ScaleUtil: 横屏");
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDIsplayHeight = displayMetrics.widthPixels;
                }else {
                    Log.d("scale", "ScaleUtil: 竖屏 ----> 状态栏高度" +getStatusBarHeight(context));
                    mDisplayWidth = displayMetrics.widthPixels;
                    mDIsplayHeight = displayMetrics.heightPixels - getStatusBarHeight(context);//屏幕减去状态栏的高度
                }
            }
        }
    }

    //获取状态栏的高度
    public int getStatusBarHeight(Context context){
        int resID = context.getResources().getIdentifier("status_bar_height","dimen","android");
        if (resID > 0 ){
            return context.getResources().getDimensionPixelSize(resID);
        }
        return 0;
    }

    public static ScaleUtil getInstance(Context context){
        if (scaleUtil == null){
            scaleUtil = new ScaleUtil(context.getApplicationContext());
        }
        return scaleUtil;
    }
    //获取水平方向上的缩放比例
    public float getHorizaontalScale(){
        return mDisplayWidth / STANDARD_WIDTH;
    }

    //获取垂直方向上的缩放比例
    public float getVerticalScale(){
        return mDIsplayHeight / STANDARD_HEIGHT;
    }

    public int getmDisplayWidth() {
        return mDisplayWidth;
    }

    public int getmDIsplayHeight() {
        return mDIsplayHeight;
    }
}


