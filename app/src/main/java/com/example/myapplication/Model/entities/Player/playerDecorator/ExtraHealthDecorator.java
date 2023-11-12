package com.example.myapplication.Model.entities.Player.playerDecorator;

import com.example.myapplication.Model.entities.Player.Player;

public class ExtraHealthDecorator implements PlayerPowerUp {

    @Override
    public void applyPowerUp() {
        addExtraHealth();
    }

    private void addExtraHealth() {
        Player.getInstance().increaseHealth(50);
    }
}