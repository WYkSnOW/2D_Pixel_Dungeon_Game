package com.example.myapplication.Model.entities.Player;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MainViewModel;

public enum AttackEffects implements BitmapMethods {

    BIG_SWORD(R.drawable.big_sword);

    private final Bitmap weaponImg;

    AttackEffects(int resId) {
        OPTIONS.inScaled = false;
        weaponImg = getScaledBitmap(BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(), resId, OPTIONS)
        );
    }

    public Bitmap getWeaponImg() {
        return weaponImg;
    }

    public int getWidth() {
        return weaponImg.getWidth();
    }

    public int getHeight() {
        return weaponImg.getHeight();
    }
}