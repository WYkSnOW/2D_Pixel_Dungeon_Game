package com.example.myapplication.Model.entities;

import android.graphics.PointF;
import android.graphics.RectF;

public abstract class Entity {
    protected RectF hitBox;
    protected boolean active = true;
    public Entity(PointF pos, float width, float height) {
        this.hitBox = new RectF(pos.x, pos.y, pos.x + width, pos.y + height);
    }

    public RectF getHitBox() {
        return hitBox;
    }


    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }

}
