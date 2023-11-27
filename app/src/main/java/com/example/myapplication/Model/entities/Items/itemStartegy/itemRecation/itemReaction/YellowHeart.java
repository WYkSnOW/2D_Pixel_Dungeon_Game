package com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.itemReaction;


import com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.ItemReaction;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerDecorator.ExtraAtkDecorator;

public class YellowHeart implements ItemReaction {
    @Override
    public void itemReaction() {
        ExtraAtkDecorator extraAtkDecorator = new ExtraAtkDecorator(Player.getInstance());
        extraAtkDecorator.applyPowerUp();
    }
}