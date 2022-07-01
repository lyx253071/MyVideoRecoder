package com.example.my_video_recoder.Bean;

public class VideoInfo {
    private String displayName;
    private String path;
    private long size;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() { return size; }

    public void setSize(long size) { this.size = size; }
}
