package com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.itemReaction;


import com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.ItemReaction;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerDecorator.ExtraHealthDecorator;

public class RedHeart implements ItemReaction {
    @Override
    public void itemReaction() {
        ExtraHealthDecorator extraHealthDecorator
                = new ExtraHealthDecorator(Player.getInstance(), 50);

        extraHealthDecorator.applyPowerUp();
    }
}