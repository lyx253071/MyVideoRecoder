package com.example.myvideorecoderlib;

import androidx.annotation.MainThread;

public interface RecordCallBack {
    /**
     * 录制成功的回调接口
     * @param filePath 视频存储路径
     */
    @MainThread
    public void succed(String filePath);

    /**
     * 录制失败的回调接口
     * @param code 异常代码
     * @param msg  异常信息
     */
    @MainThread
    public void failed(int code,String msg);
}
