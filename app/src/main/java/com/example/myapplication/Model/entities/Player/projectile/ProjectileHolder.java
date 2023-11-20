package com.example.myapplication.Model.entities.Player.projectile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.myapplication.Model.entities.Player.projectile.projecttileStrategy.ProjectileStrategy;
import com.example.myapplication.Model.entities.Player.projectile.projecttileStrategy.ShotProjectile;
import com.example.myapplication.Model.environments.GameMap;
import com.example.myapplication.Model.loopVideo.GameVideos;

import java.util.ArrayList;

public class ProjectileHolder {
    private ArrayList<Projectile> proList;
    private  static ProjectileHolder instance;
    private boolean isEmpty;
    private boolean onClearing;
    private Paint hitBoxPaint;
    private float cameraX;
    private float cameraY;


    private ProjectileHolder() {
        proList = new ArrayList<>();
        isEmpty = true;
        hitBoxPaint = new Paint();
        hitBoxPaint.setStrokeWidth(1);
        hitBoxPaint.setStyle(Paint.Style.STROKE);
        hitBoxPaint.setColor(Color.RED);
        cameraX = 0;
        cameraY = 0;
        onClearing = false;
    }
    public static synchronized ProjectileHolder getInstance() {
        if (instance == null) {
            instance = new ProjectileHolder();
        }
        return instance;
    }

    public ArrayList<Projectile> getProList() {
        return proList;
    }

    public void addProjectile(
            PointF pos, PointF size, boolean faceRight, float speed,
            GameVideos anim, PointF animOffset,
            ProjectileStrategy projectileStrategy) {

        proList.add(new Projectile(
                new PointF(pos.x - cameraX, pos.y - cameraY),
                size,
                faceRight,
                speed,
                anim,
                animOffset,
                projectileStrategy)
        );
        isEmpty = false;
    }


    public void update(double delta, GameMap gameMap, float cameraX, float cameraY) {
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        if (isEmpty || onClearing) {
            return;
        }

        for (int i = 0; i < proList.size(); i++) {
            Projectile p = proList.get(i);
            if (p.isActive()) {

                float currentSpeed = (float) (delta * p.getSpeed());
                if (!p.isFaceRight()) {
                    currentSpeed *= -1;
                }

                p.updatePos(currentSpeed);
                if (!(gameMap.canMoveHere(
                        p.getHitBox().left, p.getHitBox().top, p.getHitBox().bottom))
                        || !(gameMap.canMoveHere(
                        p.getHitBox().right, p.getHitBox().top, p.getHitBox().bottom))) {
                    p.setActive(false);
                    onClearing = true;
                    proList.remove(p);
                    onClearing = false;
                }
            }
        }
        if (proList.size() == 0) {
            isEmpty = true;
        }
    }

    public void draw(Canvas c) {
        if (isEmpty || onClearing) {
            return;
        }

        for (Projectile p : ProjectileHolder.getInstance().getProList()) {
            if (p.isActive()) {
                p.drawProjectileHitBox(c, cameraX, cameraY, hitBoxPaint);
                p.drawProjectile(c, cameraX, cameraY);
            }
        }
    }



}