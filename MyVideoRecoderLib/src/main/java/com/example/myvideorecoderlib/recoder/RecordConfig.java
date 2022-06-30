package com.example.myvideorecoderlib.recoder;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.myvideorecoderlib.RCCallBack;
import com.example.myvideorecoderlib.camera.open.CameraFacing;

import java.util.ArrayList;
import java.util.List;

public class RecordConfig {
    /**
     * 后置相机
     */
    private static final int FACING_BACK = 0;

    /**
     * 前置相机
     */
    private static final int FACING_FRONT = 1;

    /**
     * 默认录制宽度
     */
    private static final int DEFAULT_WIDTH = 1280;

    /**
     * 默认录制高度
     */
    private static final int DEFAULT_HEIGHT = 720;

    /**
     * 默认帧数
     */
    private static final int DEFAULT_FRAME_RATE = 20;

    /**
     * 默认码率，512 KB
     */
    private static final int DEFAULT_BIT_RATE = 512;

    /**
     * 默认最短录制时间
     */
    private static final int DEFAULT_MIN_TIME = 0;

    /**
     * 默认最长录制时间
     */
    private static final int DEFAULT_MAX_TIME = 600;

    /**
     * 默认最长录制时间
     */
    private static final boolean DEFAULT_AUTO_OPEN = true;


    private int mCamera;
    private int mVideoWidth;
    private int mVideoHeight;
    private int mFrameRate;
    private int mBitRate;
    private int mMinTime;
    private int mMaxTime;
    private boolean mAutoOpen;
    private Bitmap mBitmap;
    private Context mContext;
    private String mPackgeName;
    private List<Integer> mMaskID;
    private RCCallBack mRCCallBack;

    private static volatile RecordConfig recordConfig;

    private RecordConfig(Context context){
        this.mContext = context;
        mCamera = FACING_FRONT;
        mVideoWidth = DEFAULT_WIDTH;
        mVideoHeight = DEFAULT_HEIGHT;
        mFrameRate = DEFAULT_BIT_RATE;
        mBitRate = DEFAULT_BIT_RATE;
        mMinTime = DEFAULT_MIN_TIME;
        mMaxTime = DEFAULT_MAX_TIME;
        mAutoOpen = DEFAULT_AUTO_OPEN;
        if(mContext!=null) mPackgeName = mContext.getPackageName();
        mMaskID = new ArrayList<>();

    }

    public static RecordConfig getInstance(Context context){
        if(recordConfig==null){
            synchronized (RecordConfig.class){
                if(recordConfig==null){
                    recordConfig = new RecordConfig(context);
                }
            }
        }
        return recordConfig;
    }

    public int getmCamera() {
        return mCamera;
    }

    public void setmCamera(int mCamera) {
        this.mCamera = mCamera;
    }

    public int getmVideoWidth() {
        return mVideoWidth;
    }

    public void setmVideoWidth(int mVideoWidth) {
        this.mVideoWidth = mVideoWidth;
    }

    public int getmVideoHeight() {
        return mVideoHeight;
    }

    public void setmVideoHeight(int mVideoHeight) {
        this.mVideoHeight = mVideoHeight;
    }

    public int getmFrameRate() {
        return mFrameRate;
    }

    public void setmFrameRate(int mFrameRate) {
        this.mFrameRate = mFrameRate;
    }

    public int getmBitRate() {
        return mBitRate;
    }

    public void setmBitRate(int mBitRate) {
        this.mBitRate = mBitRate;
    }

    public int getmMinTime() {
        return mMinTime;
    }

    public void setmMinTime(int mMinTime) {
        this.mMinTime = mMinTime;
    }

    public int getmMaxTime() {
        return mMaxTime;
    }

    public void setmMaxTime(int mMaxTime) {
        this.mMaxTime = mMaxTime;
    }

    public boolean ismAutoOpen() {
        return mAutoOpen;
    }

    public void setmAutoOpen(boolean mAutoOpen) {
        this.mAutoOpen = mAutoOpen;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public String getmPackgeName(){return mPackgeName;}

    public void addMask(int id){ mMaskID.add(id);}

    public int[] getmMaskIDS() {
        int count=mMaskID.size();
        int[] res = new int[count];
        for(int i=0;i<count;i++) res[i]=mMaskID.get(i);
        return res;}

    public RCCallBack getmRCCallBack() {
        return mRCCallBack;
    }

    public void setmRCCallBack(RCCallBack mRCCallBack) {
        this.mRCCallBack = mRCCallBack;
    }
}
