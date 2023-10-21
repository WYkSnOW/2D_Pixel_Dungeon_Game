package com.example.myapplication.Model.environments;

import com.example.myapplication.Model.entities.Items.Item;
import com.example.myapplication.Model.entities.enemies.Zombie;
import com.example.myapplication.Model.environments.Doorways.Doorway;
import com.example.myapplication.Model.helper.GameConstants;

import java.util.ArrayList;

public class GameMap { //store world and help to draw map
    private int[][] spriteIds;
    private Floor floorType;
    private ArrayList<Item> itemArrayList;
    private ArrayList<Doorway> doorwayArrayList;
    private ArrayList<Zombie> zombieArrayList;



    public GameMap(int[][] spriteIds, Floor floorType, ArrayList<Item> itemArrayList) {
        this.spriteIds = spriteIds;
        this.floorType = floorType;
        this.itemArrayList = itemArrayList;
        this.doorwayArrayList = new ArrayList<>();
        this.zombieArrayList = new ArrayList<>();
    }

    public void addDoorway(Doorway doorway) {
        this.doorwayArrayList.add(doorway);
    }

    public ArrayList<Doorway> getDoorwayArrayList() {
        return doorwayArrayList;
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public Floor getFloorType() {
        return floorType;
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

    public ArrayList<Zombie> getZombieArrayList() {
        return zombieArrayList;
    }

    public int getMapWidth() {
        return getArrayWidth() * GameConstants.Sprite.SIZE;
    }
    public int getMapHeight() {
        return getArrayHeight() * GameConstants.Sprite.SIZE;
    }

    public void addZombiesToList(ArrayList<Zombie> zombies) {
        if (zombies != null) {
            zombieArrayList.addAll(zombies);
        }
    }
    public void replaceZombiesToList(ArrayList<Zombie> zombies) {
        if (zombies != null) {
            zombieArrayList = zombies;
        }
    }


    public static boolean checkEdge(
            float x, float yTop, float yBottom, float mapWidth, float mapHeight
    ) {
        if (x < 0 || yTop < 0 || yBottom < 0) {
            return false;
        }
        if (x >= mapWidth || yTop >= mapHeight || yBottom >= mapHeight) {
            return false;
        }
        return true;
    }


    public boolean canMoveHere(float x, float yTop, float yBottom) {

        if (!checkEdge(x, yTop, yBottom, getMapWidth(), getMapHeight())) {
            return false;
        }

        int tileX = (int) (x / GameConstants.Sprite.SIZE);
        int tileYTop = (int) (yTop / GameConstants.Sprite.SIZE);
        int tileYBottom = (int) (yBottom / GameConstants.Sprite.SIZE);

        int tileIdTop = getSpriteID(tileX, tileYTop);
        int tileIdBottom = getSpriteID(tileX, tileYBottom);
        //System.out.println("Top: " + yTop + "  Bottom: " + yBottom);

        return (isMoveAbleBlock(tileIdTop) && isMoveAbleBlock(tileIdBottom));
    }
    public static boolean isMoveAbleBlock(int tiledId) {
        return tiledId == 15
                || tiledId == 123
                || tiledId == 124
                || tiledId == 134
                || tiledId == 135
                || tiledId == 136
                || tiledId == 148
                || tiledId == 149
                || tiledId == 112
                || tiledId == 125
                || tiledId == 113
                || tiledId == 126
                || tiledId == 114
                || tiledId == 127
                || tiledId == 140
                || tiledId == 115
                || tiledId == 128
                || tiledId == 141;
    }

}
