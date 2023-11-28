package com.example.myapplication.Model.helper.playerMoveStartegy;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.HelpMethods;

public class PlayerRun implements PlayerMoveStrategy {
    @Override
    public void setPlayerAnim(float xSpeed, float ySpeed, PointF lastTouchDiff) {

        if (xSpeed > ySpeed) { //意味着x角度更大，角色应该随着x调整     实现角色移动时的动画切换
            if (lastTouchDiff.x > 0) { //正数意味光标在圆环右侧，即朝右移动
                Player.getInstance().setMoveDir(GameConstants.MoveDir.RIGHT);
            } else {
                Player.getInstance().setMoveDir(GameConstants.MoveDir.LEFT);
            }
        } else {
            if (lastTouchDiff.y > 0) {
                Player.getInstance().setMoveDir(GameConstants.MoveDir.DOWN);
            } else {
                Player.getInstance().setMoveDir(GameConstants.MoveDir.UP);
            }
        }
        if (lastTouchDiff.x >= 0) {
            Player.getInstance().setDrawDir(GameConstants.DrawDir.RIGHT);
            Player.getInstance().setFaceDir(GameConstants.FaceDir.RIGHT);
        }  else {
            Player.getInstance().setDrawDir(GameConstants.DrawDir.LEFT);
            Player.getInstance().setFaceDir(GameConstants.FaceDir.LEFT);
        }

    }

    @Override
    public PointF playerMovement(float xSpeed, float ySpeed, float baseSpeed) {
        return HelpMethods.playerMovementRun(xSpeed, ySpeed, baseSpeed);
    }
}
