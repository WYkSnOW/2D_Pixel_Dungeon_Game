package com.example.myapplication.Model.entities.enemies;

import static com.example.myapplication.View.main.MainActivity.gameHeight;
import static com.example.myapplication.View.main.MainActivity.gameWidth;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Character;
import com.example.myapplication.Model.helper.GameConstants;

import java.util.Random;

public class Zombie extends Character {
    private long lastDirChange = System.currentTimeMillis();
    private Random rand = new Random();
    public Zombie(PointF pos) {
        super(pos, GameCharacters.ZOMBIE);
    }

    public void update(double delta) {
        updateMove(delta);
        updateAnimation();
    }


    private void updateMove(double delta) {
        if (System.currentTimeMillis() - lastDirChange >= 3000) { // 距离上次改变方向，5000毫秒即五秒后改变怪物方向
            moveDir = rand.nextInt(4);
            lastDirChange = System.currentTimeMillis(); //更新改变方向的时间
        }
        if (moveDir == 0 || moveDir == 1) {
            drawDir = moveDir;       //若新方向为左或右，则改变为对应动画，若为上或下则保留原先的数值
        }

        //检测是否碰撞到屏幕边缘，如果是则将方向翻转(1，将坐标位置改变，2.通过改变Face_Dir改变动画行数，变为对应目标的动画
        if (moveDir == GameConstants.MoveDir.DOWN) {
            hitBox.top += delta * 300;  //updating hitBox
            hitBox.bottom += delta * 300; //300 is the speed
            if (hitBox.top >= gameHeight) { //2220为屏幕像素
                moveDir = GameConstants.MoveDir.UP;
                lastDirChange = System.currentTimeMillis();
            }
        } else if (moveDir == GameConstants.MoveDir.UP) {
            hitBox.top -= delta * 300;
            hitBox.bottom -= delta * 300;
            if (hitBox.top <= 0) {
                moveDir = GameConstants.MoveDir.DOWN;
                lastDirChange = System.currentTimeMillis();
            }
        } else if (moveDir == GameConstants.MoveDir.RIGHT) {
            hitBox.left += delta * 300;
            hitBox.right += delta * 300;
            if (hitBox.left >= gameWidth) {
                moveDir = GameConstants.MoveDir.LEFT;
                drawDir = 1;
                lastDirChange = System.currentTimeMillis();
            }
        } else if (moveDir == GameConstants.MoveDir.LEFT) {
            hitBox.left -= delta * 300;
            hitBox.right -= delta * 300;
            if (hitBox.left <= 0) {
                moveDir = GameConstants.MoveDir.RIGHT;
                drawDir = 0;
                lastDirChange = System.currentTimeMillis();
            }
        }
    }



}
