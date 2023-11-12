package com.example.myapplication.Model.entities.Items.itemStartegy;

import android.graphics.Canvas;
import android.graphics.PointF;


import com.example.myapplication.Model.entities.Items.Item;
import com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.ItemReactor;
import com.example.myapplication.Model.helper.GameConstants;

public class ReactiveItem implements ItemStrategy {
    private boolean startLoop = false;
    private boolean finishLoop = false;


    @Override
    public void drawItem(Canvas c, float cameraX, float cameraY, PointF pos, Item item) {
        if (startLoop) {
            updateAnimation(item.getItemType().getMaxAnimIndex(), item);
        }

        c.drawBitmap(
                item.getItemImg(),
                pos.x + cameraX,
                pos.y + cameraY,
                null
        );

    }


    protected void updateAnimation(int maxIdx, Item item) {
        if (!finishLoop) {
            item.increaseAniTick();
            if (item.getAniTick() >= GameConstants.Animation.ANI_SPEED) {
                item.setAniTick(0);
                item.increaseAniIndex();
                if (item.getAniIndex() + 1 >= maxIdx) {
                    finishLoop = true;
                }
            }
        }
    }

    @Override
    public void onReaction(Item item) {
        startLoop = true;
        item.setAbleReact(false);
        ItemReactor.itemReactor(item.getItemType());

    }
}
