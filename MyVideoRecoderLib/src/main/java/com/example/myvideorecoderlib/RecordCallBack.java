package com.example.myvideorecoderlib;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

/**
  *
  * @ClassName:      RecordCallBack
  * @Description:    录制视频的回调接口
  * @Author:         lyx253071
  * @Version:        1.0
  */
public interface RecordCallBack {
    /**
     * 录制成功的回调接口
     * @param filePath 视频存储路径
     */
    @MainThread
    public void succed( String filePath);

    /**
     * 录制失败的回调接口
     * @param code 异常代码
     * @param msg  异常信息
     */
    @MainThread
    public void failed( int code,  String msg);
}
