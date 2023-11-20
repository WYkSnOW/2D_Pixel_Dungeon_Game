package com.example.myapplication.Model.entities.Player.playerStartegy;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.entities.Player.projectile.ProjectileHolder;
import com.example.myapplication.Model.entities.Player.projectile.projecttileStrategy.ShotProjectile;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.loopVideo.GameVideos;

public class CharOne implements PlayerCharStrategy {
    @Override
    public void initCharAtkBoxInfo() {
        Player.getInstance().setAttackWidth((int) (0.8 * GameConstants.Sprite.SIZE));
        Player.getInstance().setAttackHeight(2 * GameConstants.Sprite.SIZE);
    }

    @Override
    public void initDefence() {
        Player.getInstance().setDefence(2);
    }
    @Override
    public void initBaseDamage() {
        Player.getInstance().setBaseDamage(10);
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
        } else if (state == PlayerStates.HURT) {
            return 4;
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
    public int getProjectileMaxHit(PlayerStates state) {
        if (state == PlayerStates.SKILL_ONE) {
            return -1;
        } else {
            return 1;
        }
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
        PointF pos = getAtkBoxPosWhenAttacking(); // y of pos is bottom

        float w = getAtkBoxSizeWhenAttacking().x;
        float h = getAtkBoxSizeWhenAttacking().y;

        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            Player.getInstance().setAttackBox(new RectF(pos.x, pos.y - h, pos.x + w, pos.y));
        } else {
            Player.getInstance().setAttackBox(new RectF(pos.x - w, pos.y - h, pos.x, pos.y));
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
    @Override
    public void skillOne() {
        if (Player.getInstance().getAniIndex() == 15) {
            if (Player.getInstance().isAbleProjectile()) {
                Player.getInstance().setAbleProjectile(false);
                ProjectileHolder.getInstance().addProjectile(
                        getSkillStartPos(),
                        getProjectileSize(),
                        Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT,
                        getProjectSpeed(),
                        GameVideos.STORM_ARROW_ANIM,
                        new PointF(
                                (float) (7 * GameVideos.STORM_ARROW_ANIM.getScale()),
                                -(float) (45 * GameVideos.STORM_ARROW_ANIM.getScale())
                        ),
                        new ShotProjectile());
            }
        } else {
            Player.getInstance().setAbleProjectile(true);
        }
    }
    @Override
    public void drawSkillOne(Canvas c) {
    }

    private PointF getAtkBoxSizeWhenAttacking() {
        int idx = Player.getInstance().getAniIndex();
        float scale = (float) Player.getInstance().getGameCharType().getScale();
        Player.getInstance().setMakingDamage(true);

        if (6 <= idx && idx <= 8) {
            return new PointF(
                    30 * scale,
                    37 * scale
            );
        } else if (10 <= idx && idx <= 11) {
            return new PointF(
                    29 * scale,
                    26 * scale
            );
        }


        resetEnemyAbleTakeDamage();
        Player.getInstance().setMakingDamage(false);
        return new PointF(0, 0);
    }

    private PointF getAtkBoxPosWhenAttacking() {
        float bottom = Player.getInstance().getHitBox().bottom;
        float scale = (float) Player.getInstance().getGameCharType().getScale();
        int idx = Player.getInstance().getAniIndex();


        if (6 <= idx && idx <= 8) {
            bottom -= 8 * scale;
            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
                return new PointF(
                        Player.getInstance().getHitBox().left,
                        bottom
                );
            } else {
                return new PointF(
                        Player.getInstance().getHitBox().right,
                        bottom
                );
            }
        } else if (10 <= idx && idx <= 11) {
            bottom -= 3 * scale;
            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
                return new PointF(
                        Player.getInstance().getHitBox().left,
                        bottom
                );
            } else {
                return new PointF(
                        Player.getInstance().getHitBox().right,
                        bottom
                );
            }
        }

        return new PointF(0, 0);
    }

    @Override
    public float adjustX() {
        PlayerStates state = Player.getInstance().getCurrentStates();
        float scale = (float) Player.getInstance().getGameCharType().getScale();
        float offset = 0;
        if (state == PlayerStates.IDLE) {
            offset = 10;
        } else if (state == PlayerStates.WALK) {
            offset = 12;
        } else if (state == PlayerStates.RUNNING) {
            offset = 8;
        } else if (state == PlayerStates.ATTACK) {
            offset = 30;
        } else if (state == PlayerStates.PROJECTILE) {
            offset = 34;
        } else if (state == PlayerStates.DASH) {
            offset = 14;
        } else if (state == PlayerStates.HURT) {
            offset = 14;
        } else if (state == PlayerStates.SKILL_ONE) {
            offset = 25;
        }
        return offset * scale;
    }

    @Override
    public float adjustY() {
        PlayerStates state = Player.getInstance().getCurrentStates();
        float scale = (float) Player.getInstance().getGameCharType().getScale();
        float offset = 0;
        if (state == PlayerStates.SKILL_ONE) {
            offset = 6;
        }
        return offset * scale;
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
            return 3000;
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
                        getProjectSpeed(),
                        GameVideos.ARROW_ANIM,
                        new PointF(0, (float) (2 * GameVideos.ARROW_ANIM.getScale())),
                        new ShotProjectile());
            }
        } else {
            Player.getInstance().setAbleProjectile(true);
        }
    }


    @Override
    public PointF getProjectileStartPos() {
        float bottom = Player.getInstance().getHitBox().bottom;
        float scale = (float) Player.getInstance().getGameCharType().getScale();

        float top = bottom - (24 * scale) - getProjectileSize().y;

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

    private PointF getSkillStartPos() {
        float bottom = Player.getInstance().getHitBox().bottom;
        float scale = (float) Player.getInstance().getGameCharType().getScale();

        float top = bottom - (20 * scale) - getProjectileSize().y;

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
        if (Player.getInstance().getCurrentStates() == PlayerStates.SKILL_ONE) {
            return new PointF(GameConstants.Sprite.SIZE * 4,
                    GameConstants.Sprite.SIZE * 0.3f);
        }
        return new PointF(GameConstants.Sprite.SIZE - 10,
                GameConstants.Sprite.SIZE * 0.3f);
    }
}