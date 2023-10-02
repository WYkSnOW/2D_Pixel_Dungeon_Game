package com.example.myapplication.gmaestates;

import android.view.KeyEvent;

import com.example.myapplication.main.Game;

public abstract class BaseState {
    protected Game game;
    public BaseState(Game game) {
        this.game = game;
    }

    public  Game getGame() {
     return game;
    }

    public abstract void onKeyDown(int keyCode, KeyEvent event);
}
