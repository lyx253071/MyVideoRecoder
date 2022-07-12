package com.example.myvideorecoderlib;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myvideorecoderlib.exception.RecordException;
import com.example.myvideorecoderlib.recorder.RecordConfig;
import com.example.myvideorecoderlib.utils.ReflectObjectUtils;

import java.util.Map;

 /**
  *
  * @ClassName:      VideoRecorder
  * @Description:    录制视频的对外接口类
  * @Author:         lyx253071
  * @Version:        1.0
  */
public class VideoRecorder {
    //获取配置类信息的标签
    private static final String CUSTOM_CAMERA = "CUSTOM_CAMERA";
    private static final String CUSTOM_VIDEO_WIDTH = "CUSTOM_VIDEO_WIDTH";
    private static final String CUSTOM_VIDEO_HEIGHT = "CUSTOM_VIDEO_HEIGHT";
    private static final String CUSTOM_FRAME_RATE = "CUSTOM_FRAME_RATE";
    private static final String CUSTOM_BIT_RATE = "CUSTOM_BIT_RATE";
    private static final String CUSTOM_MIN_TIME = "CUSTOM_MIN_TIME";
    private static final String CUSTOM_MAX_TIME = "CUSTOM_MAX_TIME";

    private static final String DEFAULT_CAMERA = "DEFAULT_CAMERA";
    private static final String DEFAULT_VIDEO_WIDTH = "DEFAULT_VIDEO_WIDTH";
    private static final String DEFAULT_VIDEO_HEIGHT = "DEFAULT_VIDEO_HEIGHT";
    private static final String DEFAULT_FRAME_RATE = "DEFAULT_FRAME_RATE";
    private static final String DEFAULT_BIT_RATE = "DEFAULT_BIT_RATE";
    private static final String DEFAULT_MIN_TIME = "DEFAULT_MIN_TIME";
    private static final String DEFAULT_MAX_TIME = "DEFAULT_MAX_TIME";

    private Context mContext;
    private RecordConfig mRecordConfig;

    public VideoRecorder(@NonNull Context mContext, @Nullable Class<?> obj ) {
        this.mContext = mContext;
        if(mContext!=null) mRecordConfig = RecordConfig.getInstance(mContext);
        if(obj!=null){
            Log.d("Reflect TAG", "VideoRecorder: start");
            Map<String, Object> recordCfg = ReflectObjectUtils.getKeyValues(obj);
            Log.d("Reflect TAG", "VideoRecorder: "+recordCfg.toString());
            init(recordCfg);
        }
    }

    public VideoRecorder(@NonNull Context mContext) {
        this.mContext = mContext;
        if(mContext!=null) mRecordConfig = RecordConfig.getInstance(mContext);
    }

    //初始化配置参数
    private void init(Map<String,Object> map){
        Object defCamera = map.get(DEFAULT_CAMERA);
        Object cusCamera = map.get(CUSTOM_CAMERA);
        if(cusCamera instanceof Integer && (Integer) cusCamera !=-1){
            mRecordConfig.setCamera((Integer) cusCamera);
        }else{
            if(defCamera instanceof Integer) mRecordConfig.setCamera((Integer) defCamera);
        }

        Object defVideoWidth = map.get(DEFAULT_VIDEO_WIDTH);
        Object cusVideoWidth = map.get(CUSTOM_VIDEO_WIDTH);
        if(cusVideoWidth instanceof Integer && (Integer) cusVideoWidth !=-1){
            mRecordConfig.setVideoWidth((Integer) cusVideoWidth);
        }else{
            if(defVideoWidth instanceof Integer) mRecordConfig.setVideoWidth((Integer) defVideoWidth);
        }

        Object defVideoHeight= map.get(DEFAULT_VIDEO_HEIGHT);
        Object cusVideoHeight = map.get(CUSTOM_VIDEO_HEIGHT);
        if(cusVideoHeight instanceof Integer && (Integer) cusVideoHeight !=-1){
            mRecordConfig.setVideoHeight((Integer) cusVideoHeight);
        }else{
            if(defVideoHeight instanceof Integer) mRecordConfig.setVideoHeight((Integer) defVideoHeight);
        }

        Object defFrameRate= map.get(DEFAULT_FRAME_RATE);
        Object cusFrameRate = map.get(CUSTOM_FRAME_RATE);
//        Log.d("reflect value", "init: "+defFrameRate +"=== cus"+cusFrameRate);
        if(cusFrameRate instanceof Integer && (Integer) cusFrameRate !=-1){
            mRecordConfig.setFrameRate((Integer)cusFrameRate);
        }else{
            if(defFrameRate instanceof Integer) mRecordConfig.setFrameRate((Integer) defFrameRate);
        }

        Object defBitRate= map.get(DEFAULT_BIT_RATE);
        Object cusBitRate = map.get(CUSTOM_BIT_RATE);
        if(cusBitRate instanceof Integer && (Integer) cusBitRate !=-1){
            mRecordConfig.setBitRate((Integer)cusBitRate);
        }else{
            if(defBitRate instanceof Integer) mRecordConfig.setBitRate((Integer) defBitRate);
        }

        Object defMinTime= map.get(DEFAULT_MIN_TIME);
        Object cusMinTime = map.get(CUSTOM_MIN_TIME);
        if(cusMinTime instanceof Integer && (Integer) cusMinTime !=-1){
            mRecordConfig.setMinTime((Integer)cusMinTime);
        }else{
            if(defMinTime instanceof Integer) mRecordConfig.setMinTime((Integer) defMinTime);
        }

        Object defMaxTime= map.get(DEFAULT_MAX_TIME);
        Object cusMaxTime = map.get(CUSTOM_MAX_TIME);
        if(cusMaxTime instanceof Integer && (Integer) cusMaxTime !=-1){
            mRecordConfig.setMaxTime((Integer)cusMaxTime);
        }else{
            if(defMaxTime instanceof Integer) mRecordConfig.setMaxTime((Integer) defMaxTime);
        }

    }

    /**
     * 开始录制
     */
    public void startRecod() {

        try {
            RecoderActivity.activityStart(mContext,mRecordConfig);
        } catch (RecordException e) {
            e.printStackTrace();
            Log.e("RecordException", e.getMsg());
            Toast.makeText(mContext, e.getMsg(), Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * 设置视频分辨路宽度
     * @param width
     */
    public void setVideoWidth( @NonNull int width){
        mRecordConfig.setVideoWidth(width);
    }

    /**
     * 设置视频分辨率高度
     * @param height
     */
    public void setVideoHeight(@NonNull  int height){
        mRecordConfig.setVideoHeight(height);
    }
    /**
     * 设置视频帧率
     * @param frameRate
     */
    public void setFrame(@NonNull int frameRate){
        mRecordConfig.setFrameRate(frameRate);
    }
    /**
     * 设置视频码率
     * @param BitRate
     */
    public void setBitRate(@NonNull int BitRate){
        mRecordConfig.setBitRate(BitRate);
    }
    /**
     * 设置蒙版
     * @param id 资源ID值
     */
    public void addMask(@NonNull int id){
        mRecordConfig.addMask(id);
    }
    /**
     * 设置相机自动打开
     * @param tag
     */
    public void setAutoOpen(@NonNull boolean tag){
        mRecordConfig.setAutoOpen(tag);
    }
    /**
     * 设置最长录制时间
     * @param maxTime
     */
    public void setMaxTime(@NonNull int maxTime){
        mRecordConfig.setMaxTime(maxTime);
    }
    /**
     * 设置最短录制时间
     * @param minTime
     */
    public void setMinTime(@NonNull int minTime){
        mRecordConfig.setMinTime(minTime);
    }
    /**
     * 设置结果回调
     * @param recordCallBack RecordCallBack的接口实现
     */
    public void setCallback(@NonNull RecordCallBack recordCallBack){
        mRecordConfig.setRecordCallBack(recordCallBack);
    }
}
