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
    abstract void initChar();
    abstract void initBaseSpeed();

    default void initStrategy()  {
        initChar();
        updateAtkBox();
        initCharAtkBoxInfo();
        initBaseSpeed();
    }
    abstract int offSetX();
    abstract int offSetY();
    abstract int getAnimMaxIndex(PlayerStates state);
    abstract void drawAttackBox(Canvas c);

    abstract void updateAtkBoxWhenAttacking();


    default float getCurrentSpeed(float baseSpeed, PlayerStates state) {
        if (state == PlayerStates.RUNNING) {
            return baseSpeed * 2;
        }
        return baseSpeed;
    }

    default void updateAtkBox() {
        PlayerStates state = Player.getInstance().getCurrentStates();
        if (state == PlayerStates.ATTACK) {
            updateAtkBoxWhenAttacking();

        }
    }







    default void drawPlayer(Canvas c) {

        c.drawBitmap(//在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                getPlayerSprite(),
                getPlayerLeft(),
                getPlayerTop(),
                null
        );

        c.drawRect(Player.getInstance().getHitBox(), Player.getInstance().getHitBoxPaint());
        if (Player.getInstance().isAbleMakeDamage()) {
            Player.getInstance().drawAtk(c);
        }
    }

    default float getPlayerLeft() {
        return Player.getInstance().getHitBox().left - offSetX(); //此处减去的为碰撞箱盒实际素材的误差
    }
    default float getPlayerTop() {
        return Player.getInstance().getHitBox().top - offSetY();
    }
    default Bitmap getPlayerSprite() {
        return Player.getInstance().getGameCharType().getSprite(
                Player.getInstance().getCurrentStates().getAnimRow() + Player.getInstance().getFaceDir(),
                Player.getInstance().getAniIndex()
        );
    }

}
