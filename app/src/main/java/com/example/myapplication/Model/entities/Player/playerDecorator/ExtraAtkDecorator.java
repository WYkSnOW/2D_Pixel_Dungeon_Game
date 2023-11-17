package com.example.myapplication.Model.entities.Player.playerDecorator;

import com.example.myapplication.Model.entities.Player.Player;

public class ExtraAtkDecorator extends PlayerDecorator implements PlayerPowerUp {
    public ExtraAtkDecorator(Player decoratedPlayer) {
        super(decoratedPlayer);
    }

    @Override
    public void applyPowerUp() {
        decoratedPlayer.increaseBaseDamage();
    }
}
