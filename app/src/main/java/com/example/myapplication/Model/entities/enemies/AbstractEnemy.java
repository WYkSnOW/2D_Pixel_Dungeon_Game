package com.example.myapplication.Model.entities.enemies;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Character;
import com.example.myapplication.Model.environments.GameMap;
import com.example.myapplication.Model.environments.MapManager;
import com.example.myapplication.Model.helper.GameConstants;

import java.util.Random;
public abstract class AbstractEnemy extends Character {
    private long lastDirChange = System.currentTimeMillis();
    private Random rand = new Random();
    private float baseSpeed;
    private int atk;
    private float currentSpeed;
    public AbstractEnemy(PointF pos, GameCharacters characterType, float baseSpeed, int atk) {
        super(pos, characterType);
        this.baseSpeed = baseSpeed;
        this.atk = atk;
    }

    public void update(double delta, MapManager mapManager, PointF playerPos) {
        updateMove(delta, mapManager, playerPos);
        updateAnimation();
    }

    private void updateMove(double delta, MapManager mapManager, PointF playerPos) {
        GameMap gameMap = mapManager.getCurrentMap();
        //defaultMoveMode(delta, gameMap);

        float facePoint = hitBox.left;
        if (drawDir == GameConstants.DrawDir.RIGHT) {
            facePoint = hitBox.right;
        }

        currentSpeed = (float) delta * baseSpeed;
        float distanceX = hitBox.centerX() - playerPos.x;
        float distanceY = hitBox.centerY() - playerPos.y;
        double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

        if (distance <= 4 * GameConstants.Sprite.SIZE) { //怪物检测范围，单位为格
            chaseMoveMode(distanceX, distanceY, gameMap, facePoint);
        } else {
            defaultMoveMode(gameMap, facePoint);
        }

    }

    private void chaseMoveMode(
            float distanceX,
            float distanceY,
            GameMap gameMap,
            float facePoint) {
        float ratio = Math.abs(distanceY) / Math.abs(distanceX);
        double angle = Math.atan(ratio); //怪物与玩家的角度
        float xSpeed = (float) Math.cos(angle); //用角度与直线速度计算斜向速度
        float ySpeed = (float) Math.sin(angle);
        float deltaX = xSpeed * currentSpeed; //移动镜头而不是角色
        float deltaY = ySpeed * currentSpeed; //因镜头需与角色相反的方向移动，即乘以-1


        if (xSpeed > ySpeed) { //意味着x角度更大，角色应该随着x调整     实现角色移动时的动画切换
            if (distanceX > 0) { //正数意味光标在圆环右侧，即朝右移动
                moveDir = GameConstants.MoveDir.LEFT;
            } else {
                moveDir = GameConstants.MoveDir.RIGHT;
            }
        } else {
            if (distanceY > 0) {
                moveDir = GameConstants.MoveDir.UP;
            } else {
                moveDir = GameConstants.MoveDir.DOWN;
            }
        }

        if (distanceX > 1 && gameMap.canMoveHere(hitBox.left, hitBox.top, hitBox.bottom)) {
            hitBox.left -= deltaX;
            hitBox.right -= deltaX;
            drawDir = GameConstants.DrawDir.LEFT;
            faceDir = GameConstants.FaceDir.LEFT;
        } else if (distanceX < -1 && gameMap.canMoveHere(hitBox.right, hitBox.top, hitBox.bottom)) {
            hitBox.left += deltaX;
            hitBox.right += deltaX;
            drawDir = GameConstants.DrawDir.RIGHT;
            faceDir = GameConstants.FaceDir.RIGHT;
        }
        if (distanceY > 1 && gameMap.canMoveHere(facePoint, hitBox.top, hitBox.top)) {
            hitBox.top -= deltaY;
            hitBox.bottom -= deltaY;
        } else if (distanceY < -1 && gameMap.canMoveHere(facePoint, hitBox.bottom, hitBox.bottom)) {
            hitBox.top += deltaY;  //updating hitBox
            hitBox.bottom += deltaY;
        }
    }

    private void defaultMoveMode(GameMap gameMap, float facePoint) {
        if (System.currentTimeMillis() - lastDirChange >= 3000) { // 距离上次改变方向，3000毫秒即3秒后改变怪物方向
            moveDir = rand.nextInt(4);
            lastDirChange = System.currentTimeMillis(); //更新改变方向的时间
        }
        if (moveDir == 0 || moveDir == 1) {
            drawDir = moveDir;       //若新方向为左或右，则改变为对应动画，若为上或下则保留原先的数值
        }

        //检测是否碰撞到屏幕边缘，如果是则将方向翻转(1，将坐标位置改变，2.通过改变Face_Dir改变动画行数，变为对应目标的动画
        if (moveDir == GameConstants.MoveDir.DOWN) {
            hitBox.top += currentSpeed;  //updating hitBox
            hitBox.bottom += currentSpeed; //300 is the speed
            if (!(gameMap.canMoveHere(facePoint, hitBox.bottom, hitBox.bottom))) { //2220为屏幕像素
                moveDir = GameConstants.MoveDir.UP;
            }
        } else if (moveDir == GameConstants.MoveDir.UP) {
            hitBox.top -= currentSpeed;
            hitBox.bottom -= currentSpeed;
            if (!(gameMap.canMoveHere(facePoint, hitBox.top, hitBox.top))) {
                moveDir = GameConstants.MoveDir.DOWN;
            }
        } else if (moveDir == GameConstants.MoveDir.RIGHT) {
            hitBox.left += currentSpeed;
            hitBox.right += currentSpeed;
            if (!(gameMap.canMoveHere(hitBox.right, hitBox.top, hitBox.bottom))) {
                moveDir = GameConstants.MoveDir.LEFT;
                drawDir = GameConstants.DrawDir.LEFT;
            }
        } else if (moveDir == GameConstants.MoveDir.LEFT) {
            hitBox.left -= currentSpeed;
            hitBox.right -= currentSpeed;
            if (!(gameMap.canMoveHere(hitBox.left, hitBox.top, hitBox.bottom))) {
                moveDir = GameConstants.MoveDir.RIGHT;
                drawDir = GameConstants.DrawDir.RIGHT;
            }
        }
    }

    public void setBaseSpeed(float baseSpeed) {
        this.baseSpeed = baseSpeed;
    }
}
