package com.example.my_video_recoder;
 /**
  *
  * @ClassName:      
  * @Description:    java类作用描述
  * @Author:         author
  * @CreateDate:     
  * @UpdateUser:     updater
  * @UpdateDate:     
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */
public interface RecorderCfg {
    int DEFAULT_CAMERA = 1;
    int DEFAULT_VIDEO_WIDTH = 1920;
    int DEFAULT_VIDEO_HEIGHT = 1080;
    int DEFAULT_FRAME_RATE = 15;
    int DEFAULT_BIT_RATE = 256;
    int DEFAULT_MIN_TIME = 0;
    int DEFAULT_MAX_TIME = 600;

    int CUSTOM_CAMERA = -1;
    int CUSTOM_VIDEO_WIDTH = -1;
    int CUSTOM_VIDEO_HEIGHT = -1;
    int CUSTOM_FRAME_RATE = -1;
    int CUSTOM_BIT_RATE = -1;
    int CUSTOM_MIN_TIME = -1;
    int CUSTOM_MAX_TIME = 15;

}
