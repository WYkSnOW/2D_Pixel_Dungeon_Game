package com.example.myapplication.Model.entities.Player.playerStartegy;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.entities.Player.projectile.ProjectileHolder;
import com.example.myapplication.Model.helper.GameConstants;

public class CharOne implements PlayerCharStrategy {
    @Override
    public void initCharAtkBoxInfo() {
        Player.getInstance().setAttackWidth((int) (0.8 * GameConstants.Sprite.SIZE));
        Player.getInstance().setAttackHeight(2 * GameConstants.Sprite.SIZE);
    }


    @Override
    public int getAnimMaxIndex(PlayerStates state) {
        if (state == PlayerStates.IDLE) {
            return 29;
        } else if (state == PlayerStates.WALK) {
            return 14;
        } else if (state == PlayerStates.RUNNING) {
            return 8;
        } else if (state == PlayerStates.ATTACK) {
            return 12;
        } else if (state == PlayerStates.PROJECTILE) {
            return 21;
        } else if (state == PlayerStates.DASH) {
            return 14;
        } else if (state == PlayerStates.SKILL_ONE) {
            return 20;
        }
        return 1;
    }

    @Override
    public void initChar() {
        Player.getInstance().setCharacterChoice(GameCharacters.CENTAUR);
    }

    @Override
    public void initBaseSpeed() {
        Player.getInstance().setBaseSpeed(250);
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
        if (1 <= idx && idx <= 2) {
            return baseSpeed * 2;
        } else if (3 <= idx && idx <= 7) {
            return baseSpeed * 4;
        } else if (8 <= idx && idx <= 11) {
            return baseSpeed * 2;
        } else if (12 <= idx && idx <= 14) {
            return baseSpeed;
        }
        return 0;

    }

    private PointF getAtkBoxSizeWhenAttacking() {
        int idx = Player.getInstance().getAniIndex();
        Player.getInstance().setMakingDamage(true);

        if (6 <= idx && idx <= 8) {
            return new PointF(
                    (float) (0.9 * GameConstants.Sprite.SIZE),
                    (float) (1.5 * GameConstants.Sprite.SIZE)
            );
        } else if (10 <= idx && idx <= 11) {
            return new PointF(
                    (float) (0.7 * GameConstants.Sprite.SIZE),
                    (float) (0.9 * GameConstants.Sprite.SIZE)
            );
        }


        resetEnemyAbleTakeDamage();
        Player.getInstance().setMakingDamage(false);
        return new PointF(0, 0);
    }

    private PointF getAtkBoxPosWhenAttacking() {
        float top = Player.getInstance().getHitBox().top;
        int idx = Player.getInstance().getAniIndex();


        if (6 <= idx && idx <= 8) {
            top -= Player.getInstance().getHitBoxOffsetY() / 2f;
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
        } else if (10 <= idx && idx <= 11) {
            top += Player.getInstance().getHitBoxOffsetY() / 2.6f;
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
    public int offSetX() { //画出动画时会减去这个值
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
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 1.5;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 1.5;
        } else if (state == PlayerStates.PROJECTILE) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 1.5;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 1.5;
        } else if (state == PlayerStates.DASH) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 4;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 4;
        } else if (state == PlayerStates.SKILL_ONE) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 1.5;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 1.5;
        }

        if (dir == GameConstants.FaceDir.LEFT) {
            return offsetXLeft;
        }
        return offsetXRight;
    }

    @Override
    public int offSetY() {
        int offsetYTop = Player.getInstance().getHitBoxOffsetY();
        PlayerStates state = Player.getInstance().getCurrentStates();
        if (state == PlayerStates.SKILL_ONE) {
            offsetYTop -= Player.getInstance().getHitBoxOffsetY() / 4.5;
        }
        return offsetYTop;
    }

    @Override
    public float getProjectSpeed() {
        PlayerStates states = Player.getInstance().getCurrentStates();
        if (states == PlayerStates.PROJECTILE) {
            return 1300;
        } else if (states == PlayerStates.SKILL_ONE) {
            return 1300 * 2;
        }
        return 100;
    }

    @Override
    public void makeProjectile() {
        if (Player.getInstance().getAniIndex() == 16) {
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
        top += (float) (Player.getInstance().getHitBoxHeight() / 6);
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
        return new PointF(GameConstants.Sprite.SIZE - 10,
                GameConstants.Sprite.SIZE * 0.3f);
    }
}
