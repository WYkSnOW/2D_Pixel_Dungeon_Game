package com.example.myapplication.Model.ui.playingUI.playerStateBar;

import android.graphics.Canvas;
import android.graphics.PointF;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.loopVideo.GameVideos;


public class PlayerStateBar {

    private PointF leftTop;
    private float currentHealthBarX;
    private int healthHealthBarEndAnimIdx = 0;
    private int aniTick = 0;


    private static PlayerStateBar instance;


    public PlayerStateBar() {
        this.leftTop = new PointF(5, 5);
        this.currentHealthBarX = leftTop.x;
    }



    public static synchronized PlayerStateBar getInstance() {
        if (instance == null) {
            instance = new PlayerStateBar();
        }
        return instance;
    }


    public void drawPlayerStateBar(Canvas c) {
        drawHealthBar(c);
    }




    private void drawHealthBar(Canvas c) {
        drawHealthBarBackground(c);




        double percentHealth = Player.getInstance().getPercentHealth();
        currentHealthBarX = leftTop.x;

        if (percentHealth > 0) {
            drawHealthStart(c, percentHealth);
        }
        if (percentHealth > 10) {
            drawHealthMiddle(c, percentHealth);
        }
        if (percentHealth > 90) {
            drawHealthEnd(c, percentHealth);
        }

        if (percentHealth <= 97 && (percentHealth != 0)) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_END_ANIM.getSprite(0, healthHealthBarEndAnimIdx),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            updateHealthEndAnimation();
        }

    }


    private void updateHealthEndAnimation() {
        aniTick++;
        if (aniTick >= GameVideos.PLAYER_HEALTH_END_ANIM.getAnimRate()) {
            aniTick = 0;
            healthHealthBarEndAnimIdx++;
            if (healthHealthBarEndAnimIdx >= GameVideos.PLAYER_HEALTH_END_ANIM.getMaxAnimIndex()) {
                healthHealthBarEndAnimIdx = 0;
            }
        }
    }

    private void drawHealthEnd(Canvas c, double percentHealth) {
        float playerHealthMiddleWidth = (float) (GameVideos.PLAYER_HEALTH_END.getWidth());

        double healthDraw = 90;

        if (healthDraw < percentHealth) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_END.getSprite(0, 0),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += playerHealthMiddleWidth;
            healthDraw += 1.25;
        }

        if (healthDraw < 92.5 && healthDraw < percentHealth) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_END.getSprite(0, 1),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += playerHealthMiddleWidth;
            healthDraw += 1.25;
        }

        if (healthDraw < 93.75 && healthDraw < percentHealth) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_END.getSprite(0, 2),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += playerHealthMiddleWidth;
            healthDraw += 1.25;
        }

        if (healthDraw < 95 && healthDraw < percentHealth) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_END.getSprite(0, 3),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += playerHealthMiddleWidth;
            healthDraw += 1.25;
        }

        if (healthDraw < 96.25 && healthDraw < percentHealth) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_END.getSprite(0, 4),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += playerHealthMiddleWidth;
            healthDraw += 1.25;
        }

        if (healthDraw < 97.5 && healthDraw < percentHealth) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_END.getSprite(0, 5),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += playerHealthMiddleWidth;
            healthDraw += 1.25;
        }


        if (healthDraw < 98.75 && healthDraw < percentHealth) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_END.getSprite(0, 6),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += playerHealthMiddleWidth;
        }

        if (percentHealth == 100) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_END.getSprite(0, 7),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += playerHealthMiddleWidth;
        }


    }


    private void drawHealthMiddle(Canvas c, double percentHealth) {
        float playerHealthMiddleWidth = (float) (GameVideos.PLAYER_HEALTH_MIDDLE.getWidth());

        double healthDraw = 10;
        while (healthDraw < 81.25 && healthDraw < percentHealth) {
            c.drawBitmap(GameVideos.PLAYER_HEALTH_MIDDLE.getSprite(0, 0),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += playerHealthMiddleWidth;
            healthDraw += 1.25;


            if (healthDraw < 82.5 && healthDraw < percentHealth) {
                c.drawBitmap(GameVideos.PLAYER_HEALTH_MIDDLE.getSprite(0, 1),
                        currentHealthBarX,
                        leftTop.y,
                        null);
                currentHealthBarX += playerHealthMiddleWidth;
                healthDraw += 1.25;
            }

            if (healthDraw < 83.75 && healthDraw < percentHealth) {
                c.drawBitmap(GameVideos.PLAYER_HEALTH_MIDDLE.getSprite(0, 2),
                        currentHealthBarX,
                        leftTop.y,
                        null);
                currentHealthBarX += playerHealthMiddleWidth;
                healthDraw += 1.25;
            }

            if (healthDraw < 85 && healthDraw < percentHealth) {
                c.drawBitmap(GameVideos.PLAYER_HEALTH_MIDDLE.getSprite(0, 3),
                        currentHealthBarX,
                        leftTop.y,
                        null);
                currentHealthBarX += playerHealthMiddleWidth;
                healthDraw += 1.25;
            }

            if (healthDraw < 86.25 && healthDraw < percentHealth) {
                c.drawBitmap(GameVideos.PLAYER_HEALTH_MIDDLE.getSprite(0, 4),
                        currentHealthBarX,
                        leftTop.y,
                        null);
                currentHealthBarX += playerHealthMiddleWidth;
                healthDraw += 1.25;
            }

            if (healthDraw < 87.5 && healthDraw < percentHealth) {
                c.drawBitmap(GameVideos.PLAYER_HEALTH_MIDDLE.getSprite(0, 5),
                        currentHealthBarX,
                        leftTop.y,
                        null);
                currentHealthBarX += playerHealthMiddleWidth;
                healthDraw += 1.25;
            }


            if (healthDraw < 88.75 && healthDraw < percentHealth) {
                c.drawBitmap(GameVideos.PLAYER_HEALTH_MIDDLE.getSprite(0, 6),
                        currentHealthBarX,
                        leftTop.y,
                        null);
                currentHealthBarX += playerHealthMiddleWidth;
                healthDraw += 1.25;
            }


            if (healthDraw < 90 && healthDraw < percentHealth) {
                c.drawBitmap(GameVideos.PLAYER_HEALTH_MIDDLE.getSprite(0, 7),
                        currentHealthBarX,
                        leftTop.y,
                        null);
                currentHealthBarX += playerHealthMiddleWidth;
                currentHealthBarX -= GameVideos.PLAYER_HEALTH_MIDDLE.getScale();
                healthDraw += 1.25;
            }

        }
    }



    private void drawHealthStart(Canvas c, double percentHealth) {
        float healthBarStartWidth = (float) (GameVideos.PLAYER_HEALTH_START.getWidth());


        c.drawBitmap(GameVideos.PLAYER_HEALTH_START.getSprite(0, 0),
                currentHealthBarX,
                leftTop.y,
                null);
        currentHealthBarX += healthBarStartWidth;

        if (percentHealth >= 2) {

            c.drawBitmap(GameVideos.PLAYER_HEALTH_START.getSprite(0, 1),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += healthBarStartWidth;
        }


        if (percentHealth >= 5) {

            c.drawBitmap(GameVideos.PLAYER_HEALTH_START.getSprite(0, 2),
                    currentHealthBarX,
                    leftTop.y,
                    null);
            currentHealthBarX += healthBarStartWidth;
        }

    }


    private void drawHealthBarBackground(Canvas c) {
        c.drawBitmap(GameVideos.PLAYER_HEALTH_BAR.getSprite(0, 0),
                leftTop.x,
                leftTop.y,
                null);
    }














}
