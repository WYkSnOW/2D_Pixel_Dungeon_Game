package com.example.myapplication.loopVideo;

import android.graphics.PointF;

import com.example.myapplication.entities.Entity;

public abstract class BackVideo extends Entity {
    protected int aniTick, aniIndex;

    protected final GameVideos gameVideoType;




    public BackVideo(PointF pos, GameVideos gameVideoType) {
        super(pos, 1, 1);
        this.gameVideoType = gameVideoType;
    }


    protected void updateAnimation(int maxIndex, int aniRate) {
        //if(!movePlayer){
            //停止移动后停止动画循环，可将idle动画在这里实现（会停止共用此更新的怪物的动画）
            //return;
        //}
        aniTick++;
        if (aniTick >= aniRate) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= maxIndex) {
                aniIndex = 0;
            }
        }
    }
    public void resetAnimation() {
        aniTick = 0;
        aniIndex = 0;
    }



    public int getAniIndex() {
        return aniIndex;
    }

    public GameVideos getGameVideoType() {
        return gameVideoType;
    }
}
