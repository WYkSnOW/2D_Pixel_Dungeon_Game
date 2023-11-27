package com.example.myapplication.Model.entities.Player.projectile.projecttileStrategy;


public interface ProjectileStrategy {
    abstract boolean ableMakeDamage(int idx);
    abstract boolean deactivateWhenAnimDone();
}
