package com.example.my_video_recoder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.my_video_recoder.view.VideoReviewActivity;
import com.example.myvideorecoderlib.RecordCallBack;
import com.example.myvideorecoderlib.VideoRecorder;

public class FirstActivity extends AppCompatActivity {
    private Button mRecodeButton;
    private Button mReviewButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mRecodeButton = findViewById(R.id.recode);
        mReviewButton = findViewById(R.id.review);

        mReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toReview();
            }
        });
        mRecodeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                toRecord();
            }
        });
    }
    //跳转到录制页面
    private void toRecord(){
        VideoRecorder videoRecorder = new VideoRecorder(this);

//        videoRecorder.setVideoHeight(1920);
//        videoRecorder.setVideoHeight(1080);

        videoRecorder = new VideoRecorder(this,RecorderCfg.class);

//        videoRecorder.setMaxTime(15);

//        videoRecorder.addMask(R.drawable.app_record_mask_head);
//        videoRecorder.addMask(R.drawable.app_record_mask_rc2);

        videoRecorder.setCallback(new RecordCallBack() {
            @Override
            public void succed(String filePath) {
                Log.d("TAGcallback", "succed: "+filePath);
            }

            @Override
            public void failed(int code,String msg) {
                Log.d("TAGcallback", "failed: "+msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        videoRecorder.startRecod();

    }

    //跳转到视频文件页面
    private void toReview(){

        Intent intent = new Intent(this, VideoReviewActivity.class);
        startActivity(intent);
    }

}