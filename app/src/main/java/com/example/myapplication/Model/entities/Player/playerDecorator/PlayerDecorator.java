package com.example.myapplication.Model.entities.Player.playerDecorator;


import com.example.myapplication.Model.entities.Player.Player;

public abstract class PlayerDecorator {
    protected Player decoratedPlayer;

    public PlayerDecorator(Player decoratedPlayer) {
        this.decoratedPlayer = decoratedPlayer;
    }

    // Override methods from Player class as needed
}