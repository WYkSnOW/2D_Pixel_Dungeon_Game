package com.example.myapplication.Model.gameStatesLogic;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.entities.enemies.AbstractEnemy;
import com.example.myapplication.Model.environments.MapManager;
import com.example.myapplication.Model.helper.GameConstants;

public class PlayingLogic {



    public int getPlayerDrawDir(boolean attacking) {
        if (!attacking) {
            return Player.getInstance().getDrawDir();
        }
        return Player.getInstance().getFaceDir() + 4; //return row that have attacking anim
    }

    public void setPlayerAnimDir(float xSpeed, float ySpeed, PointF lastTouchDiff) {
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
        Player.getInstance().setPlayerStates(PlayerStates.RUNNING);
    }

    public Bitmap getPlayerSprite(boolean attacking) {
        return Player.getInstance().getGameCharType().getSprite(
                getPlayerDrawDir(attacking), Player.getInstance().getAniIndex()
        );
    }
    public float getPlayerLeft() {
        return Player.getInstance().getHitBox().left - offSetX(); //此处减去的为碰撞箱盒实际素材的误差
    }
    public float getPlayerTop() {
        return Player.getInstance().getHitBox().top - Player.getInstance().getHitBoxOffSetY();
    }
    public RectF getPlayerHitbox() {
        return Player.getInstance().getHitBox();
    }
    public PointF getEffectPos() {
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

    public boolean checkPlayerAbleMoveWithAttacking(boolean attacking) {
        return !attacking;
    }


    public boolean checkPlayerAbleMove(
            boolean attacking,
            MapManager mapManager,
            int pWidth, int pHeight,
            PointF delta, PointF camera
    ) {
        float x
                = Player.getInstance().getHitBox().left + camera.x * -1 + delta.x * -1 + pWidth;

        float yTop
                = Player.getInstance().getHitBox().top + camera.y * -1 + delta.y * -1;
 
        float yBottom
                = Player.getInstance().getHitBox().top + camera.y * -1 + delta.y * -1 + pHeight;

        return mapManager.getCurrentMap().canMoveHere(x, yTop, yBottom)
                && checkPlayerAbleMoveWithAttacking(attacking);
    }

    public float getEffectRote() {
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
            return 180;
        } else {
            return 0;
        }
    }
    public int offSetX() {
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
            return Player.getInstance().getHitBoxOffsetX();
        }
        return 0;
    }

    public boolean checkHitBoxCollision(RectF hitBoxOne, RectF hitBoxTwo) {
        return hitBoxOne.intersects(
                hitBoxTwo.left,
                hitBoxTwo.top,
                hitBoxTwo.right,
                hitBoxTwo.bottom);
    }

    public void checkAttackByEnemies(
            RectF playerHitBox,
            MapManager mapManager,
            float cameraX, float cameraY
    ) {
        RectF attackBoxWithoutCamera = new RectF(playerHitBox);
        attackBoxWithoutCamera.left -= cameraX;
        attackBoxWithoutCamera.top -= cameraY;
        attackBoxWithoutCamera.right -= cameraX;
        attackBoxWithoutCamera.bottom -= cameraY;

        for (AbstractEnemy enemy : mapManager.getCurrentMap().getMobArrayList()) {
            if (checkHitBoxCollision(attackBoxWithoutCamera, enemy.getHitBox())) {
                if (enemy.isAbleAttackPlayer()) {
                    enemy.setAbleAttackPlayer(false);

                    if (Player.getInstance().getCurrentHealth() > 0) {

//                        int newHealth = calculateHealthByAtk(
//
//                                Player.getInstance().getCurrentHealth(),
//                                calculateDamage(
//                                        enemy.getAtk(),
//                                        Player.getInstance().getDifficulty()
//                                )
//                        );
//
//                        if (newHealth < 0) {
//                            newHealth = 0;
//                        }
                        Player.getInstance().setCurrentHealth(
                                calculateNewHealthForPlayer(
                                        Player.getInstance().getCurrentHealth(),
                                        enemy.getAtk(),
                                        Player.getInstance().getDifficulty()
                                ));
                    }
                }

            }
        }
    }

    public int calculateNewHealthForPlayer(int currentHealth, int enemyAtk, int difficulty) {
        int newHealth = calculateHealthByAtk(
                currentHealth,
                calculateDamage(
                        enemyAtk,
                        difficulty
                )
        );
        return Math.max(newHealth, 0);
    }

    public int calculateHealthByAtk(int currentHealth, int enemyAtk) {
        return currentHealth - enemyAtk;
    }

    public int calculateDamage(int enemyAtk, int difficulty) {
        return enemyAtk * difficulty;
    }


    public void checkAttack(
            boolean attacking, RectF attackBox,
            MapManager mapManager,
            float cameraX, float cameraY
    ) {
        if (attacking) {
            RectF attackBoxWithoutCamera = new RectF(attackBox);
            attackBoxWithoutCamera.left -= cameraX;
            attackBoxWithoutCamera.top -= cameraY;
            attackBoxWithoutCamera.right -= cameraX;
            attackBoxWithoutCamera.bottom -= cameraY;

            for (AbstractEnemy enemy : mapManager.getCurrentMap().getMobArrayList()) {
                if (attackBoxWithoutCamera.intersects(
                        enemy.getHitBox().left,
                        enemy.getHitBox().top,
                        enemy.getHitBox().right,
                        enemy.getHitBox().bottom)
                ) {
                    enemy.setActive(false); //remove enemy(or any mob)
                }
            }
        }
    }

    public void updateZombies(MapManager mapManager, double delta, float cameraX, float cameraY) {
        for (AbstractEnemy zombie : mapManager.getCurrentMap().getMobArrayList()) {
            if (zombie.isActive()) {
                zombie.update(delta, mapManager, new PointF(
                        Player.getInstance().getHitBox().centerX() - cameraX,
                        Player.getInstance().getHitBox().centerY() - cameraY)
                );
            }
        }
    }






}
