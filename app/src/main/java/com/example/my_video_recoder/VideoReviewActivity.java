package com.example.my_video_recoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class VideoReviewActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private RecyclerView mRecycleView;
    private List<VideoInfo> mVideoSet;
    private VideoDataAdapter mVideoDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_review);
        initView();
        startScanTask();
    }
    private void initView(){
        mProgressBar = findViewById(R.id.mprogressBar);
        mRecycleView = findViewById(R.id.videorv);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private void tendToVideo(VideoInfo v){
        Intent s = new Intent(this,VideoActivity.class);
        s.putExtra("path",v.getPath());
        startActivity(s);
    }

    private void startScanTask(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ScannerAnsyTask ansyTask=new ScannerAnsyTask(VideoReviewActivity.this);
                    ansyTask.execute();
                    mVideoSet = ansyTask.get();
                    Log.d("filemanager", "run: 视频数量为"+mVideoSet.size());
                    if(mVideoSet!=null){
                        mHandler.sendEmptyMessage(1);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
            if(msg.what==1){
                mProgressBar.setVisibility(View.GONE);
                if(mVideoSet.size()>0){
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
                }else{
                    Toast.makeText(VideoReviewActivity.this, "没有视频文件", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}