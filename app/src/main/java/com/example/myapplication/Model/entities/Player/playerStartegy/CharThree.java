package com.example.myapplication.Model.entities.Player.playerStartegy;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.helper.GameConstants;

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
    public int getAnimMaxIndex(PlayerStates state) {
        if (state == PlayerStates.IDLE) {
            return 6;
        } else if (state == PlayerStates.RUNNING) {
            return 8;
        } else if (state == PlayerStates.ATTACK) {
            return 27;
        } else if (state == PlayerStates.WALK) {
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

    private PointF getAtkBoxSizeWhenAttacking() {
        int idx = Player.getInstance().getAniIndex();
        Player.getInstance().setAbleMakeDamage(true);

        if (6 <= idx && idx <= 8) {
            return new PointF(
                    (float) (0.9 * GameConstants.Sprite.SIZE),
                    (float) (1.1 * GameConstants.Sprite.SIZE)
            );
        }

        if (12 <= idx && idx <= 14) {
            return new PointF(
                    (float) (0.9 * GameConstants.Sprite.SIZE),
                    (float) (1.5 * GameConstants.Sprite.SIZE)
            );
        }

        if (19 <= idx && idx <= 22) {
            return new PointF(
                    (float) (1.2 * GameConstants.Sprite.SIZE),
                    (float) (0.3 * GameConstants.Sprite.SIZE)
            );
        }


        Player.getInstance().setAbleMakeDamage(false);
        return new PointF(
                (float) (1.2 * GameConstants.Sprite.SIZE),
                (float) (2 * GameConstants.Sprite.SIZE)
        );
    }

    private PointF getAtkBoxPosWhenAttacking() {
        float top = Player.getInstance().getHitBox().top;

        int idx = Player.getInstance().getAniIndex();
        if (6 <= idx && idx <= 8) {
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

        if (12 <= idx && idx <= 14) {
            top -= Player.getInstance().getHitBoxOffSetY() / 3f;

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

        if (19 <= idx && idx <= 22) {
            top += Player.getInstance().getHitBoxOffSetY() / 3.3f;

            if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
                return new PointF(
                        Player.getInstance().getHitBox().left,
                        top
                );
            } else  {
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
            offsetXRight = Player.getInstance().getHitBoxOffsetX();
            offsetXLeft = 0;
        } else if (state == PlayerStates.RUNNING) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 3;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 3;
        } else if (state == PlayerStates.WALK) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX() / 2;
            offsetXLeft += Player.getInstance().getHitBoxOffsetX() / 2;
        } else if (state == PlayerStates.ATTACK) {
            offsetXRight -= Player.getInstance().getHitBoxOffsetX();
            offsetXLeft += Player.getInstance().getHitBoxOffsetX();
        }


        if (dir == GameConstants.FaceDir.LEFT) {
            return offsetXLeft;
        }
        return offsetXRight;
    }

    @Override
    public int offSetY() {
        return Player.getInstance().getHitBoxOffSetY();
    }
}
