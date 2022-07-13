package com.example.my_video_recoder.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.my_video_recoder.R;
import com.example.my_video_recoder.VideoDataAdapter;
import com.example.my_video_recoder.Bean.VideoInfo;
import com.example.my_video_recoder.presenter.VideoPresenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class VideoReviewActivity extends AppCompatActivity implements VideoReview {
    private ProgressBar mProgressBar;
    private RecyclerView mRecycleView;
    private List<VideoInfo> mVideoSet;
    private VideoDataAdapter mVideoDataAdapter;
    private VideoPresenter mVideoPresenter;
    private final MyHandler myHandler =  new MyHandler(this);

    public static final int LOAD_SUCCESS = 1;
    public static final int LOAD_FAILED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_review);
        mVideoSet = new ArrayList<>();
        mVideoPresenter = new VideoPresenter(this,mVideoSet,getApplication().getExternalFilesDir(null));
        initView();
        mVideoPresenter.loadData();
    }
    private void initView(){
        mProgressBar = findViewById(R.id.mprogressBar);
        mRecycleView = findViewById(R.id.videorv);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        mRecycleView.setVisibility(View.VISIBLE);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mVideoDataAdapter = new VideoDataAdapter(VideoReviewActivity.this,mVideoSet);
        mVideoDataAdapter.setOnItemClickListener(new VideoDataAdapter.OnItemClickListener() {
            @Override
            public void click(View view, int position) {
                VideoInfo curVideo = mVideoSet.get(position);
                tendToVideo(curVideo);
            }
        });
        mRecycleView.setAdapter(mVideoDataAdapter);
    }

    private void tendToVideo(VideoInfo v){
        Intent s = new Intent(this, VideoActivity.class);
        s.putExtra("path",v.getPath());
        startActivity(s);
    }

//    private void startScanTask(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ScannerAnsyTask ansyTask=new ScannerAnsyTask(VideoReviewActivity.this);
//                    ansyTask.execute();
//                    mVideoSet = ansyTask.get();
//                    Log.d("filemanager", "run: 视频数量为"+mVideoSet.size());
//                    if(mVideoSet!=null){
//                        mHandler.sendEmptyMessage(1);
//                    }
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

    //显示视频文件
    private void updateViewAfterSuccess(){
        mProgressBar.setVisibility(View.GONE);
        if(mVideoSet.size()>0){
            mVideoDataAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(VideoReviewActivity.this, "没有视频文件", Toast.LENGTH_SHORT).show();
        }
    }

    //
    private void updateViewAfterFail(String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("fail Message", "onFailed: "+msg);
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(VideoReviewActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private Handler mHandler = new Handler(){
//        public void handleMessage(android.os.Message msg){
//            switch(msg.what){
//                case LOAD_SUCCESS :
//                    mProgressBar.setVisibility(View.GONE);
//                    if(mVideoSet.size()>0){
//                        mVideoDataAdapter.notifyDataSetChanged();
//                    }else{
//                        Toast.makeText(VideoReviewActivity.this, "没有视频文件", Toast.LENGTH_SHORT).show();
//                    }
//                case LOAD_FAILED :
//                    Log.d("fail Message", "onFailed: "+msg);
//                    mProgressBar.setVisibility(View.GONE);
//            }
//        }
//    };

    static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;

        MyHandler(Activity activity) {
            mActivityReference= new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final Activity activity = mActivityReference.get();
            if (activity != null) {
                //...
                if(activity instanceof VideoReviewActivity) {
                    VideoReviewActivity activity1 = (VideoReviewActivity) activity;
                    switch(msg.what){
                        case LOAD_SUCCESS :
                            activity1.updateViewAfterSuccess();
                        default:
                    }
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoPresenter.cancleLoad();

//        if (mHandler != null)  {
//            mHandler.removeCallbacksAndMessages(null);
//        }

    }

    @Override
    public void onSuccess() {
        Log.d(" run success", ":获取成功");
        myHandler.sendEmptyMessage(LOAD_SUCCESS);
    }

    @Override
    public void onFailed(int code, String msg) {
        Log.d(" run exception", "onFailed: "+msg);
        updateViewAfterFail(msg); }
}