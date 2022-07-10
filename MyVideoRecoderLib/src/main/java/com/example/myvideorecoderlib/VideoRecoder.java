package com.example.myvideorecoderlib;

import android.content.Context;
import com.example.myvideorecoderlib.recoder.RecordConfig;

public class VideoRecoder {
    private Context mContext;
    private RecordConfig mRecordConfig;

    public VideoRecoder(Context mContext) {
        this.mContext = mContext;
        if(mContext!=null) mRecordConfig = RecordConfig.getInstance(mContext);
    }
    /**
     * 开始录制
     */
    public void startRecod(){
        RecoderActivity.activityStart(mContext,mRecordConfig);
    }
    /**
     * 设置视频分辨路宽度
     * @param width
     */
    public void setVideoWidth(int width){
        mRecordConfig.setVideoWidth(width);
    }

    /**
     * 设置视频分辨率高度
     * @param height
     */
    public void setVideoHeight(int height){
        mRecordConfig.setVideoHeight(height);
    }
    /**
     * 设置视频帧率
     * @param frameRate
     */
    public void setFrame(int frameRate){
        mRecordConfig.setFrameRate(frameRate);
    }
    /**
     * 设置视频码率
     * @param BitRate
     */
    public void setBitRate(int BitRate){
        mRecordConfig.setBitRate(BitRate);
    }
    /**
     * 设置蒙版
     * @param id 资源ID值
     */
    public void addMask(int id){
        mRecordConfig.addMask(id);
    }
    /**
     * 设置相机自动打开
     * @param tag
     */
    public void setAutoOpen(boolean tag){
        mRecordConfig.setAutoOpen(tag);
    }
    /**
     * 设置最长录制时间
     * @param maxTime
     */
    public void setMaxTime(int maxTime){
        mRecordConfig.setMaxTime(maxTime);
    }
    /**
     * 设置最短录制时间
     * @param minTime
     */
    public void setMinTime(int minTime){
        mRecordConfig.setMinTime(minTime);
    }
    /**
     * 设置结果回调
     * @param rCCallBack RCCallBack的接口实现
     */
    public void setCallback(RCCallBack rCCallBack){
        mRecordConfig.setRCCallBack(rCCallBack);
    }
}
