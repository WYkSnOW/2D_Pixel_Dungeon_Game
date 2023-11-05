package com.example.myapplication.Model.entities.Player.projectile;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.Entity;

public class Projectile extends Entity {
    private float speed;
    private boolean faceRight;
    private int hitEnemyCount;

    public Projectile(PointF pos, PointF size, boolean faceRight, float speed) {
        super(pos, size.x, size.y);
        this.faceRight = faceRight;
        this.speed = speed;
        this.hitEnemyCount = 0;
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

    public void updateHitCount(int maxHit) {
        this.hitEnemyCount += 1;
        if (hitEnemyCount >= maxHit) {
            active = false;
        }
    }

    public void increaseHitCount() {
        this.hitEnemyCount += 1;
    }
}
