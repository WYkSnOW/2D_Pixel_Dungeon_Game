package com.example.myapplication.gmaestates;

import com.example.myapplication.main.Game;

public abstract class BaseState {
    protected Game game;
    public BaseState(Game game) {
        this.game = game;
    }

    public  Game getGame() {
     return game;
    }
}
