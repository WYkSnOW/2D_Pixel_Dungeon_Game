package com.example.myapplication.Model.entities.Player.projectile.projecttileStrategy;


public class ShotProjectile implements ProjectileStrategy {
    @Override
    public boolean ableMakeDamage(int idx) {
        return true;
    }

    @Override
    public boolean deactivateWhenAnimDone() {
        return false;
    }

}
