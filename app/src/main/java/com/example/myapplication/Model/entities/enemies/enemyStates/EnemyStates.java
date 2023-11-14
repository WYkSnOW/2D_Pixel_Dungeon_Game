package com.example.myapplication.Model.entities.enemies.enemyStates;

public enum EnemyStates {

    WALK(0),
    IDLE(2),
    ATK(4),
    HURT(6),
    DEATH(8);
    private final int animRow;

    EnemyStates(int animRow) {
        this.animRow = animRow;
    }

    public int getAnimRow() {
        return animRow;
    }

}
