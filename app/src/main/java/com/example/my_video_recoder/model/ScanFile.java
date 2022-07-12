package com.example.my_video_recoder.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.my_video_recoder.Bean.VideoInfo;
import com.example.my_video_recoder.presenter.VideoPresenter;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 /**
  *
  * @ClassName:      ScanFile
  * @Description:    获取指定文件夹的视频数据
  * @Author:         Lyx25301
  * @Version:        1.0
  */
public class ScanFile implements LoadData {
    private List<VideoInfo> mVideoInfos;
    private File mFile;
    private ExecutorService mThreadPool;

    public ScanFile(List<VideoInfo> videoInfos, File file) {
        this.mVideoInfos = videoInfos;
        this.mFile = file;
        mThreadPool = Executors.newSingleThreadExecutor();
    }

     /**
      *
      * @brief 获取指定文件夹的视频文件
      * @param  list<VideoInfo>  视频文件集合
      * @param  file                  指定文件夹
      * @return
      */
    private void getVideoFile(final List<VideoInfo> list, @NonNull  File file) throws Exception {
        if(file==null || list==null){
            throw new Exception("参数非法");
        }
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
                        if(file.length()>0){
                            video.setDisplayName(file.getName());
                            video.setPath(file.getAbsolutePath());
                            video.setSize(file.length());
                            Log.i("filemanager","path"+video.getPath());
                            list.add(video);
                        }
                        return true;
                    }
                    //判断是不是目录
                } else if (file.isDirectory()) {
                    try {
                        getVideoFile(list, file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }

    //加载数据
    @Override
    public void load()  {
//        new Thread().start();
        Runnable loadTask = new Runnable() {
            @Override
            public void run() {
                Log.d("runable", "run: start");
                try {
                    getVideoFile(mVideoInfos, mFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    mLoadResult.failed(7,e.getMessage());
                }

                try{
                    mLoadResult.succeed();
                }catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ScanFile exception", "LoadResult Callback设置异常");
                }

                Log.d("runable", "run: end");

            }
        };
        mThreadPool.submit(loadTask);
    }

    /**
     *
     * @brief 取消加载数据的任务
     */
    public void cancle() {
        mThreadPool.shutdownNow();
        Log.d("runable", "cancle: ");
    }

    /**
     *
     * @brief 加载结果的回调方法
     */
    public interface LoadResult{
        //加载成功
        public void succeed();
        //加载失败
        public void failed(int code,String msg);
    }

    private LoadResult mLoadResult;

    public void setLoadResult(LoadResult l){
        this.mLoadResult = l;
    }

}
