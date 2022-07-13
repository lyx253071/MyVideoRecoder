package com.example.my_video_recoder;

 /**
  *
  * @ClassName:      LoadResult
  * @Description:    加载结果的回调方法
  * @Author:         lyx253071
  * @Version:        1.0
  */
public interface LoadResult {
    //加载成功
    public void succeed();
    //加载失败
    public void failed(int code,String msg);
}
