package com.example.my_video_recoder.model;

import com.example.my_video_recoder.LoadResult;

/**
  *
  * @Name:           LoadData
  * @Description:    Model层的接口
  */
public interface LoadData {
    //加载数据
    public void load();
    //设置录制结果的监听器
    public void setLoadResultListener(LoadResult loadResultListener);
    //取消加载任务
    public void cancle();

}
