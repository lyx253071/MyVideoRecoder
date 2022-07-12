package com.example.my_video_recoder;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.List;

public interface RecorderCfg {
    int DEFAULT_CAMERA = 1;
    int DEFAULT_VIDEO_WIDTH = 1280;
    int DEFAULT_VIDEO_HEIGHT = 720;
    int DEFAULT_FRAME_RATE = 15;
    int DEFAULT_BIT_RATE = 512;
    int DEFAULT_MIN_TIME = 0;
    int DEFAULT_MAX_TIME = 600;

    int CUSTOM_CAMERA = -1;
    int CUSTOM_VIDEO_WIDTH = -1;
    int CUSTOM_VIDEO_HEIGHT = -1;
    int CUSTOM_FRAME_RATE = -1;
    int CUSTOM_BIT_RATE = -1;
    int CUSTOM_MIN_TIME = -1;
    int CUSTOM_MAX_TIME = 10;

}
