package com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.itemReaction;


import com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.ItemReaction;
import com.example.myapplication.Model.entities.Player.Player;

public class YellowHeart implements ItemReaction {
    @Override
    public void itemReaction() {
        Player.getInstance().increaseBaseDamage();
    }
}