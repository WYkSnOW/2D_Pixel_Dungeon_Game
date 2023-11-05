package com.example.myapplication.View.main.gameStates;

import com.example.myapplication.Model.coreLogic.Game;

public abstract class BaseState {
    protected Game game;
    public BaseState(Game game) {
        this.game = game;
    }

    public  Game getGame() {
        return game;
    }
}
