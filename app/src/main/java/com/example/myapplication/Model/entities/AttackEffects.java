package com.example.myapplication.Model.entities;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.View.main.MainActivity;

public enum AttackEffects implements BitmapMethods {

    BIG_SWORD(R.drawable.big_sword);

    final Bitmap weaponImg;

    AttackEffects(int resId) {
        options.inScaled = false;
        weaponImg = getScaledBitmap(BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resId, options));
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