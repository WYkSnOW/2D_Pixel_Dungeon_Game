package com.example.myapplication.Model.entities.Items;

import static com.example.myapplication.Model.helper.GameConstants.Sprite.DEFAULT_SIZE;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MainViewModel;

public enum Items implements BitmapMethods {

    CHEST_ONE(7, 2, 1, 1); //单位为格子

    private Bitmap itemImg;


    Items(int x, int y, int width, int height) {
        OPTIONS.inScaled = false;

        Bitmap atlas = BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(),
                R.drawable.map_resource, OPTIONS
        ); //now using map resource, might change later
        itemImg = getScaledBitmap(Bitmap.createBitmap(
                atlas,
                x * DEFAULT_SIZE,
                y * DEFAULT_SIZE,
                width * DEFAULT_SIZE,
                height * DEFAULT_SIZE)
        );
    }

    public Bitmap getItemImg() {
        return itemImg;
    }

}
