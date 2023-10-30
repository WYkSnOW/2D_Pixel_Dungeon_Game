package com.example.myapplication.Model.gameStatesLogic;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.entities.Player.projectile.Projectile;
import com.example.myapplication.Model.entities.Player.projectile.ProjectileHolder;
import com.example.myapplication.Model.entities.enemies.AbstractEnemy;
import com.example.myapplication.Model.environments.GameMap;
import com.example.myapplication.Model.environments.MapManager;

public class PlayingLogic {

    public boolean checkPlayerAbleMoveWithAttacking(boolean attacking) {
        return !attacking;
    }
    public boolean checkPlayerAbleMoveX(
            boolean attacking,
            MapManager mapManager,
            PointF delta, PointF camera
    ) {
        if (Player.getInstance().getCurrentStates() == PlayerStates.DASH) {
            System.out.println("hittttt");
        }
        System.out.println();
        if (Player.getInstance().isAttacking()
                || Player.getInstance().isProjecting()) {
            return false;
        }
        return checkAbleMoveX(mapManager, delta, camera);

    }
    public boolean checkPlayerAbleMoveY(
            boolean attacking,
            MapManager mapManager,
            PointF delta, PointF camera
    ) {
        if (Player.getInstance().isAttacking()
                || Player.getInstance().isProjecting()) {
            return false;
        }
        return checkAbleMoveY(mapManager, delta, camera);

    }


    public boolean checkAbleMoveY(
            MapManager mapManager,
            PointF delta, PointF camera
    ) {
        float xL
                = Player.getInstance().getHitBox().left + camera.x * -1 + delta.x;
        float xR
                = Player.getInstance().getHitBox().right + camera.x * -1 + delta.x;

        float yTop
                = Player.getInstance().getHitBox().top + camera.y * -1 + delta.y * -1;

        float yBottom
                = Player.getInstance().getHitBox().bottom + camera.y * -1 + delta.y * -1;

        return mapManager.getCurrentMap().canMoveHere(xR, yTop, yBottom)
                && mapManager.getCurrentMap().canMoveHere(xL, yTop, yBottom);
    }

    public boolean checkAbleMoveX(
            MapManager mapManager,
            PointF delta, PointF camera
    ) {
        float xL
                = Player.getInstance().getHitBox().left + camera.x * -1 + delta.x * -1;
        float xR
                = Player.getInstance().getHitBox().right + camera.x * -1 + delta.x * -1;

        float currYTop
                = Player.getInstance().getHitBox().top + camera.y * -1 + delta.y;

        float currYBottom
                = Player.getInstance().getHitBox().bottom + camera.y * -1 + delta.y;

        return mapManager.getCurrentMap().canMoveHere(xL, currYTop, currYBottom)
                && mapManager.getCurrentMap().canMoveHere(xR, currYTop, currYBottom);
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

        RectF attackBoxWithoutCamera = new RectF(attackBox);
        attackBoxWithoutCamera.left -= cameraX;
        attackBoxWithoutCamera.top -= cameraY;
        attackBoxWithoutCamera.right -= cameraX;
        attackBoxWithoutCamera.bottom -= cameraY;

        for (AbstractEnemy enemy : mapManager.getCurrentMap().getMobArrayList()) {
            if (enemy.isActive()) {
                if (Player.getInstance().isMakingDamage()) {
                    if (attackBoxWithoutCamera.intersects(
                            enemy.getHitBox().left,
                            enemy.getHitBox().top,
                            enemy.getHitBox().right,
                            enemy.getHitBox().bottom)
                    ) {
                        enemy.takeDamage(); //remove enemy(or any mob)
                    }
                }



                for (Projectile p : ProjectileHolder.getInstance().getProList()) {
                    if (p.isActive()) {
                        if (p.getHitBox().intersects(
                                enemy.getHitBox().left,
                                enemy.getHitBox().top,
                                enemy.getHitBox().right,
                                enemy.getHitBox().bottom)
                        ) {
                            enemy.takePjtDamage(); //remove enemy(or any mob)
                            p.setActive(false);
                        }
                    }

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
