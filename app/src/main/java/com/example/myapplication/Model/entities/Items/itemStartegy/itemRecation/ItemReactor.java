package com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation;

import com.example.myapplication.Model.entities.Items.Items;
import com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.itemReaction.BlueHeart;
import com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.itemReaction.RedHeart;
import com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.itemReaction.YellowHeart;

public class ItemReactor {
    public static void itemReactor(Items itemType) {
        ItemReaction itemReaction;
        if (itemType == Items.READ_HEART) {
            itemReaction = new RedHeart();
        } else if (itemType == Items.BLUE_HEART) {
            itemReaction = new BlueHeart();
        } else if (itemType == Items.YELLOW_HEART) {
            itemReaction = new YellowHeart();
        } else {
            itemReaction = new RedHeart();
        }
        itemReaction.itemReaction();
    }
}
