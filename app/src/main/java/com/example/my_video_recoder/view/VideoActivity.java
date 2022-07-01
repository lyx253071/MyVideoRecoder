package com.example.my_video_recoder.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.my_video_recoder.R;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent intent=getIntent();
        String path=intent.getStringExtra("path");

        VideoView videoView= findViewById(R.id.videoView);
        videoView.setVideoPath(path);
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }
}