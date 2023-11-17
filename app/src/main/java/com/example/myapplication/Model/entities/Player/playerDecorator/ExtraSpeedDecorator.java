package com.example.myapplication.Model.entities.Player.playerDecorator;

import com.example.myapplication.Model.entities.Player.Player;

public class ExtraSpeedDecorator extends PlayerDecorator implements PlayerPowerUp {
    public ExtraSpeedDecorator(Player decoratedPlayer) {
        super(decoratedPlayer);
    }

    @Override
    public void applyPowerUp() {
       decoratedPlayer.increaseSpeed();
    }
}
