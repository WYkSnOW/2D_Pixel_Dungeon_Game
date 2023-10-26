package com.example.myapplication.Model.entities.Player.playerStates;

public enum PlayerStates {
    RUNNING(0),
    IDLE(2),
    ATTACK(4);
    private int animRow;

    PlayerStates(int animRow) {
        this.animRow = animRow;
    }

    public int getAnimRow() {
        return animRow;
    }


}
