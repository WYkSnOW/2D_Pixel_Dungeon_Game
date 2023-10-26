package com.example.myapplication.Model.entities.Player.playerStartegy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.helper.GameConstants;

public interface PlayerCharStrategy {


    abstract void initCharAtkBoxInfo();
    abstract void initAtkEffect();
    abstract void initChar();

    default void initStrategy()  {
        initChar();
        updateCharAtkBox();
        initCharAtkBoxInfo();
        initAtkEffect();
    }

    abstract int getAnimMaxIndex(PlayerStates state);
    abstract void drawAttackBox(Canvas c);



    default void drawPlayer(Canvas c) {
        c.drawBitmap(//在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                getPlayerSprite(),
                getPlayerLeft(),
                getPlayerTop(),
                null
        );
        c.drawRect(Player.getInstance().getHitBox(), Player.getInstance().getHitBoxPaint());
        if (Player.getInstance().isAttacking()) {
            Player.getInstance().drawAtk(c);
        }
    }

    default float getPlayerLeft() {
        return Player.getInstance().getHitBox().left - offSetX(); //此处减去的为碰撞箱盒实际素材的误差
    }
    default float getPlayerTop() {
        return Player.getInstance().getHitBox().top - Player.getInstance().getHitBoxOffSetY();
    }
    default Bitmap getPlayerSprite() {
        return Player.getInstance().getGameCharType().getSprite(
                Player.getInstance().getPlayerStates().getAnimRow() + Player.getInstance().getFaceDir(),
                Player.getInstance().getAniIndex()
        );
    }


//    default int getPlayerDrawDir(boolean attacking) {
//        if (!attacking) {
//            return Player.getInstance().getDrawDir();
//        }
//        return Player.getInstance().getFaceDir() + 4; //return row that have attacking anim
//    }


//    default int getPlayerDrawDir() {
//        return Player.getInstance().getPlayerStates().getAnimRow()
//                + Player.getInstance().getFaceDir();
//    }



    default int offSetX() {
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
            return Player.getInstance().getHitBoxOffsetX();
        }
        return 0;
    }


    default void updateCharAtkBox() {
        PointF pos = getAtkBoxPos();
        float w = Player.getInstance().getAttackWidth();
        float h = Player.getInstance().getAttackHeight();
        float bottom = pos.y + GameConstants.Sprite.SIZE; //(SIZE = 6 * 16 = 96)
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            Player.getInstance().setAttackBox(new RectF(pos.x, bottom - h, pos.x + w, bottom));
        } else {
            Player.getInstance().setAttackBox(new RectF(pos.x - w, bottom - h, pos.x, bottom));
        }
    }
    default PointF getAtkBoxPos() {
        PointF hitBox;
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
            hitBox = new PointF(
                    Player.getInstance().getHitBox().left,
                    Player.getInstance().getHitBox().top
            );
        } else if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            hitBox = new PointF(
                    Player.getInstance().getHitBox().right,
                    Player.getInstance().getHitBox().top
            );
        } else {
            throw new IllegalStateException(
                    "Unexpected value: " + Player.getInstance().getFaceDir()
            );
        }
        return hitBox;
    }

}
