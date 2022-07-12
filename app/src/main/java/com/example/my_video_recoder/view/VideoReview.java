package com.example.my_video_recoder.view;

import androidx.annotation.MainThread;

/**
  *
  * @ClassName:      VideoReview
  * @Description:    View层的接口
  */
public interface VideoReview {
    //加载成功
    @MainThread
    public void onSuccess();
    //加载失败
     @MainThread
    public void onFailed(int code ,String msg);
}
