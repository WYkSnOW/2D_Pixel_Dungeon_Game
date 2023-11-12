package com.example.myapplication.Model.entities.Items.itemStartegy;

import android.graphics.Canvas;
import android.graphics.PointF;


import com.example.myapplication.Model.entities.Items.Item;
import com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.ItemReactor;
import com.example.myapplication.Model.helper.GameConstants;

public class LoopItemStrategy implements ItemStrategy {

    @Override
    public void drawItem(Canvas c, float cameraX, float cameraY, PointF pos, Item item) {
        c.drawBitmap(
                item.getItemImg(),
                pos.x + cameraX,
                pos.y + cameraY,
                null
        );
        updateAnimation(item.getItemType().getMaxAnimIndex(), item);
    }



    private void updateAnimation(int maxIdx, Item item) {
        item.increaseAniTick();
        if (item.getAniTick() >= GameConstants.Animation.ANI_SPEED) {
            item.setAniTick(0);
            item.increaseAniIndex();
            if (item.getAniIndex() >= maxIdx) {
                item.setAniIndex(0);
            }
        }
    }

    @Override
    public void onReaction(Item item) {
        item.setActive(false);
        item.setAbleReact(false);
        ItemReactor.itemReactor(item.getItemType());
    }

}
