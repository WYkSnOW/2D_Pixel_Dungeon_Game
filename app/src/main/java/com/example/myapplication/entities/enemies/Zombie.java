package com.example.myapplication.entities.enemies;

import static com.example.myapplication.StartScreen.GAME_HEIGHT;
import static com.example.myapplication.StartScreen.GAME_WIDTH;

import android.graphics.PointF;

import com.example.myapplication.entities.Character;
import com.example.myapplication.entities.GameCharacters;
import com.example.myapplication.helper.GameConstants;

import java.util.Random;

public class Zombie extends Character {
    private long lastDirChange = System.currentTimeMillis();
    private Random rand = new Random();
    public Zombie(PointF pos) {
        super(pos, GameCharacters.MOB1);
    }

    public void update(double delta) {
        updateMove(delta);
        updateAnimation();
    }


    private void updateMove(double delta) {
        if (System.currentTimeMillis() - lastDirChange >= 3000) { // 距离上次改变方向，5000毫秒即五秒后改变怪物方向
            faceDir = rand.nextInt(4);
            lastDirChange = System.currentTimeMillis(); //更新改变方向的时间
        }
        if (faceDir == 0 || faceDir == 1) {
            drawDir = faceDir;       //若新方向为左或右，则改变为对应动画，若为上或下则保留原先的数值
        }

        switch (faceDir) { //检测是否碰撞到屏幕边缘，如果是则将方向翻转(1，将坐标位置改变，2.通过改变Face_Dir改变动画行数，变为对应目标的动画
            case GameConstants.Face_Dir.DOWN:
                hitBox.top += delta * 300;
                if (hitBox.top >= GAME_HEIGHT) { //2220为屏幕像素
                    faceDir = GameConstants.Face_Dir.UP;
                    lastDirChange = System.currentTimeMillis();
                }
                break;
            case GameConstants.Face_Dir.UP:
                hitBox.top -= delta * 300;
                if (hitBox.top <= 0) {
                    faceDir = GameConstants.Face_Dir.DOWN;
                    lastDirChange = System.currentTimeMillis();
                }
                break;
            case GameConstants.Face_Dir.RIGHT:
                hitBox.left += delta * 300;
                if (hitBox.left >= GAME_WIDTH) {
                    faceDir = GameConstants.Face_Dir.LEFT;
                    drawDir = 1;
                    lastDirChange = System.currentTimeMillis();
                }
                break;
            case GameConstants.Face_Dir.LEFT:
                hitBox.left -= delta * 300;
                if (hitBox.left <= 0) {
                    faceDir = GameConstants.Face_Dir.RIGHT;
                    drawDir = 0;
                    lastDirChange = System.currentTimeMillis();
                }
                break;
        }
    }



}
