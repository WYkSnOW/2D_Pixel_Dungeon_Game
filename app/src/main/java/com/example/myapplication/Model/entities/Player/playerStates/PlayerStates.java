package com.example.myapplication.Model.entities.Player.playerStates;

public enum PlayerStates {
    IDLE(0),
    WALK(2),
    RUNNING(4),
    ATTACK(6);
    private int animRow;

    PlayerStates(int animRow) {
        this.animRow = animRow;
    }

    public int getAnimRow() {
        return animRow;
    }


}
