package com.example.myapplication.Model.environments;

public class GameMap { //store world and help to draw map
    private int[][] spriteIds;
    public GameMap(int[][] spriteIds) {
        this.spriteIds = spriteIds;
    }


    public int getSpriteID(int xIndex, int yIndex) {
        if (spriteIds[yIndex][xIndex] == 0) {
            return 0;
        }
        return spriteIds[yIndex][xIndex] - 1;
    }

    public int getArrayWidth() {
        return spriteIds[0].length;
    }
    public int getArrayHeight() {
        return spriteIds.length;
    }
}
