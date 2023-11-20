package com.example.myapplication.Model.entities.Player.projectile.projecttileStrategy;


public class StayProjectile implements ProjectileStrategy {
    @Override
    public boolean ableMakeDamage(int idx) {
        return false;
    }

    @Override
    public boolean deactivateWhenAnimDone() {
        return true;
    }


}
