package com.example.myapplication.Model.environments.Doorways;

import static com.example.myapplication.Model.helper.GameConstants.Sprite.SIZE;

import com.example.myapplication.Model.helper.interfaces.BitmapMethods;

public enum DoorwayType implements BitmapMethods {

    DOORWAY_ONE(1, 3, 0), //单位为格子数量, offset单位为像素
    DOORWAY_TWO(2, 1, 50),
    DOORWAY_Three(2, 1, 0);

    private int doorwayWidth;
    private int doorwayHeight;
    private int offsetY;

    DoorwayType(int doorwayWidth, int doorwayHeight, int offsetY) {
        this.doorwayWidth = doorwayWidth * SIZE;
        this.doorwayHeight = doorwayHeight * SIZE;
        this.offsetY = offsetY;
    }

    public int getDoorwayWidth() {
        return doorwayWidth;
    }

    public int getDoorwayHeight() {
        return doorwayHeight;
    }

    public int getOffsetY() {
        return offsetY;
    }
}
