package com.example.myapplication.Model.entities.Player.projectile;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.myapplication.Model.entities.Entity;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.loopVideo.GameVideos;

public class Projectile extends Entity {
    private float speed;
    private boolean faceRight;
    private int damage;
    private int hitEnemyCount;
    private int maxHit;
    private int aniIndex = 0;
    private int aniTick = 0;
    private int direction = 0;
    private final GameVideos anim;
    private final float animOffsetX;
    private final float animOffsetY;

    public Projectile(PointF pos, PointF size, boolean faceRight,
                      float speed, GameVideos anim, PointF animOffset) {

        super(pos, size.x, size.y);
        this.faceRight = faceRight;
        this.speed = speed;
        this.hitEnemyCount = 0;
        this.damage = Player.getInstance().getCurrentDamage();
        this.maxHit = Player.getInstance().getProjectileMaxHit();
        this.anim = anim;
        this.animOffsetX = animOffset.x;
        this.animOffsetY = animOffset.y;

        if (faceRight) {
            direction = 0;
        } else {
            direction = 1;
        }
    }

    public void updatePos(float currentSpeed) {
        hitBox.left += currentSpeed;
        hitBox.right += currentSpeed;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isFaceRight() {
        return faceRight;
    }

    public int getHitEnemyCount() {
        return hitEnemyCount;
    }

    public void updateHitCount() {
        if (maxHit == -1) {
            return;
        }

        this.hitEnemyCount += 1;
        if (hitEnemyCount >= maxHit) {
            active = false;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void increaseHitCount() {
        this.hitEnemyCount += 1;
    }

    public void drawProjectileHitBox(Canvas c, float cameraX, float cameraY, Paint hitBoxPaint) {
        c.drawRect(hitBox.left + cameraX,
                hitBox.top + cameraY,
                hitBox.right + cameraX,
                hitBox.bottom + cameraY,
                hitBoxPaint);

    }


    protected void updateAnimation() {
        aniTick++;
        if (aniTick >= GameConstants.Animation.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= anim.getMaxAnimIndex()) {
                aniIndex = 0;
            }
        }
    }


    public void drawProjectile(Canvas c, float cameraX, float cameraY) {
        float xPos = hitBox.left - animOffsetX;
        if (faceRight) {
            xPos = hitBox.right - anim.getWidth() + animOffsetX;
        }
        c.drawBitmap(
                anim.getSprite(direction, aniIndex),
                xPos + cameraX,
                hitBox.top + cameraY + animOffsetY,
                null
        );
        updateAnimation();
    }





}
