package com.example.myapplication.helper.interfaces;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.helper.GameConstants;

public interface BitmapMethods {
    BitmapFactory.Options options = new BitmapFactory.Options();

    default Bitmap getScaledBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 6, bitmap.getHeight() * GameConstants.Sprite.SCALE_MULTIPLIER, false); //将图片放大六倍（后期更换constant）
    }
}
