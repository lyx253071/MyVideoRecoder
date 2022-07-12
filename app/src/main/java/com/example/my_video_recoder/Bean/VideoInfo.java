package com.example.my_video_recoder.Bean;

import androidx.annotation.NonNull;

/**
  *
  * @ClassName:      VideoInfo
  * @Description:    描述视频文件信息的Bean类
  * @Author:         lyx253071
  */
public class VideoInfo {
    private String displayName;
    private String path;
    private long size;

    /**
     *
     * @return 返回视频文件名
     */
    @NonNull
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName 设置视频文件名称
     */
    public void setDisplayName(@NonNull String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return 返回视频文件的存储路径
     */
    @NonNull
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path 设置视频文件路径
     */
    public void setPath(@NonNull String path) {
        this.path = path;
    }

    /**
     *
     * @return 返回视频文件大小
     */
    public long getSize() { return size; }

    /**
     *
     * @param size 设置视频文件大小
     */
    public void setSize(long size) { this.size = size; }
}
