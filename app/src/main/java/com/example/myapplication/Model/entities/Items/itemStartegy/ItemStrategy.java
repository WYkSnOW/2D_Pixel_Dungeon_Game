package com.example.myapplication.Model.entities.Items.itemStartegy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;


import com.example.myapplication.Model.entities.Items.Item;


public interface ItemStrategy {
    Paint HITBOXPAINT = new Paint();

    abstract void drawItem(Canvas c, float cameraX, float cameraY, PointF pos, Item item);
    abstract void onReaction(Item item);
    default void drawItemHitBox(Canvas c, float cameraX, float cameraY, Item item) {
        HITBOXPAINT.setStrokeWidth(1);
        HITBOXPAINT.setStyle(Paint.Style.STROKE);
        HITBOXPAINT.setColor(Color.RED);
        c.drawRect(
                item.getHitBox().left + cameraX,
                item.getHitBox().top + cameraY,
                item.getHitBox().right + cameraX,
                item.getHitBox().bottom + cameraY,
                HITBOXPAINT); //draw mob's hitBox
    }
}