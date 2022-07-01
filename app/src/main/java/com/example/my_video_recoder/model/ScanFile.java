package com.example.my_video_recoder.model;

import android.content.Context;
import android.util.Log;

import com.example.my_video_recoder.Bean.VideoInfo;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class ScanFile implements Request {
    private List<VideoInfo> mVideoInfos;
    private File mFile;

    public ScanFile(List<VideoInfo> videoInfos, File file) {
        this.mVideoInfos = videoInfos;
        this.mFile = file;
    }

    private void getVideoFile(final List<VideoInfo> list, File file) {
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {

                String name = file.getName();

                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (name.equalsIgnoreCase(".mp4")
                            || name.equalsIgnoreCase(".3gp")
                            || name.equalsIgnoreCase(".wmv")
                            || name.equalsIgnoreCase(".ts")
                            || name.equalsIgnoreCase(".rmvb")
                            || name.equalsIgnoreCase(".mov")
                            || name.equalsIgnoreCase(".m4v")
                            || name.equalsIgnoreCase(".avi")
                            || name.equalsIgnoreCase(".m3u8")
                            || name.equalsIgnoreCase(".3gpp")
                            || name.equalsIgnoreCase(".3gpp2")
                            || name.equalsIgnoreCase(".mkv")
                            || name.equalsIgnoreCase(".flv")
                            || name.equalsIgnoreCase(".divx")
                            || name.equalsIgnoreCase(".f4v")
                            || name.equalsIgnoreCase(".rm")
                            || name.equalsIgnoreCase(".asf")
                            || name.equalsIgnoreCase(".ram")
                            || name.equalsIgnoreCase(".mpg")
                            || name.equalsIgnoreCase(".v8")
                            || name.equalsIgnoreCase(".swf")
                            || name.equalsIgnoreCase(".m2v")
                            || name.equalsIgnoreCase(".asx")
                            || name.equalsIgnoreCase(".ra")
                            || name.equalsIgnoreCase(".ndivx")
                            || name.equalsIgnoreCase(".xvid")) {
                        VideoInfo video = new VideoInfo();
                        file.getUsableSpace();
                        video.setDisplayName(file.getName());
                        video.setPath(file.getAbsolutePath());
                        video.setSize(file.length());
                        Log.i("filemanager","path"+video.getPath());
                        list.add(video);
                        return true;
                    }
                    //判断是不是目录
                } else if (file.isDirectory()) {
                    getVideoFile(list, file);
                }
                return false;
            }
        });
    }


    @Override
    public void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getVideoFile(mVideoInfos, mFile);
                if(mVideoInfos.size()>0){
                    mLoadResult.succeed();
                }
                else{
                    mLoadResult.failed();
                }
            }
        }).start();
    }

    public interface LoadResult{
        public void succeed();
        public void failed();
    }
    private LoadResult mLoadResult;

    public void setLoadResult(LoadResult l){
        this.mLoadResult = l;
    }

}
