package com.example.myvideorecoderlib.recorder;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.myvideorecoderlib.RecordCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频录制的配置类（单例）
 */
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
    private RecordCallBack mRecordCallBack;

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
    //获取摄像头
    public int getCamera() {
        return mCamera;
    }
    //设置摄像头
    public void setCamera(int mCamera) {
        this.mCamera = mCamera;
    }
    //获取视频分辨率宽带
    public int getVideoWidth() {
        return mVideoWidth;
    }
    //设置视频分辨率宽带
    public void setVideoWidth(int mVideoWidth) {
        this.mVideoWidth = mVideoWidth;
    }
    //获取视频分辨率高度
    public int getVideoHeight() {
        return mVideoHeight;
    }
    //设置视频分辨率高度
    public void setVideoHeight(int mVideoHeight) {
        this.mVideoHeight = mVideoHeight;
    }
    //获取视频帧率
    public int getFrameRate() {
        return mFrameRate;
    }
    //设置视频帧率
    public void setFrameRate(int mFrameRate) {
        this.mFrameRate = mFrameRate;
    }
    //获取视频码率
    public int getBitRate() {
        return mBitRate;
    }
    //设置视频码率
    public void setBitRate(int mBitRate) {
        this.mBitRate = mBitRate;
    }
    //获取最短录制时间
    public int getMinTime() {
        return mMinTime;
    }
    //设置最短录制时间
    public void setMinTime(int mMinTime) {
        this.mMinTime = mMinTime;
    }
    //获取最长录制时间
    public int getMaxTime() {
        return mMaxTime;
    }
    //设置最长录制时间
    public void setMaxTime(int mMaxTime) {
        this.mMaxTime = mMaxTime;
    }
    //获取是否自动开启摄像头
    public boolean isAutoOpen() {
        return mAutoOpen;
    }
    //设置是否自动开启摄像头
    public void setAutoOpen(boolean mAutoOpen) {
        this.mAutoOpen = mAutoOpen;
    }
    //获取蒙版图
    public Bitmap getBitmap() {
        return mBitmap;
    }
    //设置蒙版图
    public void setBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }
    //获取应用包名称
    public String getPackgeName(){return mPackgeName;}
    //添加蒙版ID
    public void addMask(int id){ mMaskID.add(id);}
    //获取蒙版ID
    public int[] getMaskIDS() {
        int count=mMaskID.size();
        int[] res = new int[count];
        for(int i=0;i<count;i++) res[i]=mMaskID.get(i);
        return res;}
    //获取录制结束的回调方法
    public RecordCallBack getRecordCallBack() {
        return mRecordCallBack;
    }
    //设置录制结束的回调方法
    public void setRecordCallBack(RecordCallBack mRecordCallBack) {
        this.mRecordCallBack = mRecordCallBack;
    }
}
