package com.example.my_video_recoder.presenter;

import com.example.my_video_recoder.Bean.VideoInfo;
import com.example.my_video_recoder.model.Request;
import com.example.my_video_recoder.model.ScanFile;
import com.example.my_video_recoder.view.VideoReview;

import java.io.File;
import java.util.List;

public class VideoPresenter {
    private VideoReview mVideoReview;
    private Request mRequest;
    private List<VideoInfo> mVideoSet;

    public VideoPresenter(VideoReview mVideoReview, final List<VideoInfo> VideoSet, File file) {
        this.mVideoReview = mVideoReview;
        this.mVideoSet = VideoSet;
        this.mRequest = new ScanFile(mVideoSet,file);
    }

    public void loadData(){
        ScanFile task = (ScanFile) mRequest;
        task.setLoadResult(new ScanFile.LoadResult() {
            @Override
            public void succeed() {
                mVideoReview.finished();
            }

            @Override
            public void failed() {
                mVideoReview.finished();
            }
        });
        task.load();
    }


}
