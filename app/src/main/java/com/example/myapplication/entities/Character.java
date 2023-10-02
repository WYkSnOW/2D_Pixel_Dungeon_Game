package com.example.myapplication.entities;

import android.graphics.PointF;

import com.example.myapplication.helper.GameConstants;

public abstract class  Character extends Entity {
    protected int aniTick, aniIndex;
    protected int faceDir = GameConstants.Face_Dir.RIGHT;
    protected int drawDir = GameConstants.Face_Dir.RIGHT;
    protected final GameCharacters gameCharType;




    public Character(PointF pos, GameCharacters gameCharType) {
        super(pos, 1, 1);
        this.gameCharType = gameCharType;
    }


    protected void updateAnimation() {
        //if(!movePlayer){
            //停止移动后停止动画循环，可将idle动画在这里实现（会停止共用此更新的怪物的动画）
            //return;
        //}
        aniTick++;
        if(aniTick >= GameConstants.Animation.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GameConstants.Animation.AMOUNT)
                aniIndex = 0;
        }
    }
    public void resetAnimation() {
        aniTick = 0;
        aniIndex = 0;
    }


    public int getDrawDir() {
        return drawDir;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getFaceDir() {
        return faceDir;
    }
    public void setFaceDir(int faceDir) {
        this.faceDir = faceDir;
    }

    public void setDrawDir(int drawDir) {
        this.drawDir = drawDir;
    }

    public GameCharacters getGameCharType() {
        return gameCharType;
    }
}
