package com.example.myapplication.Model.entities.Player.playerDecorator;

import com.example.myapplication.Model.entities.Player.Player;

public class ExtraHealthDecorator extends PlayerDecorator implements PlayerPowerUp {
    private int amount;

    public ExtraHealthDecorator(Player decoratedPlayer, int amount) {
        super(decoratedPlayer);
        this.amount = amount;
    }


    @Override
    public void applyPowerUp() {
        decoratedPlayer.increaseHealth(this.amount);
    }

    public Player getDecoratedPlayer() {
        return this.decoratedPlayer;
    }
}