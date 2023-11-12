package com.example.myapplication.Model.entities.Player.playerDecorator;


public class PlayerDecorator {
    protected PlayerPowerUp powerUp;

    public void setPowerUp(PlayerPowerUp powerUp) {
        this.powerUp = powerUp;
        if (this.powerUp != null) {
            this.powerUp.applyPowerUp();
        }
    }

}