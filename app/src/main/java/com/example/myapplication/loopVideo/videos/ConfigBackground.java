package com.example.myapplication.loopVideo.videos;

import android.graphics.PointF;

import com.example.myapplication.loopVideo.BackVideo;
import com.example.myapplication.loopVideo.GameVideos;


public class ConfigBackground extends BackVideo {
    public ConfigBackground() {
        super(new PointF(0, 0), GameVideos.CONFIG_BACK_VIDEO);
    }
    public void update(double delta) {
        //if (movePlayer) {
        updateAnimation(11, 15);
        //}
    }
}
