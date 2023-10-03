package com.example.myapplication.ViewModel.gmaestates;

import com.example.myapplication.View.main.Game;

public abstract class BaseState {
    protected Game game;
    public BaseState(Game game) {
        this.game = game;
    }

    public  Game getGame() {
     return game;
    }
}
