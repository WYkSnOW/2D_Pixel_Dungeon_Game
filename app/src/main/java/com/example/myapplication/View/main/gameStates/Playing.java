package com.example.myapplication.View.main.gameStates;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.entities.Player.projectile.Projectile;
import com.example.myapplication.Model.entities.Player.projectile.ProjectileHolder;
import com.example.myapplication.Model.entities.enemies.AbstractEnemy;
import com.example.myapplication.Model.environments.Doorways.Doorway;
import com.example.myapplication.Model.environments.MapManager;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.Model.helper.playerMoveStartegy.PlayerDash;
import com.example.myapplication.Model.helper.playerMoveStartegy.PlayerIdle;
import com.example.myapplication.Model.helper.playerMoveStartegy.PlayerMoveStrategy;
import com.example.myapplication.Model.helper.playerMoveStartegy.PlayerRun;
import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.ui.PlayingUI;
import com.example.myapplication.ViewModel.gameStatesVideoModel.PlayingViewModel;

import java.util.Random;

public class Playing extends BaseState implements GameStateInterFace {
    private Random rand = new Random();
    private Paint paint = new Paint();
    private Paint healthPaint = new Paint();
    private MapManager mapManager;
    private float cameraX;
    private float cameraY;
    private boolean playerAbleMoveX;
    private boolean playerAbleMoveY;
    private PointF lastTouchDiff;

    private final PlayingUI playingUI;
    private final Paint hitBoxPaint = new Paint();
    private boolean doorwayJustPassed;
    private PlayingViewModel viewModel;
    private PlayerMoveStrategy playerMoveStrategy;
    private PlayerMoveStrategy playerRun;
    private PlayerMoveStrategy playerIdle;
    private PlayerMoveStrategy playerDash;
    private float xSpeed;
    private float ySpeed;






    public Playing(Game game, Context context) {
        super(game);

        paint.setTextSize(50);
        paint.setColor(Color.WHITE);

        healthPaint.setTextSize(20);
        healthPaint.setTextSize(Color.WHITE);

        hitBoxPaint.setStrokeWidth(1);
        hitBoxPaint.setStyle(Paint.Style.STROKE);
        hitBoxPaint.setColor(Color.RED);

        playerAbleMoveX = false;
        playerAbleMoveY = false;
        playerIdle = new PlayerIdle();
        playerRun = new PlayerRun();
        playerDash = new PlayerDash();
        xSpeed = 0;
        ySpeed = 0;

        mapManager = new MapManager(this);
        initCameraValue();
        //itemManager = new ItemManager();
        //mob1Pos = new PointF(rand.nextInt(GAME_WIDTH), rand.nextInt(GAME_HEIGHT));

        playingUI = new PlayingUI(this);

        //updateAttackHitbox();

        initPlaying();

        viewModel = new ViewModelProvider((ViewModelStoreOwner) context)
                .get(PlayingViewModel.class);

        viewModel.getLastTouchDiff().observe((LifecycleOwner) context, new Observer<PointF>() {
            @Override
            public void onChanged(PointF touchDiff) {
                lastTouchDiff = touchDiff;
            }
        });
        viewModel.getCameraX().observe((LifecycleOwner) context, new Observer<Float>() {
            @Override
            public void onChanged(Float x) {
                cameraX = x;
            }
        });
        viewModel.getCameraY().observe((LifecycleOwner) context, new Observer<Float>() {
            @Override
            public void onChanged(Float y) {
                cameraY = y;
            }
        });
        viewModel.getIsPlayerAbleMoveX().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean ableMove) {
                playerAbleMoveX = ableMove;
            }
        });
        viewModel.getIsPlayerAbleMoveY().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean ableMove) {
                playerAbleMoveY = ableMove;
            }
        });

        viewModel.getCheckingPlayerEnemyCollision()
                .observe((LifecycleOwner) context, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean checking) {
                        viewModel.checkAttackByEnemies(
                                Player.getInstance().getHitBox(), mapManager, cameraX, cameraY);
                    }
                });
    }

    private void initCameraValue() {
        cameraX = (float) (GameConstants.UiSize.GAME_WIDTH / 2
                - mapManager.getMaxWidthCurrentMap() / 2);
        cameraY = (float) (GameConstants.UiSize.GAME_HEIGHT / 2
                - mapManager.getMaxHeightCurrentMap() / 2);
    }

    private void setPlayerMoveStrategy(PlayerMoveStrategy playerMoveStrategy) {
        this.playerMoveStrategy = playerMoveStrategy;
    }

    public void initPlaying() {
        //zombie = new Zombie(new PointF(100, 100));
        //zombie.setActive(true);

        initCameraValue();
        lastTouchDiff = new PointF(0, 0);
        Player.getInstance().backToIdleState();
    }

    @Override
    public void update(double delta) {
        if (game.getCurrentGameState() != Game.GameState.PLAYING) {
            return;
        }

        if (playerMoveStrategy != null) {
            playerMoveStrategy.setPlayerAnim(xSpeed, ySpeed, lastTouchDiff);
        }

        updatePlayerMoveInfo(delta);
        updatePlayerPosition(delta);

        Player.getInstance().update(delta);

        //updateAttackHitbox();
        mapManager.setCameraValues(cameraX, cameraY);
        checkForDoorway();
        //itemManager.setCameraValues(cameraX, cameraY);


        viewModel.checkAttack(
                Player.getInstance().isAttacking(),
                Player.getInstance().getAttackBox(),
                mapManager, cameraX, cameraY);

        viewModel.checkingPlayerEnemyCollision();
        viewModel.updateZombies(mapManager, delta, cameraX, cameraY);


        if (Player.getInstance().getCurrentHealth() <= 0) {
            setGameStateToEnd();
        }

        ProjectileHolder.getInstance().update(delta, mapManager.getCurrentMap(), cameraX, cameraY);

    }

    @Override
    public void touchEvents(MotionEvent event) {
        if (game.getCurrentGameState() != Game.GameState.PLAYING) {
            return;
        }
        viewModel.playingUiTouchEvent(event, playingUI);
    }
    @Override
    public void render(Canvas c) {
        if (game.getCurrentGameState() != Game.GameState.PLAYING) {
            return;
        }
        mapManager.draw(c);
        //itemManager.draw(c);
        Player.getInstance().drawPlayer(c);
        for (AbstractEnemy enemy : mapManager.getCurrentMap().getMobArrayList()) {
            if (enemy.isActive()) {
                drawEnemy(c, enemy);

            }
        }
        ProjectileHolder.getInstance().draw(c);

        viewModel.playingUiDrawUi(c, playingUI);
        drawUi((c));


    }


    public void setCameraValues(PointF cameraPos) {
        this.cameraX = cameraPos.x;
        this.cameraY = cameraPos.y;
    }
    private void checkForDoorway() {
        Doorway doorwayPlayerIsOn = mapManager.isPlayerOnDoorway(Player.getInstance().getHitBox());
        if (doorwayPlayerIsOn != null) {
            if (!doorwayJustPassed) {
                if (doorwayPlayerIsOn.isEndGameDoorway()) {
                    Player.getInstance().setWinTheGame(true);
                    setGameStateToEnd();
                } else {
                    mapManager.changeMap(doorwayPlayerIsOn.getDoorwayConnectedTo());
                }

            }
        } else {
            doorwayJustPassed = false;
        }
    }
    public void setDoorwayJustPassed(boolean doorwayJustPassed) {
        this.doorwayJustPassed = doorwayJustPassed;
    }


    private void drawUi(Canvas c) {
        c.drawText("PlayerName: " + Player.getInstance().getPlayerName(), 200, 100, paint);
        c.drawText("Difficulty: " + Player.getInstance().getDifficulty(), 200, 150, paint);
        c.drawText("Health: " + Player.getInstance().getCurrentHealth(), 200, 200, paint);
        c.drawText("Game Score:" + Player.getInstance().getCurrentScore(), 200, 250, paint);
    }
    public void drawEnemy(Canvas canvas, AbstractEnemy enemy) {
        int offsetX = enemy.getHitBoxOffsetX();
        if (enemy.getDrawDir() == GameConstants.DrawDir.RIGHT) {
            offsetX = 0;
        }
        canvas.drawBitmap(
                enemy.getGameCharType().getSprite(
                        enemy.getDrawDir(),
                        enemy.getAniIndex()
                ),
                enemy.getHitBox().left + cameraX - offsetX,
                enemy.getHitBox().top + cameraY - enemy.getHitBoxOffsetY(),
                null
        );
        canvas.drawRect(
                enemy.getHitBox().left + cameraX,
                enemy.getHitBox().top + cameraY,
                enemy.getHitBox().right + cameraX,
                enemy.getHitBox().bottom + cameraY,
                hitBoxPaint); //draw mob's hitBox

        canvas.drawText(
                "" + enemy.getCurrentHealth(),
                enemy.getHitBox().left + cameraX,
                enemy.getHitBox().top - 20 + cameraY,
                paint
        );

    }



    public void drawProjectile(Canvas c, Projectile p) {
        c.drawRect(p.getHitBox().left + cameraX,
                p.getHitBox().top + cameraY,
                p.getHitBox().right + cameraX,
                p.getHitBox().bottom + cameraY,
                hitBoxPaint);
    }



    private void updatePlayerMoveInfo(double delta) {
        if (Player.getInstance().getCurrentStates() == PlayerStates.IDLE) {
            setPlayerMoveStrategy(playerIdle);
            return;
        }
        setPlayerMoveStrategy(playerRun);


        float ratio = Math.abs(lastTouchDiff.y) / Math.abs(lastTouchDiff.x);
        double angle = Math.atan(ratio); //找到玩家滑动的角度

        xSpeed = (float) Math.cos(angle); //用角度与直线速度计算斜向速度
        ySpeed = (float) Math.sin(angle);


        if (lastTouchDiff.x < 0) {
            xSpeed *= -1;
        }
        if (lastTouchDiff.y < 0) {
            ySpeed *= -1;
        }



        PointF d = Player.getInstance().getMoveDelta(delta, xSpeed, ySpeed);

        viewModel.setIsPlayerAbleMoveX(
                viewModel.checkPlayerAbleMoveX(
                        Player.getInstance().isAttacking(),
                        mapManager,
                        d,
                        new PointF(cameraX, cameraY)
                ));
        viewModel.setIsPlayerAbleMoveY(
                viewModel.checkPlayerAbleMoveY(
                        Player.getInstance().isAttacking(),
                        mapManager,
                        d,
                        new PointF(cameraX, cameraY)
                ));



    }

    private void updatePlayerPosition(double delta) {

        float baseSpeed = (float) (delta * Player.getInstance().getCurrentSpeed());


        if (playerAbleMoveX) {
            cameraX += Player.getInstance().getPlayerMovement(xSpeed, ySpeed, baseSpeed).x;
        }
        if (playerAbleMoveY) {
            cameraY += Player.getInstance().getPlayerMovement(xSpeed, ySpeed, baseSpeed).y;
        }

    }

    public void setGameStateToMenu() {
        game.setCurrentGameState(Game.GameState.MENU);
    }
    public void setGameStateToEnd() {
        //Leaderboard.getInstance().addPlayerRecord(player.sumbitScore());
        Leaderboard.getInstance().addPlayerRecord(
                Player.getInstance().sumbitScore(),
                Player.getInstance().isWinTheGame()
        );
        game.setCurrentGameState(Game.GameState.END);

        mapManager.resetMap();
    }

    public void setPlayerMoveTrue(PointF lastTouchDiff) { //查看是否应该移动角色（触发移动后没有松开光标）
        viewModel.setLastTouchDiff(lastTouchDiff);
    }
    public void setPlayerMoveFalse() {
        if (!(Player.getInstance().isAttacking()
                || Player.getInstance().isOnSkill()
                || Player.getInstance().isProjecting())) {
            Player.getInstance().backToIdleState();
        }

    }



    public MapManager getMapManager() {
        return mapManager;
    }

}