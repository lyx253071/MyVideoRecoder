package com.example.myvideorecoderlib.exception;

public enum ResultCode {

    /** 结果信息 */
    SUCCESS(1, "成功"),
    CAMERA_DENY(10001, "没有相机权限"),
    CAMERA_INIT_ERROR(10002, "Camera启动失败，请检查参数配置  "),
    MICS_DENY(10003, "没有麦克风权限"),
    MEDIARECORDER_INIT_ERROR(10004, "MediaRecorder启动失败,请检查参数配置  "),
    MEDIARECORDER_STOP_ERROR(10005, "MediaRecorder停止失败"),
    LACK_THE_CALLBACK(10006,"缺少回调函数");

    //上面的逗号可去可不去, 并不会导致编译错误

    private Integer code;

    private String message;

    //构造函数默认并且必须是private
    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
