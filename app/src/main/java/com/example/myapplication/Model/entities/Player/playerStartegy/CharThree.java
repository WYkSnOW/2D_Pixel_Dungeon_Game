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

public class CharThree implements PlayerCharStrategy {
    @Override
    public void initCharAtkBoxInfo() {
        Player.getInstance().setAttackWidth((int) (1.2 * GameConstants.Sprite.SIZE));
        Player.getInstance().setAttackHeight((int) (2 * GameConstants.Sprite.SIZE));
    }


    @Override
    public void initChar() {
        Player.getInstance().setCharacterChoice(GameCharacters.WARRIOR2);
    }

    @Override
    public void initBaseSpeed() {
        Player.getInstance().setBaseSpeed(150);
    }
    @Override
    public void initDefence() {
        Player.getInstance().setDefence(3);
    }
    @Override
    public void initBaseDamage() {
        Player.getInstance().setBaseDamage(10);
    }

    @Override
    public int getAnimMaxIndex(PlayerStates state) {
        if (state == PlayerStates.IDLE) {
            return 6;
        } else if (state == PlayerStates.RUNNING) {
            return 8;
        } else if (state == PlayerStates.ATTACK) {
            return 27;
        } else if (state == PlayerStates.WALK) {
            return 18;
        } else if (state == PlayerStates.PROJECTILE) {
            return 28;
        } else if (state == PlayerStates.DASH) {
            return 7;
        } else if (state == PlayerStates.HURT) {
            return 5;
        } else if (state == PlayerStates.SKILL_ONE) {
            return 30;
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
        if (1 <= idx && idx <= 4) {
            return baseSpeed * 6.5f;
        } else if (5 <= idx && idx <= 6) {
            return baseSpeed * 3;
        } else if (idx == 7) {
            return baseSpeed;
        }
        return 0;
    }

    @Override
    public void skillOne() {
        Player.getInstance().setInvincibleTrue();
        PointF pos = getAtkBoxPosWhenSkillOne();

        float w = getAtkBoxSizeWhenSkillOne().x;
        float h = getAtkBoxSizeWhenSkillOne().y;

        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            Player.getInstance().setAttackBox(new RectF(pos.x, pos.y, pos.x + w, pos.y + h));
        } else {
            Player.getInstance().setAttackBox(new RectF(pos.x - w, pos.y, pos.x, pos.y + h));
        }
    }

    @Override
    public void drawSkillOne(Canvas c) {
    }


    private PointF getAtkBoxPosWhenSkillOne() {
        float top = Player.getInstance().getHitBox().bottom;

        int idx = Player.getInstance().getAniIndex();


        if (25 == idx || idx == 27) {
            PointF size = getAtkBoxSizeWhenSkillOne();
            top -= size.y;
            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
                return new PointF(
                        Player.getInstance().getHitBox().right + (float) (size.x / 3.7),
                        top
                );
            } else {
                return new PointF(
                        Player.getInstance().getHitBox().left - (float) (size.x / 3.7),
                        top
                );
            }

        }

        return new PointF(0, 0);
    }

    private PointF getAtkBoxSizeWhenSkillOne() {
        int idx = Player.getInstance().getAniIndex();
        Player.getInstance().setMakingDamage(true);

        if (25 == idx || idx == 27) {
            return new PointF(
                    (float) (2.1 * GameConstants.Sprite.SIZE),
                    (float) (1.7 * GameConstants.Sprite.SIZE)
            );
        }

        resetEnemyAbleTakeDamage();
        Player.getInstance().setMakingDamage(false);
        return new PointF(
                (float) (1.2 * GameConstants.Sprite.SIZE),
                (float) (2 * GameConstants.Sprite.SIZE)
        );

    }





    private PointF getAtkBoxSizeWhenAttacking() {
        int idx = Player.getInstance().getAniIndex();
        Player.getInstance().setMakingDamage(true);

        float scale = (float) Player.getInstance().getGameCharType().getScale();


        if (6 <= idx && idx <= 8) {
            return new PointF(
                    (float) (0.9 * GameConstants.Sprite.SIZE),
                    (float) (1.1 * GameConstants.Sprite.SIZE)
            );
        }

        if (12 <= idx && idx <= 14) {
            return new PointF(
                    28 * scale,
                    35 * scale
            );
        }

        if (19 <= idx && idx <= 22) {
            return new PointF(
                    36 * scale,
                    4 * scale
            );
        }


        resetEnemyAbleTakeDamage();
        Player.getInstance().setMakingDamage(false);
        return new PointF(
                24 * scale,
                30 * scale
        );
    }

    private PointF getAtkBoxPosWhenAttacking() {
        float bottom = Player.getInstance().getHitBox().bottom;
        float scale = (float) Player.getInstance().getGameCharType().getScale();

        int idx = Player.getInstance().getAniIndex();
        if (6 <= idx && idx <= 8) {
            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
                bottom -= 3 * scale;
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
        } else if (12 <= idx && idx <= 14) {
            bottom -= 9 * scale;

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
        } else if (19 <= idx && idx <= 22) {
            bottom -= 19 * scale;

            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
                return new PointF(
                        Player.getInstance().getHitBox().left,
                        bottom
                );
            } else  {
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
            offset = 11;
        } else if (state == PlayerStates.WALK) {
            offset = 22;
        } else if (state == PlayerStates.RUNNING) {
            offset = 9;
        } else if (state == PlayerStates.ATTACK) {
            offset = 36;
        } else if (state == PlayerStates.PROJECTILE) {
            offset = 27;
        } else if (state == PlayerStates.DASH) {
            offset = 9;
        } else if (state == PlayerStates.HURT) {
            offset = 14;
        } else if (state == PlayerStates.SKILL_ONE) {
            offset = 31;
        }
        return offset * scale;
    }

    @Override
    public float adjustY() {
        PlayerStates state = Player.getInstance().getCurrentStates();
        float scale = (float) Player.getInstance().getGameCharType().getScale();
        float offset = 0;
        if (state == PlayerStates.PROJECTILE) {
            offset = 6;
        } else if (state == PlayerStates.HURT) {
            offset = 1;
        } else if (state == PlayerStates.SKILL_ONE) {
            offset = 8;
        }
        return offset * scale;
    }

    @Override
    public int offSetY() { //画出动画时会减去这个值, 数字越大越往上
        int offsetYTop = Player.getInstance().getHitBoxOffsetY();
        PlayerStates state = Player.getInstance().getCurrentStates();
        if (state == PlayerStates.PROJECTILE) {
            offsetYTop -= Player.getInstance().getHitBoxOffsetY() / 4.5;
        } else if (state == PlayerStates.SKILL_ONE) {
            offsetYTop -= Player.getInstance().getHitBoxOffsetY() / 4.5;
        }
        return offsetYTop;
    }

    @Override
    public float getProjectSpeed() {
        PlayerStates states = Player.getInstance().getCurrentStates();
        if (states == PlayerStates.PROJECTILE) {
            return 400;
        }
        return 100;
    }

    @Override
    public int getProjectileMaxHit(PlayerStates state) {
        return 3;
    }

    @Override
    public void makeProjectile() {
        if (Player.getInstance().getAniIndex() == 22) {
            if (Player.getInstance().isAbleProjectile()) {
                Player.getInstance().setAbleProjectile(false);
                ProjectileHolder.getInstance().addProjectile(
                        getProjectileStartPos(),
                        getProjectileSize(),
                        Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT,
                        getProjectSpeed(),
                        GameVideos.SWORD_PJT_ANIM,
                        new PointF(
                                (float) (16 * GameVideos.SWORD_PJT_ANIM.getScale()),
                                -(float) (7 * GameVideos.SWORD_PJT_ANIM.getScale())
                        ),
                        new ShotProjectile());
            }
        } else {
            Player.getInstance().setAbleProjectile(true);
        }
    }


    @Override
    public PointF getProjectileStartPos() {
        float bottom = Player.getInstance().getHitBox().bottom;
        //float scale = (float) Player.getInstance().getGameCharType().getScale();

        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
            return new PointF(
                    Player.getInstance().getHitBox().left - getProjectileSize().x,
                    bottom - getProjectileSize().y
            );
        } else {
            return new PointF(
                    Player.getInstance().getHitBox().right,
                    bottom - getProjectileSize().y
            );
        }
    }
    @Override
    public PointF getProjectileSize() {
        return new PointF((float) (GameConstants.Sprite.SIZE * 0.5),
                (float) (GameConstants.Sprite.SIZE * 1.2));
    }
}