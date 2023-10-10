package com.example.myapplication.Model.helper.interfaces;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Model.helper.GameConstants;

public interface BitmapMethods {
    BitmapFactory.Options OPTIONS = new BitmapFactory.Options();

    default Bitmap getScaledBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(
                bitmap,
                bitmap.getWidth() * GameConstants.Sprite.SCALE_MULTIPLIER,
                bitmap.getHeight() * GameConstants.Sprite.SCALE_MULTIPLIER,
                false
        ); //将图片放大六倍（后期更换constant）
    }
}
