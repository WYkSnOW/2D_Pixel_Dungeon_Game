package com.example.myapplication.Model.loopVideo;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.Entity;

public abstract class VideoFrame extends Entity {
    protected int aniTick;
    protected int aniIndex;
    protected final GameVideos gameVideoType;
    private final RectF hitbox;
    private boolean pushed;

    //help set up video(background/special effects
    public VideoFrame(PointF pos, int width, int height, GameVideos gameVideoType) {
        super(pos, 1, 1);
        this.gameVideoType = gameVideoType;
        hitbox = new RectF(pos.x, pos.y, pos.x + width, pos.y + height);

    }
    protected void updateAnimation() {
        aniTick++;
        if (aniTick >= gameVideoType.getAnimRate()) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= gameVideoType.getMaxAnimIndex()) {
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
    public RectF getHitbox() {
        return hitbox;
    }
    public boolean isPushed() {
        return pushed;
    }
    public void setPushed(boolean pushed) {
        this.pushed = pushed;
    }
}
