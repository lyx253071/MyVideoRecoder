package com.example.my_video_recoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.my_video_recoder.view.VideoReviewActivity;
import com.example.myvideorecoderlib.RCCallBack;
import com.example.myvideorecoderlib.VideoRecoder;

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
                toRecode();
            }
        });
    }

    private void toRecode(){
//        Drawable drawable = getResources().getDrawable(R.drawable.rc2);

        VideoRecoder videoRecoder = new VideoRecoder(this);
        videoRecoder.setMaxTime(15);
        videoRecoder.addMask(R.drawable.head);
        videoRecoder.setCallback(new RCCallBack() {
            @Override
            public void succed(String filePath) {
                Log.d("TAGcallback", "succed: "+filePath);
            }

            @Override
            public void failed() {
                Log.d("TAGcallback", "failed: ");
            }
        });

        videoRecoder.startRecod();
    }

    private void toReview(){

        Intent intent = new Intent(this, VideoReviewActivity.class);
        startActivity(intent);
    }

}