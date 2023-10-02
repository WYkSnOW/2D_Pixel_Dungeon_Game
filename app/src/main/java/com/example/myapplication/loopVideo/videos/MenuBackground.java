package com.example.myapplication.loopVideo.videos;

import android.graphics.PointF;

import com.example.myapplication.loopVideo.BackVideo;
import com.example.myapplication.loopVideo.GameVideos;


public class MenuBackground extends BackVideo {
    public MenuBackground() {
        super(new PointF(0, 0), GameVideos.MAIN_BACK_VIDEO);
    }
    public void update(double delta) {
        //if (movePlayer) {
        updateAnimation(8,6);
        //}
    }
}
