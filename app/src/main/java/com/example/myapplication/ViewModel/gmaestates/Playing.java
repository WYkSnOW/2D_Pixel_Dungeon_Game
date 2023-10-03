package com.example.myapplication.ViewModel.gmaestates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import com.example.myapplication.Model.entities.Character;
import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player;
import com.example.myapplication.Model.entities.enemies.Zombie;
import com.example.myapplication.Model.environments.MapManager;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.View.main.Game;
import com.example.myapplication.Model.ui.PlayingUI;

import java.util.Random;

public class Playing extends BaseState implements GameStateInterFace {
    private float cameraX, cameraY;
    private boolean movePlayer;
    private PointF lastTouchDiff;
    private MapManager mapManager;
    private Player player;
    private Zombie zombie;


    //private ArrayList<RndSquare> squares = new ArrayList<>();
    private Random rand = new Random();

    private PlayingUI playingUI;

    private final Paint hitBoxPaint;

    private RectF attackBox = null; //hitBox for weapon
    private boolean attacking;

    private int characterAttackWidth;
    private int characterAttackHeight;

    private GameAnimation attackAffect;
    private boolean haveEffect = false;
    private Paint paint = new Paint();
    private int effectAdjustLeftWhenRight, effectAdjustLeftWhenLeft, effectAdjustTopWhenRight, effectAdjustTopWhenLeft;





    public Playing(Game game) {
        super(game);

        paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);

        //mob1Pos = new PointF(rand.nextInt(GAME_WIDTH), rand.nextInt(GAME_HEIGHT)); //随机初始位置（在屏幕范围内）

        mapManager = new MapManager();
        player = new Player(GameCharacters.WARRIOR);
        zombie = new Zombie(new PointF(100, 100));
        playingUI = new PlayingUI(this);


        hitBoxPaint = new Paint();
        hitBoxPaint.setStrokeWidth(1);
        hitBoxPaint.setStyle(Paint.Style.STROKE);
        hitBoxPaint.setColor(Color.RED);

        updateAttackHitbox();

        attackAffect = new GameAnimation(1,1,1,1,GameVideos.WITCH_ATTACK_EFFECT);
    }
    @Override
    public void update(double delta) {
        updatePlayerMove(delta);
        player.update(delta);
        updateAttackHitbox();

        mapManager.setCameraValues(cameraX, cameraY);


        attackAffect.update(delta);


        if (attacking) {
                checkAttack();
        }


        if (zombie.isActive()) {
            zombie.update(delta);
        }





    }

    private void checkAttack() {
        RectF attackBoxWithoutCamera = new RectF(attackBox);
        attackBoxWithoutCamera.left -= cameraX;
        attackBoxWithoutCamera.top -= cameraY;
        attackBoxWithoutCamera.right -= cameraX;
        attackBoxWithoutCamera.bottom -= cameraY;

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

    private void updateAttackHitbox() {
        PointF pos = getEffectPos();
        float w = characterAttackWidth;
        float h = characterAttackHeight;
        float bottom = pos.y + GameConstants.Sprite.SIZE; //(SIZE = 6 * 16 = 96)

        if (player.getFaceDir() == GameConstants.FaceDir.RIGHT) {
            attackBox = new RectF(pos.x, bottom - h, pos.x + w, bottom);
        } else {
            attackBox = new RectF(pos.x - w, bottom - h, pos.x, bottom);
        }

    }


    private PointF getEffectPos() {
        PointF hitBox;
        if (player.getFaceDir() == GameConstants.FaceDir.LEFT) {
            hitBox = new PointF(
                    player.getHitBox().left,
                    player.getHitBox().top
            );
        } else if (player.getFaceDir() == GameConstants.FaceDir.RIGHT) {
            hitBox = new PointF(
                    player.getHitBox().right,
                    player.getHitBox().top
            );
        } else {
            throw new IllegalStateException("Unexpected value: " + player.getFaceDir());
        }
        return hitBox;
    }

    @Override
    public void render(Canvas c) {
        mapManager.draw(c);

        drawPlayer(c);

        if (zombie.isActive()) {
            drawCharacter(c, zombie);
        }

        playingUI.drawUI(c);


        c.drawText("PlayerName: " + player.getPlayerName(), 200, 100, paint);
        c.drawText("Difficulty: " + player.getDifficulty(), 200, 150, paint);
        c.drawText("Starting Health: : " + player.getCurrentHealth(), 200, 200, paint);
    }



    private void drawPlayer(Canvas c) {
            c.drawBitmap(
                    player.getGameCharType().getSprite(getDrawDir(), player.getAniIndex()), //在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                    player.getHitBox().left - OffSetX(), //此处减去的为碰撞箱盒实际素材的误差
                    player.getHitBox().top - player.getHitBoxOffSetY(),
                    null
            );
            c.drawRect(player.getHitBox(), hitBoxPaint);



        if(attacking) {
            drawAttackBox(c);
        }
    }

    private int OffSetX() {
        if (player.getFaceDir() == GameConstants.FaceDir.LEFT) {
            return player.getHitBoxOffsetX();
        }
        return 0;
    }


    private int getDrawDir() {
        if (!attacking) {
            return player.getDrawDir();
        }
        return player.getFaceDir() + 4; //return row that have attacking anim

    }

    private void drawAttackBox(Canvas c) {
        if (haveEffect) {
            c.rotate(getEffectRote(), attackBox.left, attackBox.top); //use rotate to change direction of weapon
            c.drawBitmap(
                    attackAffect.getGameVideoType().getSprite(0,attackAffect.getAniIndex()),
                    attackBox.left + attackEffectAdjustLeft(),
                    attackBox.top + attackEffectAdjustTop(),
                    null
            );
            c.rotate(getEffectRote() * -1, attackBox.left, attackBox.top); //rotate back

        }

        c.drawRect(attackBox, hitBoxPaint); //draw weapon's hitbox
    }

    private float attackEffectAdjustTop() { //adjust make base on that default direction of weapon is facing downward
        if (player.getFaceDir() == GameConstants.FaceDir.RIGHT) {
            return effectAdjustTopWhenRight;
        } else {
            return effectAdjustTopWhenLeft;
        }
    }

    private float attackEffectAdjustLeft() {
        if (player.getFaceDir() == GameConstants.FaceDir.RIGHT) {
            return effectAdjustLeftWhenRight;
        } else {
            return effectAdjustLeftWhenLeft;
        }
    }

    private float getEffectRote() {
        if (player.getFaceDir() == GameConstants.FaceDir.LEFT) {
            return 180;
        } else {
            return 0;
        }
    }

    public void drawCharacter(Canvas canvas, Character character) {
        canvas.drawBitmap(
                character.getGameCharType().getSprite(character.getDrawDir(), character.getAniIndex()),
                character.getHitBox().left + cameraX - character.getHitBoxOffsetX(), //
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


    private void updatePlayerMove(double delta) {
        if (!movePlayer) {
            if (player.getDrawDir() <= 1) {
                player.setDrawDir(player.getDrawDir() + 2);
            }

            return;
        }

        float baseSpeed = (float) delta * 300;
        float ratio = Math.abs(lastTouchDiff.y) / Math.abs(lastTouchDiff.x);
        double angle = Math.atan(ratio); //找到玩家滑动的角度

        float xSpeed = (float) Math.cos(angle); //用角度与直线速度计算斜向速度
        float ySpeed = (float) Math.sin(angle);



        if (xSpeed > ySpeed) { //意味着x角度更大，角色应该随着x调整     实现角色移动时的动画切换
            if (lastTouchDiff.x > 0) { //正数意味光标在圆环右侧，即朝右移动
                player.setMoveDir(GameConstants.MoveDir.RIGHT);

            } else {
                player.setMoveDir(GameConstants.MoveDir.LEFT);

            }
        } else {
            if (lastTouchDiff.y > 0) {
                player.setMoveDir(GameConstants.MoveDir.DOWN);
            } else {
                player.setMoveDir(GameConstants.MoveDir.UP);
            }
        }
        if (lastTouchDiff.x >= 0) {
            player.setDrawDir(GameConstants.DrawDir.RIGHT);
            player.setFaceDir(GameConstants.FaceDir.RIGHT);
        }  else {
            player.setDrawDir(GameConstants.DrawDir.LEFT);
            player.setFaceDir(GameConstants.FaceDir.LEFT);

        }


        if (lastTouchDiff.x < 0) {
            xSpeed *= -1;
        }
        if (lastTouchDiff.y < 0) {
            ySpeed *= -1;
        }

        int pWidth = player.getCharacterWidth(); //计算player的实际碰撞体积
        int pHeight = player.getCharacterHeight();
        if (xSpeed <= 0) //当角色往左边或上边移动时，判定点为左上角，则将碰撞修正设置为0
            pWidth = 0;
        if (ySpeed <= 0)
            pHeight = 0;

        float deltaX = xSpeed * baseSpeed * -1; //移动镜头而不是角色
        float deltaY = ySpeed * baseSpeed * -1; //因镜头需与角色相反的方向移动，即乘以-1

        if (mapManager.canMoveHere(player.getHitBox().left + cameraX * -1 + deltaX * -1 + pWidth, player.getHitBox().top + cameraY * -1 + deltaY * -1 + pHeight)) { //查看是否允许通过，是则增加camera值准许移动
            cameraX += deltaX;
            cameraY += deltaY;
        }

    }

    public void setGameStateToMenu() {
        game.setCurrentGameState(Game.GameState.MENU);
    }
    public void setGameStateToEnd() {
        game.setCurrentGameState(Game.GameState.END);
    }

    public void setPlayerMoveTrue(PointF lastTouchDiff) { //查看是否应该移动角色（触发移动后没有松开光标）
        movePlayer = true;
        this.lastTouchDiff = lastTouchDiff;

    }


    public void setPlayerMoveFalse() {
        movePlayer = false; //在操作板class中，松开光标/键盘后将角色移动设置为false，即停止角色移动
        player.resetAnimation();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }

    @Override
    public void touchEvents(MotionEvent event) {
        playingUI.touchEvent(event);
    }


    public void setAttacking(boolean attacking) {
        this.attacking = attacking;

    }

    public void initializeAttackBox(int choice) {
        if (choice == 1) {
            characterAttackWidth = (int) (0.8 * GameConstants.Sprite.SIZE);
            characterAttackHeight = 2 * GameConstants.Sprite.SIZE;
        } else if (choice == 2) {
            characterAttackWidth = GameVideos.WITCH_ATTACK_EFFECT.getWidth();
            characterAttackHeight = 1 * GameConstants.Sprite.SIZE;
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


}
