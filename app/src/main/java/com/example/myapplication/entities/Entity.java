package com.example.myapplication.entities;

import android.graphics.PointF;
import android.graphics.RectF;

public abstract class Entity {
    protected RectF hitBox;
    public Entity(PointF pos, float width, float height) {
        this.hitBox = new RectF(pos.x, pos.y, width, height);
    }

    public RectF getHitBox() {
        return hitBox;
    }
}
