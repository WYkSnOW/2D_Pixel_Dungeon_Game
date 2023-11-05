package com.example.myapplication.Model.entities.Player.playerStartegy;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.entities.Player.projectile.ProjectileHolder;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.loopVideo.GameVideos;

public class CharTwo implements PlayerCharStrategy {
    @Override
    public void initCharAtkBoxInfo() {
        Player.getInstance().setAttackWidth(GameVideos.WITCH_ATTACK_EFFECT.getWidth());
        Player.getInstance().setAttackHeight(GameConstants.Sprite.SIZE);
    }

    @Override
    public void initChar() {
        Player.getInstance().setCharacterChoice(GameCharacters.WITCH2);
    }

    @Override
    public void initBaseSpeed() {
        Player.getInstance().setBaseSpeed(200);
    }

    @Override
    public int getAnimMaxIndex(PlayerStates state) {
        if (state == PlayerStates.IDLE) {
            return 9;
        } else if (state == PlayerStates.WALK) {
            return 8;
        } else if (state == PlayerStates.RUNNING) {
            return 5;
        } else if (state == PlayerStates.ATTACK) {
            return 18;
        } else if (state == PlayerStates.PROJECTILE) {
            return 10;
        } else  if (state == PlayerStates.DASH) {
            return 18;
        }
        return 1;
    }

    @Override
    public void drawAttackBox(Canvas c) {
        c.drawRect(
                Player.getInstance().getAttackBox(),
                Player.getInstance().getHitBoxPaint()
        ); //draw weapon's hitbox
    }

    @Override
    public void updateAtkBoxWhenAttacking() {
        PointF pos = getAtkBoxPosWhenAttacking();

        float w = getAtkBoxSizeWhenAttacking().x;
        float h = getAtkBoxSizeWhenAttacking().y;

        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            Player.getInstance().setAttackBox(new RectF(pos.x, pos.y, pos.x + w, pos.y + h));
        } else {
            Player.getInstance().setAttackBox(new RectF(pos.x - w, pos.y, pos.x, pos.y + h));
        }
    }

    @Override
    public float getSpeedDuringDash(float baseSpeed) {
        int idx = Player.getInstance().getAniIndex();
        if (1 <= idx && idx <= 3) {
            return baseSpeed * 1.5f;
        } else if (4 <= idx && idx <= 7) {
            return baseSpeed * 4;
        } else if (8 <= idx && idx <= 11) {
            return baseSpeed * 5;
        } else if (12 <= idx && idx <= 13) {
            return baseSpeed * 2;
        } else if (14 <= idx && idx <= 17) {
            return baseSpeed;
        } else if (idx == 18) {
            return baseSpeed * 0.5f;
        }
        return 0;

    }

    private PointF getAtkBoxSizeWhenAttacking() {
        int idx = Player.getInstance().getAniIndex();
        Player.getInstance().setMakingDamage(true);

        if (9 <= idx && idx <= 15) {
            return new PointF(
                    (float) (0.7 * GameConstants.Sprite.SIZE), //width
                    (float) (1.1 * GameConstants.Sprite.SIZE) //height
            );
        }

        resetEnemyAbleTakeDamage();
        Player.getInstance().setMakingDamage(false);
        return new PointF(0, 0);
    }

    private PointF getAtkBoxPosWhenAttacking() {
        float top = Player.getInstance().getHitBox().top;

        int idx = Player.getInstance().getAniIndex();
        if (9 <= idx && idx <= 15) {
            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
                return new PointF(
                        Player.getInstance().getHitBox().left,
                        top
                );
            } else {
                return new PointF(
                        Player.getInstance().getHitBox().right,
                        top
                );
            }
        }

        return new PointF(0, 0);
    }

    @Override
    public int offSetX() { //画出动画时会减去这个值, 朝左时数字越大越往左
        PlayerStates state = Player.getInstance().getCurrentStates();
        int dir = Player.getInstance().getFaceDir();
        int offsetXRight = Player.getInstance().getHitBoxOffsetX();
        int offsetXLeft = 0;

        if (state == PlayerStates.IDLE) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 4;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 4;
        } else if (state == PlayerStates.RUNNING) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 4;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 4;
        } else if (state == PlayerStates.WALK) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 3;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 3;
        } else if (state == PlayerStates.ATTACK) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 2;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 2;
        } else if (state == PlayerStates.PROJECTILE) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 1.6;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 1.6;
        } else if (state == PlayerStates.DASH) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 3;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 3;
        }

        if (dir == GameConstants.FaceDir.LEFT) {
            return offsetXLeft;
        }
        return offsetXRight;
    }

    @Override
    public int offSetY() { //画出动画时会减去这个值, 数字越大越往上
        int offsetYTop = Player.getInstance().getHitBoxOffsetY();
        PlayerStates state = Player.getInstance().getCurrentStates();
        if (state == PlayerStates.ATTACK) {
            offsetYTop -= Player.getInstance().getHitBoxOffsetY() / 4.5;
        }
        return offsetYTop;
    }

    @Override
    public float getProjectSpeed() {
        PlayerStates states = Player.getInstance().getCurrentStates();
        if (states == PlayerStates.PROJECTILE) {
            return 700;
        }
        return 100;
    }

    @Override
    public void makeProjectile() {
        if (Player.getInstance().getAniIndex() == 6) {
            if (Player.getInstance().isAbleProjectile()) {
                Player.getInstance().setAbleProjectile(false);
                ProjectileHolder.getInstance().addProjectile(
                        getProjectileStartPos(),
                        getProjectileSize(),
                        Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT,
                        getProjectSpeed());
            }
        } else {
            Player.getInstance().setAbleProjectile(true);
        }
    }


    @Override
    public PointF getProjectileStartPos() {
        float top = Player.getInstance().getHitBox().top;
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
            return new PointF(
                    Player.getInstance().getHitBox().left - getProjectileSize().x,
                    top
            );
        } else {
            return new PointF(
                    Player.getInstance().getHitBox().right,
                    top
            );
        }
    }
    @Override
    public PointF getProjectileSize() {
        return new PointF(GameConstants.Sprite.SIZE,
                GameConstants.Sprite.SIZE);
    }
}
