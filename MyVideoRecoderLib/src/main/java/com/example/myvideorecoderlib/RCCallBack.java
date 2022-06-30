package com.example.myvideorecoderlib;

public interface RCCallBack {
    /**
     * 录制成功的回调接口
     * @param filePath 视频包存储路径
     */
    public void succed(String filePath);

    //录制失败的回调接口
    public void failed();
}
