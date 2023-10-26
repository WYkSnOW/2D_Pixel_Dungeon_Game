package com.example.myapplication.Model.entities;

import android.graphics.PointF;

import com.example.myapplication.Model.helper.GameConstants;

public abstract class  Character extends Entity {
    protected int aniTick;
    protected int aniIndex;
    protected int moveDir = GameConstants.MoveDir.RIGHT;
    protected int drawDir = GameConstants.DrawDir.RIGHT;
    protected int faceDir = GameConstants.FaceDir.RIGHT;
    protected GameCharacters gameCharType;

    protected int characterWidth;
    protected int characterHeight;
    protected int hitBoxOffsetX;
    protected int hitBoxOffSetY;



    public Character(PointF pos, GameCharacters gameCharType) { //创建一个碰撞箱(不同于角色素材的实际尺寸 -> 通常更小)
        super(
                pos,
                gameCharType.getCharacterWidth() - gameCharType.getHitBoxOffSetX(),
                gameCharType.getCharacterHeight() - gameCharType.getHitBoxOffSetY()
        );


        this.gameCharType = gameCharType;
        this.characterWidth = gameCharType.getCharacterWidth();
        this.characterHeight = gameCharType.getCharacterHeight();
        this.hitBoxOffsetX = gameCharType.getHitBoxOffSetX();
        this.hitBoxOffSetY = gameCharType.getHitBoxOffSetY();
    }


    protected void updateAnimation() {
        aniTick++;
        if (aniTick >= GameConstants.Animation.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= gameCharType.getMaxAnimIndex()) {
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

    public int getDrawDir() {
        return drawDir;
    }
    public void setDrawDir(int drawDir) {
        this.drawDir = drawDir;
    }

    public int getMoveDir() {
        return moveDir;
    }
    public void setMoveDir(int moveDir) {
        this.moveDir = moveDir;
    }

    public void setFaceDir(int faceDir) {
        this.faceDir = faceDir;
    }

    public int getFaceDir() {
        return faceDir;
    }

    public GameCharacters getGameCharType() {
        return gameCharType;
    }

    public int getCharacterWidth() {
        return characterWidth;
    }
    public int getCharacterHeight() {
        return characterHeight;
    }

    public int getHitBoxOffsetX() {
        return hitBoxOffsetX;
    }

    public int getHitBoxOffSetY() {
        return hitBoxOffSetY;
    }


}
