package com.example.myapplication.Model.environments.Doorways;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.environments.GameMap;

public class Doorway {
    private RectF hitbox;
    private boolean active = true;
    private final GameMap gameMapLocatedIn;
    private Doorway doorwayConnectedTo;


    public Doorway(RectF doorwayHitBox, GameMap gameMapLocatedIn) {
        this.hitbox = doorwayHitBox;
        this.gameMapLocatedIn = gameMapLocatedIn;
        gameMapLocatedIn.addDoorway(this);
    }

    public void connectDoorway(Doorway destinationDoorway) {
        this.doorwayConnectedTo = destinationDoorway;

    }

    public Doorway getDoorwayConnectedTo() {
        if (doorwayConnectedTo != null) {
            return doorwayConnectedTo;
        } else {
            return null;
        }
    }

    public PointF getPosOfDoorway() {
        return new PointF(hitbox.left, hitbox.top);
    }

    public boolean isPlayerInsideDoorway(RectF playerHitbox, float cameraX, float cameraY) {
        return playerHitbox.intersects(
                hitbox.left + cameraX,
                hitbox.top + cameraY,
                hitbox.right + cameraX,
                hitbox.bottom + cameraY
        );

    }

    public boolean isDoorwayActive() {
        return active;
    }

    public void setDoorwayActive(boolean active) {
        this.active = active;
    }

    public GameMap getGameMapLocatedIn() {
        return gameMapLocatedIn;
    }

    public RectF getHitbox() {
        return hitbox;
    }

}
