package com.example.myapplication;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class Video extends SurfaceView implements SurfaceHolder.Callback {
    private MediaPlayer mediaPlayer;
    private final int videoResourceId;

    public Video(Context context, int videoResourceId) {
        super(context);
        this.videoResourceId = videoResourceId;

        // 初始化SurfaceHolder，并设置回调
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Surface创建后开始播放视频
        mediaPlayer = MediaPlayer.create(getContext(), videoResourceId);
        mediaPlayer.setDisplay(holder);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 这里可以处理Surface尺寸变化的情况
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface销毁时释放资源
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
