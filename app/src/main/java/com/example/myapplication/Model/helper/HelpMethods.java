package com.example.myapplication.Model.helper;

import android.app.Activity;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.Zombie;
import com.example.myapplication.Model.environments.Doorways.Doorway;
import com.example.myapplication.Model.environments.Doorways.DoorwayType;
import com.example.myapplication.Model.environments.GameMap;
import com.example.myapplication.Model.ui.CustomButton;

import java.util.ArrayList;

public class HelpMethods {

    public static void connectTwoDoorways(
            GameMap gameMapOne, RectF hitboxOne, GameMap gameMapTwo, RectF hitboxTwo
    ) {
        Doorway doorwayOne = new Doorway(hitboxOne, gameMapOne);
        Doorway doorwayTwo = new Doorway(hitboxTwo, gameMapTwo);

        doorwayOne.connectDoorway(doorwayTwo);
        doorwayTwo.connectDoorway(doorwayOne);
    }
    public static RectF createHitboxForDoorway(int xTile, int yTile, DoorwayType doorwayType) {
        float x = xTile * GameConstants.Sprite.SIZE;
        float y = yTile * GameConstants.Sprite.SIZE;

        return new RectF(
                x,
                y - doorwayType.getOffsetY(),
                x + doorwayType.getDoorwayWidth(),
                y + doorwayType.getDoorwayHeight() - doorwayType.getOffsetY());
    }
    public static ArrayList<Zombie> getZombieRandomized(int amount, GameMap gameMap) {
        int width = (gameMap.getArrayWidth() - 1) * GameConstants.Sprite.SIZE;
        int height = (gameMap.getMapHeight() - 1) * GameConstants.Sprite.SIZE;

        ArrayList<Zombie> zombieArrayList = new ArrayList<>();
        int i = 0;
        while (i < amount) {
            float x = (float) Math.random() * width;
            float y = (float) Math.random() * height;
            if (gameMap.canMoveHere(x, y, y + (GameCharacters.ZOMBIE.getCharacterHeight()))) {
                zombieArrayList.add(new Zombie(new PointF(x, y)));
                i++;
            }
        }
        return zombieArrayList;
    }
    public static void cleanUi(Activity activity) {
        if (activity != null) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
    }
    public static boolean isInBtn(MotionEvent e, CustomButton b) {
        return b.getHitbox().contains(e.getX(), e.getY());
    }

    public static PointF playerMovementIdle() {
        return new PointF(0,0);
    }

    public static PointF playerMovementRun(float xSpeed, float ySpeed, float baseSpeed) {
        float deltaX = xSpeed * baseSpeed * -1; //移动镜头而不是角色
        float deltaY = ySpeed * baseSpeed * -1; //因镜头需与角色相反的方向移动，即乘以-1
        System.out.println(deltaX < 0);
        System.out.println(deltaY);
        return new PointF(deltaX, deltaY);
    }

}
