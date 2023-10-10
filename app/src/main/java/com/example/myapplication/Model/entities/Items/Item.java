package com.example.myapplication.Model.entities.Items;

import android.graphics.PointF;

public class Item {
    private PointF pos;
    private Items itemType;

    public Item(PointF pos, Items itemType) {
        this.pos = pos;
        this.itemType = itemType;
    }

    public PointF getPos() {
        return pos;
    }

    public Items getItemType() {
        return itemType;
    }
}
