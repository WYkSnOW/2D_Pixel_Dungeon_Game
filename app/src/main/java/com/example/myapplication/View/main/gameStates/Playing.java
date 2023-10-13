package com.example.myapplication.View.main.gameStates;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.example.myapplication.Model.entities.Character;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.enemies.Zombie;
import com.example.myapplication.Model.environments.Doorways.Doorway;
import com.example.myapplication.Model.environments.MapManager;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.ui.PlayingUI;
import com.example.myapplication.ViewModel.gameStatesVideoModel.PlayingViewModel;

import java.util.Random;

public class Playing extends BaseState implements GameStateInterFace {
    private Random rand = new Random();
    private Paint paint = new Paint();
    private MapManager mapManager;
    //private ItemManager itemManager;
    private float cameraX;
    private float cameraY;
    private boolean movePlayer;
    private PointF lastTouchDiff;

    //private Zombie zombie;
    //private ArrayList<RndSquare> squares = new ArrayList<>();
    private final PlayingUI playingUI;
    private final Paint hitBoxPaint;
    private RectF attackBox = null; //hitBox for weapon
    private boolean attacking;
    private int characterAttackWidth;
    private int characterAttackHeight;
    private GameAnimation attackAffect;
    private boolean haveEffect = false;
    private int effectAdjustLeftWhenRight;
    private int effectAdjustLeftWhenLeft;
    private int effectAdjustTopWhenRight;
    private int effectAdjustTopWhenLeft;
    private boolean doorwayJustPassed;
    private PlayingViewModel viewModel;





    public Playing(Game game, Context context) {
        super(game);
        paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        hitBoxPaint = new Paint();
        hitBoxPaint.setStrokeWidth(1);
        hitBoxPaint.setStyle(Paint.Style.STROKE);
        hitBoxPaint.setColor(Color.RED);

        mapManager = new MapManager(this);
        initCameraValue();
        //itemManager = new ItemManager();
        //mob1Pos = new PointF(rand.nextInt(GAME_WIDTH), rand.nextInt(GAME_HEIGHT));


        playingUI = new PlayingUI(this);

        updateAttackHitbox();
        attackAffect = new GameAnimation(1, 1, 1, 1, GameVideos.WITCH_ATTACK_EFFECT);

        initPlaying();

        viewModel = new ViewModelProvider((ViewModelStoreOwner) context)
                .get(PlayingViewModel.class);

        viewModel.getIsAttacking().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isAttacking) {
                attacking = isAttacking;
            }
        });
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
    }

    private void initCameraValue() {
        cameraX = GameConstants.UiSize.GAME_WIDTH / 2 - mapManager.getMaxWidthCurrentMap() / 2;
        cameraY = GameConstants.UiSize.GAME_HEIGHT / 2 - mapManager.getMaxHeightCurrentMap() / 2;
    }

    public void initPlaying() {
        //zombie = new Zombie(new PointF(100, 100));
        //zombie.setActive(true);

        initCameraValue();

    }

    @Override
    public void update(double delta) {
        updatePlayerMove(delta);
        Player.getInstance().update(delta);
        updateAttackHitbox();
        mapManager.setCameraValues(cameraX, cameraY);
        checkForDoorway();
        //itemManager.setCameraValues(cameraX, cameraY);
        attackAffect.update(delta);

        viewModel.checkAttack(attacking, attackBox, mapManager, cameraX, cameraY);

        viewModel.updateZombies(mapManager, delta, cameraX, cameraY);

    }

    @Override
    public void touchEvents(MotionEvent event) {
        viewModel.playingUiTouchEvent(event, playingUI);
    }
    @Override
    public void render(Canvas c) {
        mapManager.draw(c);
        //itemManager.draw(c);
        drawPlayer(c);

        for (Zombie zombie : mapManager.getCurrentMap().getZombieArrayList()) {
            if (zombie.isActive()) {
                drawCharacter(c, zombie);
            }
        }

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
                    setGameStateToEnd();
                } else {
                    mapManager.changeMap(doorwayPlayerIsOn.getDoorwayConnectedTo(), game);
                }

            }
        } else {
            doorwayJustPassed = false;
        }
    }
    public void setDoorwayJustPassed(boolean doorwayJustPassed) {
        this.doorwayJustPassed = doorwayJustPassed;
    }

    private void drawPlayer(Canvas c) {
        c.drawBitmap(//在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                viewModel.getPlayerSprite(attacking),
                viewModel.getPlayerLeft(),
                viewModel.getPlayerTop(),
                null
        );
        c.drawRect(viewModel.getPlayerHitbox(), hitBoxPaint);
        if (attacking) {
            drawAttackBox(c);
        }
    }

    private void drawUi(Canvas c) {
        c.drawText("PlayerName: " + Player.getInstance().getPlayerName(), 200, 100, paint);
        c.drawText("Difficulty: " + Player.getInstance().getDifficulty(), 200, 150, paint);
        c.drawText("Starting Health: " + Player.getInstance().getCurrentHealth(), 200, 200, paint);
        c.drawText("Game Score:" + Player.getInstance().getCurrentScore(), 200, 250, paint);
    }
    public void drawCharacter(Canvas canvas, Character character) {
        canvas.drawBitmap(
                character.getGameCharType().getSprite(
                        character.getDrawDir(),
                        character.getAniIndex()
                ),
                character.getHitBox().left + cameraX - character.getHitBoxOffsetX(),
                character.getHitBox().top + cameraY - character.getHitBoxOffSetY(),
                null
        );
        canvas.drawRect(
                character.getHitBox().left + cameraX,
                character.getHitBox().top + cameraY,
                character.getHitBox().right + cameraX,
                character.getHitBox().bottom + cameraY,
                hitBoxPaint); //draw mob's hitBox
    }
    private void drawAttackBox(Canvas c) {
        if (haveEffect) { //use rotate to change direction of attackEffect
            c.rotate(viewModel.getEffectRote(), attackBox.left, attackBox.top);
            c.drawBitmap(
                    attackAffect.getGameVideoType().getSprite(0, attackAffect.getAniIndex()),
                    attackBox.left + attackEffectAdjustLeft(),
                    attackBox.top + attackEffectAdjustTop(),
                    null
            );
            c.rotate(viewModel.getEffectRote() * -1, attackBox.left, attackBox.top); //rotate back
        }
        c.drawRect(attackBox, hitBoxPaint); //draw weapon's hitbox
    }

    private void checkAttack() {
        RectF attackBoxWithoutCamera = new RectF(attackBox);
        attackBoxWithoutCamera.left -= cameraX;
        attackBoxWithoutCamera.top -= cameraY;
        attackBoxWithoutCamera.right -= cameraX;
        attackBoxWithoutCamera.bottom -= cameraY;

        for (Zombie zombie : mapManager.getCurrentMap().getZombieArrayList()) {
            if (attackBoxWithoutCamera.intersects(
                    zombie.getHitBox().left,
                    zombie.getHitBox().top,
                    zombie.getHitBox().right,
                    zombie.getHitBox().bottom)
            ) {
                zombie.setActive(false); //remove zombie(or any mob)
                //zombie.getGameCharType().setSprites(GameCharacters.KNIGHT.getSprites());
            }
        }
    }

    private void updateAttackHitbox() {
        PointF pos = getEffectPos();
        float w = characterAttackWidth;
        float h = characterAttackHeight;
        float bottom = pos.y + GameConstants.Sprite.SIZE; //(SIZE = 6 * 16 = 96)
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            attackBox = new RectF(pos.x, bottom - h, pos.x + w, bottom);
        } else {
            attackBox = new RectF(pos.x - w, bottom - h, pos.x, bottom);
        }
    }

    private PointF getEffectPos() {
        PointF hitBox;
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
            hitBox = new PointF(
                    Player.getInstance().getHitBox().left,
                    Player.getInstance().getHitBox().top
            );
        } else if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            hitBox = new PointF(
                    Player.getInstance().getHitBox().right,
                    Player.getInstance().getHitBox().top
            );
        } else {
            throw new IllegalStateException(
                    "Unexpected value: " + Player.getInstance().getFaceDir()
            );
        }
        return hitBox;
    }




    //adjust make base on that default direction of weapon is facing downward
    private float attackEffectAdjustTop() {
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            return effectAdjustTopWhenRight;
        } else {
            return effectAdjustTopWhenLeft;
        }
    }
    private float attackEffectAdjustLeft() {
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            return effectAdjustLeftWhenRight;
        } else {
            return effectAdjustLeftWhenLeft;
        }
    }

    private void updatePlayerMove(double delta) {
        if (!movePlayer) {
            if (Player.getInstance().getDrawDir() <= 1) {
                Player.getInstance().setDrawDir(Player.getInstance().getDrawDir() + 2);
            }
            return;
        }
        float baseSpeed = (float) delta * 300;
        float ratio = Math.abs(lastTouchDiff.y) / Math.abs(lastTouchDiff.x);
        double angle = Math.atan(ratio); //找到玩家滑动的角度

        float xSpeed = (float) Math.cos(angle); //用角度与直线速度计算斜向速度
        float ySpeed = (float) Math.sin(angle);

        viewModel.setPlayerAnimDir(xSpeed, ySpeed, lastTouchDiff);

        if (lastTouchDiff.x < 0) {
            xSpeed *= -1;
        }
        if (lastTouchDiff.y < 0) {
            ySpeed *= -1;
        }
        int pWidth = (int) Player.getInstance().getHitBox().width(); //计算player的实际碰撞体积
        int pHeight = (int) Player.getInstance().getHitBox().height();

        if (xSpeed <= 0) { //当角色往左边或上边移动时，判定点为左上角，则将碰撞修正设置为0
            pWidth = 0;
        }
        //if (ySpeed <= 0) {
        //    pHeight = 0;
        //}
        float deltaX = xSpeed * baseSpeed * -1; //移动镜头而不是角色
        float deltaY = ySpeed * baseSpeed * -1; //因镜头需与角色相反的方向移动，即乘以-1

        if (viewModel.checkPlayerAbleMove(
                attacking,
                mapManager,
                pWidth,
                pHeight,
                new PointF(deltaX, deltaY),
                new PointF(cameraX, cameraY)
        )) {
            cameraX += deltaX;
            cameraY += deltaY;
        }
    }

    public void setGameStateToMenu() {
        game.setCurrentGameState(Game.GameState.MENU);
    }
    public void setGameStateToEnd() {
        //Leaderboard.getInstance().addPlayerRecord(player.sumbitScore());
        Leaderboard.getInstance().addPlayerRecord(Player.getInstance().sumbitScore());
        game.setCurrentGameState(Game.GameState.END);
    }

    public void setPlayerMoveTrue(PointF lastTouchDiff) { //查看是否应该移动角色（触发移动后没有松开光标）
        movePlayer = true;
        viewModel.setLastTouchDiff(lastTouchDiff);
    }
    public void setPlayerMoveFalse() {
        movePlayer = false; //在操作板class中，松开光标/键盘后将角色移动设置为false，即停止角色移动
        Player.getInstance().resetAnimation();
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
        viewModel.setAttacking(attacking);
    }

    public void initializeAttackBox(int choice) {
        if (choice == 1) {
            characterAttackWidth = (int) (0.8 * GameConstants.Sprite.SIZE);
            characterAttackHeight = 2 * GameConstants.Sprite.SIZE;
        } else if (choice == 2) {
            characterAttackWidth = GameVideos.WITCH_ATTACK_EFFECT.getWidth();
            characterAttackHeight = GameConstants.Sprite.SIZE;
        } else {
            characterAttackWidth = (int) (1.2 * GameConstants.Sprite.SIZE);
            characterAttackHeight = 2 * GameConstants.Sprite.SIZE;
        }
        initializeEffectAdjust(choice);
    }

    public void initializeEffectAdjust(int choice) {
        if (choice == 2) {
            effectAdjustTopWhenRight = -30;
            effectAdjustTopWhenLeft = -115;
            effectAdjustLeftWhenRight = 20;
            effectAdjustLeftWhenLeft = -240;
            haveEffect = true;
        } else {
            haveEffect = false;
        }
    }


    public MapManager getMapManager() {
        return mapManager;
    }

}
