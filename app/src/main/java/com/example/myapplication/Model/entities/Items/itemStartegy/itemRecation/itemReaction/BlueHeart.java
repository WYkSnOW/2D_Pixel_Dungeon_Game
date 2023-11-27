package com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.itemReaction;


import com.example.myapplication.Model.entities.Items.itemStartegy.itemRecation.ItemReaction;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerDecorator.ExtraHealthDecorator;
import com.example.myapplication.Model.entities.Player.playerDecorator.ExtraSpeedDecorator;

public class BlueHeart implements ItemReaction {
    @Override
    public void itemReaction() {
        ExtraSpeedDecorator extraSpeedDecorator
                = new ExtraSpeedDecorator(Player.getInstance());

        extraSpeedDecorator.applyPowerUp();
    }
}