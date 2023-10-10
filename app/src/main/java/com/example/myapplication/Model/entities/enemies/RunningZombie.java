package com.example.myapplication.Model.entities.enemies;



import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.entities.Character;

public class RunningZombie extends Character {
    public RunningZombie(PointF pos) {
        super(pos, GameCharacters.ZOMBIE);
    }

    public void update(double delta) {
        updateMove(delta);
        updateAnimation();
    }


    private void updateMove(double delta) {

        //检测是否碰撞到屏幕边缘，如果是则将方向翻转(1，将坐标位置改变，2.通过改变Face_Dir改变动画行数，变为对应目标的动画
        if (moveDir == GameConstants.MoveDir.RIGHT) {
            hitBox.left += delta * 300;
            if (hitBox.left
                    >= GameConstants.UiSize.GAME_WIDTH
                    - GameCharacters.ZOMBIE.getCharacterWidth()) {
                moveDir = GameConstants.MoveDir.LEFT;
                drawDir = 1;

            }
        } else if (moveDir == GameConstants.MoveDir.LEFT) {
            hitBox.left -= delta * 300;
            if (hitBox.left <= 0) {
                moveDir = GameConstants.MoveDir.RIGHT;
                drawDir = 0;
            }
        }

    }



}