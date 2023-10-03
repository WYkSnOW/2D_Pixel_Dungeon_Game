package com.example.myapplication.Model.environments;

import android.graphics.Canvas;

import com.example.myapplication.Model.helper.GameConstants;

public class MapManager {
    private GameMap currentMap;
    private float cameraX, cameraY;
    public MapManager() {
        initTestMap();
    }

    public void draw(Canvas c) {
        for(int j = 0; j < currentMap.getArrayHeight(); j++)
            for(int i = 0; i < currentMap.getArrayWidth(); i++)
                c.drawBitmap(Floor.OUTSIDE.getSprite(currentMap.getSpriteID(i, j)), i * GameConstants.Sprite.SIZE + cameraX, j * GameConstants.Sprite.SIZE + cameraY, null); //原像素16，放大6倍，即96
    }

    private void initTestMap() {
        int[][] spriteIds = { //2d array that use number to represent block in map asset
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,2,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,2,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,45,0},
                {0,54,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,57,58,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}


        };

        currentMap = new GameMap(spriteIds);
    }


    public void setCameraValues(float cameraX, float cameraY) {
        this.cameraY = cameraY;
        this.cameraX = cameraX;
    }

    public boolean canMoveHere(float x, float y) {
        if(x < 0 || y < 0) {
            return false;
        }
        if(x >= getMaxWidthCurrentMap() || y >= getMaxHeightCurrentMap())
            return false;

        return true;
    }

    public int getMaxWidthCurrentMap() {
        return currentMap.getArrayWidth() * GameConstants.Sprite.SIZE;
    }
    public int getMaxHeightCurrentMap() {
        return currentMap.getArrayHeight() * GameConstants.Sprite.SIZE;
    }


}
