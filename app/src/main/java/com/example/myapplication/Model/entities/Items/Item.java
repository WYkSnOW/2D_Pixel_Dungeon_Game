package com.example.myapplication.Model.entities.Items;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

import com.example.myapplication.Model.entities.Entity;
import com.example.myapplication.Model.entities.Items.itemStartegy.ItemStrategy;


public class Item extends Entity {
    private final PointF pos;
    private final Items itemType;
    private boolean ableReact;
    private boolean readyInteract;
    private ItemStrategy itemStrategy;
    private int aniIndex = 0;
    private int aniTick = 0;

    public Item(PointF pos, Items itemType) {
        super(pos, itemType.getWidth(), itemType.getHeight());
        this.pos = pos;
        this.ableReact = itemType.isAbleReact();
        this.itemType = itemType;
        this.readyInteract = false;
        this.itemStrategy = itemType.getItemStrategy();
    }


    public ItemStrategy getItemStrategy() {
        return itemStrategy;
    }

    public void drawItem(Canvas c, float cameraX, float cameraY) {
        itemStrategy.drawItem(c, cameraX, cameraY, pos, this);
        itemStrategy.drawItemHitBox(c, cameraX, cameraY, this);
    }
    public void onReaction() {
        itemStrategy.onReaction(this);
    }

    public PointF getPos() {
        return pos;
    }

    public Items getItemType() {
        return itemType;
    }
    public Bitmap getItemImg() {
        return itemType.getItemImg(aniIndex); //y is row in asset，x is list in asset
    }

    public boolean isReadyInteract() {
        return readyInteract;
    }

    public void setReadyInteract(boolean readyInteract) {
        this.readyInteract = readyInteract;
    }

    public void setAbleReact(boolean ableReact) {
        this.ableReact = ableReact;
    }

    public boolean isAbleReact() {
        return ableReact;
    }

    public int getAniIndex() {
        return aniIndex;
    }
    public void increaseAniTick() {
        aniTick += 1;
    }
    public void increaseAniIndex() {
        aniIndex += 1;
    }

    public int getAniTick() {
        return aniTick;
    }

    public void setAniIndex(int aniIndex) {
        this.aniIndex = aniIndex;
    }

    public void setAniTick(int aniTick) {
        this.aniTick = aniTick;
    }
}
