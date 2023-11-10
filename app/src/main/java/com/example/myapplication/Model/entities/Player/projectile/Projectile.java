package com.example.myapplication.Model.entities.Player.projectile;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.Entity;
import com.example.myapplication.Model.environments.GameMap;

public class Projectile extends Entity {
    private float speed;
    private boolean faceRight;

    public Projectile(PointF pos, PointF size, boolean faceRight, float speed) {
        super(pos, size.x, size.y);
        this.faceRight = faceRight;
        this.speed = speed;
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
}
