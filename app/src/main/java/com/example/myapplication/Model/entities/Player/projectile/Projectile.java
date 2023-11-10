package com.example.myapplication.Model.entities.Player.projectile;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.Entity;
import com.example.myapplication.Model.entities.Player.Player;

public class Projectile extends Entity {
    private float speed;
    private boolean faceRight;
    private int damage;
    private int hitEnemyCount;
    private int maxHit;

    public Projectile(PointF pos, PointF size, boolean faceRight, float speed) {
        super(pos, size.x, size.y);
        this.faceRight = faceRight;
        this.speed = speed;
        this.hitEnemyCount = 0;
        this.damage = Player.getInstance().getCurrentDamage();
        this.maxHit = Player.getInstance().getProjectileMaxHit();
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
}
