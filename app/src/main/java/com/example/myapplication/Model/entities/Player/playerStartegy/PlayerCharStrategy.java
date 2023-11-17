package com.example.myapplication.Model.entities.Player.playerStartegy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.entities.Player.projectile.Projectile;
import com.example.myapplication.Model.entities.enemies.normalEnemies.AbstractEnemy;
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
        initBaseDamage();
        initDefence();
    }

    abstract void initBaseDamage();
    abstract void initDefence();


    abstract int offSetY();
    abstract int getAnimMaxIndex(PlayerStates state);
    abstract void drawAttackBox(Canvas c);

    abstract void updateAtkBoxWhenAttacking();


    default float getCurrentSpeed(float baseSpeed, PlayerStates state) {
        if (state == PlayerStates.RUNNING) {
            return baseSpeed * 2;
        } else if (state == PlayerStates.DASH) {
            return getSpeedDuringDash(baseSpeed);
        }
        return baseSpeed;
    }

    abstract float getSpeedDuringDash(float baseSpeed);

    default void updateAtkBox() {
        PlayerStates state = Player.getInstance().getCurrentStates();
        if (state == PlayerStates.ATTACK) {
            updateAtkBoxWhenAttacking();
        } else if (state == PlayerStates.PROJECTILE) {
            makeProjectile();
        } else if (state == PlayerStates.SKILL_ONE) {
            skillOne();

        }
    }

    abstract void drawSkillOne(Canvas c);


    default int getCurrentDamage(PlayerStates state, int baseDamage) {
        if (state == PlayerStates.ATTACK) {
            return baseDamage;
        } else if (state == PlayerStates.PROJECTILE) {
            return baseDamage;
        } else if (state == PlayerStates.SKILL_ONE) {
            return baseDamage * 5;
        }
        return 0;
    }

    abstract void skillOne();
    default void projectileHitEnemy(Projectile p) {
        p.updateHitCount();
    }

    abstract int getProjectileMaxHit(PlayerStates state);

    default void resetEnemyAbleTakeDamage() {
        if (Player.getInstance().getCurrentMap() == null) {
            return;
        }
        for (AbstractEnemy enemy : Player.getInstance().getCurrentMap().getMobArrayList()) {
            if (enemy.isActive()) {
                enemy.setTakeDamageAlready(false);
            }
        }
    }


    abstract float getProjectSpeed();
    abstract void makeProjectile();
    abstract PointF getProjectileStartPos();
    abstract PointF getProjectileSize();








    default PointF getMoveDelta(double delta, float xSpeed, float ySpeed) {
        float baseSpeed = (float) delta * Player.getInstance().getCurrentSpeed();
        PlayerStates states = Player.getInstance().getCurrentStates();
        if (states == PlayerStates.DASH) {
            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
                return new PointF(baseSpeed * -1, 0);
            }
            return new PointF(baseSpeed, 0);
        }
        float deltaX = xSpeed * baseSpeed * -1; //移动镜头而不是角色
        float deltaY = ySpeed * baseSpeed * -1; //因镜头需与角色相反的方向移动，即乘以-1
        return new PointF(deltaX, deltaY);
    }

    default PointF gePlayerMovement(float xSpeed, float ySpeed, float speed) {


        PlayerStates state = Player.getInstance().getCurrentStates();



        if (state == PlayerStates.DASH) {
            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
                return new PointF(speed * -1, 0);
            }
            return new PointF(speed, 0);
        } else if (state == PlayerStates.WALK || state == PlayerStates.RUNNING) {
            float deltaX = xSpeed * speed * -1; //移动镜头而不是角色
            float deltaY = ySpeed * speed * -1; //因镜头需与角色相反的方向移动，即乘以-1
            return new PointF(deltaX, deltaY);
        } else {
            return new PointF(0, 0);
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
        if (Player.getInstance().isMakingDamage()) {
            Player.getInstance().drawAtk(c);
        }

        drawSkill(c);


    }

    default void drawSkill(Canvas c) {
        PlayerStates state = Player.getInstance().getCurrentStates();
        if (state == PlayerStates.SKILL_ONE) {
            drawSkillOne(c);
        }

    }


    default float getPlayerLeft() {
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            return Player.getInstance().getHitBox().left - Player.getInstance().getHitBoxOffsetX()
                    + adjustX();
        } else {
            return Player.getInstance().getHitBox().left
                    - adjustX();
        }
    }


    abstract float adjustX();
    abstract float adjustY();


    default float getPlayerTop() {
        return Player.getInstance().getHitBox().top - Player.getInstance().getHitBoxOffsetY()
                + adjustY();
    }
    default Bitmap getPlayerSprite() {
        return Player.getInstance().getGameCharType().getSprite(
                Player.getInstance().getCurrentStates().getAnimRow()
                        + Player.getInstance().getFaceDir(),
                Player.getInstance().getAniIndex()
        );
    }

}