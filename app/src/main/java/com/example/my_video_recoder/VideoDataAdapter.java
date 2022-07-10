package com.example.my_video_recoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.my_video_recoder.Bean.VideoInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoDataAdapter extends RecyclerView.Adapter<VideoDataAdapter.MyViewHolder>{

    private static double M = 1024*1024.0;
    private List<VideoInfo> mVideoSet = new ArrayList<>();
    private Context mContext;
    //glide的相关配置
    RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.video) //图片加载出来前，显示的图片
            .fallback( R.drawable.photo); //url为空的时候,显示的图片
//           .error(drawable.img_load_failure);


    public VideoDataAdapter(Context context) {
        this.mContext = context;
    }

    public VideoDataAdapter(Context context, List<VideoInfo> videoSet) {
        this.mVideoSet = videoSet;
        this.mContext = context;
    }

    public void setVideoSet(List<VideoInfo> videoSet) {
        this.mVideoSet = videoSet;
    }


    @Override
    public VideoDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_view,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoDataAdapter.MyViewHolder holder, int position) {
        //设置视频缩略图
        Glide.with(mContext).load( Uri.fromFile( new File( mVideoSet.get(position).getPath() ) ) ).apply(options)
                .into( holder.type);
        holder.fileName.setText(mVideoSet.get(position).getDisplayName());
        String fs = String.format("%.2f",mVideoSet.get(position).getSize()/M)+"M";
        holder.fileSize.setText(fs);
    }

    @Override
    public int getItemCount() {
        return mVideoSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView type;//文件缩略图
        private TextView fileName;//文件名
        private TextView fileSize;//文件大小

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.iv_type);
            fileName = itemView.findViewById(R.id.file_name);
            fileSize=itemView.findViewById(R.id.file_size);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.click(itemView,getAdapterPosition());
                    }
                }
            });
        }
    }


    /***
     * 根据播放路径设置缩略图
     * @param filePath 视频资源的路径
     * @return 返回缩略图的Bitmap对象
     */
    public Bitmap getVideoThumbNail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    //设置点击接口
    public interface OnItemClickListener {
        void click(View view, int position);
    }
    private OnItemClickListener onItemClickListener;
    //设置点击监听器
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
