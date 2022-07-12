package com.example.my_video_recoder.presenter;

import androidx.annotation.MainThread;

import com.example.my_video_recoder.Bean.VideoInfo;
import com.example.my_video_recoder.model.LoadData;
import com.example.my_video_recoder.model.ScanFile;
import com.example.my_video_recoder.view.VideoReview;

import java.io.File;
import java.util.List;
import java.util.logging.Handler;
 /**
  *
  * @ClassName:      VideoPresenter
  * @Description:    Scanfile和ViewReview之前的桥梁
  * @Author:         lyx253071
  * @Version:        1.0
  */
public class VideoPresenter {
    //view层
    private VideoReview mVideoReview;
    //model层
    private LoadData mLoadData;
    //视频数据集合
    private List<VideoInfo> mVideoSet;

    public VideoPresenter(VideoReview mVideoReview, final List<VideoInfo> VideoSet, File file) {
        this.mVideoReview = mVideoReview;
        this.mVideoSet = VideoSet;
        this.mLoadData = new ScanFile(mVideoSet,file);
    }

    //获取目录下的视频数据并展示
    public void loadData(){
        if(mLoadData instanceof ScanFile){
            ScanFile task = (ScanFile) mLoadData;
            task.setLoadResult(new ScanFile.LoadResult() {
                @Override
                @MainThread
                public void succeed() {
                    mVideoReview.onSuccess();
                }

                @Override
                @MainThread
                public void failed(int code ,String msg) {
                    mVideoReview.onFailed(code,msg);
                }
            });
            task.load();
        }

    }

    //取消获取数据的任务
    public void cancleLoad() {
        if(mLoadData instanceof ScanFile){
            ScanFile task = (ScanFile) mLoadData;
            task.cancle();
        }

    }

}
