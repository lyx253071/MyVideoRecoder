package com.example.myvideorecoderlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myvideorecoderlib.camera.open.CameraFacing;
import com.example.myvideorecoderlib.recoder.RecordConfig;
import com.example.myvideorecoderlib.recoder.RecordControllerLayout;
import com.example.myvideorecoderlib.recoder.RecordView;

import java.io.ByteArrayOutputStream;

public class RecoderActivity extends AppCompatActivity {
    private static String TAG = "RecoderActivity";

    //启动 Activity 传输数据标签
    private static final String MinTime = "mintime";
    private static final String MaxTime = "maxtime";
    private static final String VideoWidth = "videowidth";
    private static final String VideoHeight = "videogeight";
    private static final String FrameRate = "framerate";
    private static final String BitRate = "bitrate";
    private static final String AutoOpen = "autoopen";
    private static final String CurCamera = "camera";
    private static final String PackgeName = "packgename";
    private static final String MaskIDS = "maskIDS";

    private  RecordView mRecordView;
    private  RecordControllerLayout mController;
    private  ImageView curMask;
    private  RCCallBack mRCCallback;


    public static void activityStart(Context context, RecordConfig recordConfig) {
        Intent intent = new Intent(context, RecoderActivity.class);
        intent.putExtra(MinTime, recordConfig.getMinTime());
        intent.putExtra(MaxTime, recordConfig.getMaxTime());
        intent.putExtra(VideoWidth,recordConfig.getVideoWidth());
        intent.putExtra(VideoHeight, recordConfig.getVideoHeight());
        intent.putExtra(FrameRate,recordConfig.getFrameRate());
        intent.putExtra(BitRate, recordConfig.getBitRate());
        intent.putExtra(CurCamera, recordConfig.getCamera());
        intent.putExtra(AutoOpen,recordConfig.isAutoOpen());
        //通过Intent传递图片（转换成数组）
        Bitmap mask = recordConfig.getBitmap();
        if(mask!=null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            recordConfig.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
            intent.putExtra("bitmap", baos.toByteArray());
        }

        intent.putExtra(PackgeName,recordConfig.getPackgeName());
        intent.putExtra(MaskIDS,recordConfig.getMaskIDS());

        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_recoder);
//        mRecordView = findViewById(R.id.recordView);
//        mController = findViewById(R.id.layout_controller);
//        mController.bindRecordView(mRecordView);
//        curMask = findViewById(R.id.Mask1);

        setContentView(R.layout.activity_recoder2);
        initView();

        mRCCallback = RecordConfig.getInstance(this).getRCCallBack();



        Intent intent = getIntent();
        CameraFacing curCamera = intent.getIntExtra(CurCamera,0)==0? CameraFacing.BACK : CameraFacing.FRONT;
        int minTime = intent.getIntExtra(MinTime,-1);
        int maxTime = intent.getIntExtra(MaxTime,300);
        int videoWidth = intent.getIntExtra(VideoWidth,1280);
        int videoHeight = intent.getIntExtra(VideoWidth,720);
        int frameRate = intent.getIntExtra(FrameRate,20);
        int bitRate = intent.getIntExtra(BitRate,512);
        boolean autoOpen = intent.getBooleanExtra(AutoOpen,false);
        String packgeName = intent.getStringExtra(PackgeName);
        int[] masks = intent.getIntArrayExtra(MaskIDS);

        //配置蒙版
        byte[] bitmapByte = intent.getByteArrayExtra("bitmap");
        if(bitmapByte!=null && bitmapByte.length>0){
            Bitmap bitmap= null;
            bitmap= BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length, null);
            if(bitmap!=null) curMask.setImageBitmap(bitmap);
        }

        if(masks.length>0) {
            Log.d(TAG, "mask: "+masks[0]);

            curMask.setImageDrawable(getResources().getDrawable(masks[0]));
        }
//        curMask.setImageDrawable(remoteContext.getResources().getDrawable(identifier));

        curMask.setVisibility(View.GONE);
        mController.setDuration(minTime,maxTime);
        mRecordView.setCameraFacing(curCamera);
        mRecordView.setVideoWidth(videoWidth);
        mRecordView.setVideoHeight(videoHeight);
        mRecordView.setFrameRate(frameRate);
        mRecordView.setBitRate(bitRate);
        mRecordView.setAutoOpen(autoOpen);

        //设置录制监听器
        mController.setOnRecordListener(new RecordControllerLayout.OnRecordListener() {
            @Override
            public void onStartRecord() {
                Toast.makeText(RecoderActivity.this, "开始录制", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopRecord(long duration,String filePath) {
                Toast.makeText(RecoderActivity.this, "结束录制，时长" + duration, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onStopRecord: 存储在"+filePath);
                if(mRCCallback!=null) mRCCallback.succed(filePath);
            }


            @Override
            public void onCancelRecord() {
                Toast.makeText(RecoderActivity.this, "取消录制", Toast.LENGTH_SHORT).show();
                if(mRCCallback!=null) mRCCallback.failed();
            }
        });

        //设置蒙版监听器
        mRecordView.setMaskListener(new MaskListener() {
            @Override
            public void enable() {
                curMask.setVisibility(View.VISIBLE);
            }

            @Override
            public void disable() {

            }

            @Override
            public void next() {

            }
        });

        mRecordView.verifyPermissions(RecoderActivity.this,1);
        Log.d("Recoder Resources", "onCreate: "+getResources());
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mRecordView.openCamera();
                mRecordView.setReady();
            }
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG", "onStop: "+"停止录制");
        mController.cancelRecord();
        mRecordView.closeCamera();
    }


    //设置自适应view
    private void initView(){
        mRecordView = findViewById(R.id.recordView2);
        mController = findViewById(R.id.layout_controller2);
        mController.bindRecordView(mRecordView);
        curMask = findViewById(R.id.Mask2);
    }

}