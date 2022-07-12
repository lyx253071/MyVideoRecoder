package com.example.myvideorecoderlib.exception;

import androidx.annotation.NonNull;

 /**
  *
  * @ClassName:      RecordException
  * @Description:    描述录制的异常信息
  * @Author:         lyx253071
  */
public class RecordException extends Exception {

    //异常代码
    private int code;
    //异常信息
    private String msg;
    
    /**
     *
     * @return  异常信息代码 
     */
    public int getCode() {
        return code;
    }
    
    /**
     *
     * @param code 设置异常信息代码
     */
    public void setCode(@NonNull int code) {
        this.code = code;
    }
    
    /**
     * 
     * @return 异常信息详情
     */
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @param msg 设置异常信息
     */
    public void setMsg(@NonNull String msg) {
        this.msg = msg;
    }
}
