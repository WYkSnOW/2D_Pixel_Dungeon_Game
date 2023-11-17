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
    public void initDefence() {
        Player.getInstance().setDefence(0);
    }
    @Override
    public void initBaseDamage() {
        Player.getInstance().setBaseDamage(15);
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
        } else  if (state == PlayerStates.HURT) {
            return 4;
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

    @Override
    public void skillOne() {
        //Player.getInstance().setInvincibleTrue();
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
        if (Player.getInstance().getAniIndex() >= 7) {
            int idx = Player.getInstance().getAniIndex() - 7;


            float xOffset = (float) (47 * GameVideos.BLACK_HOLE_ANIM.getScale());
            float xPos = getAnimPosSkillOne().x - xOffset;

            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
                xPos -= xOffset;
            }


            c.drawBitmap(
                    GameVideos.BLACK_HOLE_ANIM.getSprite(Player.getInstance().getFaceDir(), idx),
                    xPos,
                    getAnimPosSkillOne().y
                            - (float) (41 * GameVideos.BLACK_HOLE_ANIM.getScale()),
                    null
            );
        }
    }

    private PointF getAnimPosSkillOne() {
        PointF size = getAtkBoxSizeWhenSkillOne();
        float top = Player.getInstance().getHitBox().bottom - size.y;
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            return new PointF(
                    (float) (Player.getInstance().getHitBox().right + (size.x * 1.0) / 5),
                    top
            );
        } else {
            return new PointF(
                    (float) (Player.getInstance().getHitBox().left - (size.x * 1.0) / 5),
                    top
            );
        }
    }




    private PointF getAtkBoxPosWhenSkillOne() {
        Player.getInstance().setMakingDamage(true);
        float top = Player.getInstance().getHitBox().bottom;

        int idx = Player.getInstance().getAniIndex();
        PointF size = getAtkBoxSizeWhenSkillOne();

        top -= size.y;

        if (idx == 16 || idx == 19 || idx == 22) {

            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
                return new PointF(
                        Player.getInstance().getHitBox().right + (float) (size.x * 1.0) / 5,
                        top
                );
            } else {
                return new PointF(
                        Player.getInstance().getHitBox().left - (float) (size.x * 1.0) / 5,
                        top
                );
            }

        }
        resetEnemyAbleTakeDamage();
        Player.getInstance().setMakingDamage(false);
        return new PointF(0, 0);
    }

    private PointF getAtkBoxSizeWhenSkillOne() {
        int idx = Player.getInstance().getAniIndex();

        if (idx == 16 || idx == 19 || idx == 22) {
            return new PointF(
                    (float) (1.5 * GameConstants.Sprite.SIZE),
                    (float) (1.5 * GameConstants.Sprite.SIZE)
            );
        }

        return new PointF(
                (float) (1.5 * GameConstants.Sprite.SIZE),
                (float) (1.5 * GameConstants.Sprite.SIZE)
        );

    }

    private PointF getAtkBoxSizeWhenAttacking() {
        int idx = Player.getInstance().getAniIndex();
        float scale = (float) Player.getInstance().getGameCharType().getScale();

        if (idx == 9) {
            return new PointF(
                    16 * scale, //width
                    14 * scale //height
            );
        }
        return new PointF(
                27 * scale, //width
                23 * scale //height
        );

    }

    private PointF getAtkBoxPosWhenAttacking() {
        float bottom = Player.getInstance().getHitBox().bottom;
        float scale = (float) Player.getInstance().getGameCharType().getScale();

        Player.getInstance().setMakingDamage(true);

        int idx = Player.getInstance().getAniIndex();
        if (9 == idx) {
            bottom -= 11 * scale;
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

        } else if (11 == idx) {
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
        }  else if (13 == idx) {
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
        }
        resetEnemyAbleTakeDamage();
        Player.getInstance().setMakingDamage(false);
        return new PointF(0, 0);
    }
    @Override
    public float adjustX() {
        PlayerStates state = Player.getInstance().getCurrentStates();
        float scale = (float) Player.getInstance().getGameCharType().getScale();
        float offset = 0;
        if (state == PlayerStates.IDLE) {
            offset = 14;
        } else if (state == PlayerStates.WALK) {
            offset = 10;
        } else if (state == PlayerStates.RUNNING) {
            offset = 6;
        } else if (state == PlayerStates.ATTACK) {
            offset = 27;
        } else if (state == PlayerStates.PROJECTILE) {
            offset = 21;
        } else if (state == PlayerStates.DASH) {
            offset = 14;
        } else if (state == PlayerStates.HURT) {
            offset = 19;
        } else if (state == PlayerStates.SKILL_ONE) {
            offset = 18;
        }
        return offset * scale;
    }

    @Override
    public float adjustY() {
        PlayerStates state = Player.getInstance().getCurrentStates();
        float scale = (float) Player.getInstance().getGameCharType().getScale();
        float offset = 0;
        if (state == PlayerStates.RUNNING) {
            offset = 4;
        } else if (state == PlayerStates.ATTACK) {
            offset = 8;
        } else if (state == PlayerStates.SKILL_ONE) {
            offset = 2;
        }
        return offset * scale;
    }

    @Override
    public int offSetY() { //画出动画时会减去这个值, 数字越大越往上
        int offsetYTop = Player.getInstance().getHitBoxOffsetY();
        PlayerStates state = Player.getInstance().getCurrentStates();
        if (state == PlayerStates.ATTACK) {
            offsetYTop -= Player.getInstance().getHitBoxOffsetY() / 4.5;
        } else if (state == PlayerStates.SKILL_ONE) {
            offsetYTop -= Player.getInstance().getHitBoxOffsetY() / 6;
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
    public int getProjectileMaxHit(PlayerStates state) {
        return 1;
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
                        getProjectSpeed(),
                        GameVideos.FIRE_BALL_ANIM,
                        new PointF(
                                (float) (50 * GameVideos.FIRE_BALL_ANIM.getScale()),
                                -(float) (55 * GameVideos.FIRE_BALL_ANIM.getScale())
                        ));
            }
        } else {
            Player.getInstance().setAbleProjectile(true);
        }
    }



    @Override
    public PointF getProjectileStartPos() {
        float bottom = Player.getInstance().getHitBox().bottom;
        float scale = (float) Player.getInstance().getGameCharType().getScale();

        float top = bottom - (13 * scale) - getProjectileSize().y;

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
                (float) (0.6 * GameConstants.Sprite.SIZE));
    }
}