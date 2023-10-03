package com.example.myapplication.ViewModel.gmaestates;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.loopVideo.VideoFrame;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.View.main.Game;
import com.example.myapplication.View.main.MainActivity;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;


public class Config extends BaseState implements GameStateInterFace {
    private Paint paint = new Paint();
    private CustomButton btnConfig;
    private CustomButton nameBar;

    private int configStartX = GameConstants.UiLocation.CONFIG_START_BTN_POS_X;
    private int configStartY = GameConstants.UiLocation.CONFIG_START_BTN_POS_Y;
    private  int nameBarStartX = GameConstants.UiLocation.NAME_BAR_POS_X;
    private int nameBarStartY = GameConstants.UiLocation.NAME_BAR_POS_Y;

    private int textStartX = GameConstants.UiLocation.NAME_TEXT_POS_X;
    private int textStartY = GameConstants.UiLocation.NAME_TEXT_POS_Y;

    private int textHintY = textStartY + 100;

    private GameAnimation configBackground;

    private int characterChoice = 0;
    private int difficultyChoice = 0;


    private int elfLocX;
    private int elfLocY;
    private int elfState = 2;
    private GameAnimation elf;

    private int knightState = 2;
    private GameAnimation knight;
    private int wizardState = 2;
    private GameAnimation wizard;
    private GameAnimation difficultyBox;

    private String currentNameText = "Enter your name here";
    private String userInput;

    private AlertDialog dialog;
    private int counter = 0;
    private boolean showHint;
    private String hintText;
    private boolean validName = false;



    public Config(Game game) {
        super(game);
        paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);

        btnConfig = new CustomButton(
                configStartX,
                configStartY,
                ButtonImage.MENU_START.getWidth(),
                ButtonImage.MENU_START.getHeight()
        );
        nameBar = new CustomButton(
                nameBarStartX,
                nameBarStartY,
                ButtonImage.UI_HOLDER1.getWidth(),
                ButtonImage.UI_HOLDER1.getHeight()
        );

        difficultyBox = new GameAnimation(
                configStartX,
                MainActivity.gameHeight / 3,
                GameVideos.DIFFICULTY_BOX.getWidth(),
                GameVideos.DIFFICULTY_BOX.getHeight(),
                GameVideos.DIFFICULTY_BOX
        );

        configBackground = new GameAnimation(0,0, MainActivity.gameWidth, MainActivity.gameHeight, GameVideos.CONFIG_BACK_VIDEO);

        elfLocX = 100;
        elfLocY = MainActivity.gameHeight - 300;

        setUpCharacterAnimation();
        nameDialog();
    }



    @Override
    public void update(double delta) {
        configBackground.update(delta);


        elf.update(delta);
        knight.update(delta);
        wizard.update(delta);

    }

    @Override
    public void render(Canvas c) {
        c.drawBitmap(//在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                configBackground.getGameVideoType().getSprite(0, configBackground.getAniIndex()),
                configBackground.getHitBox().left,
                configBackground.getHitBox().top,
                null
        );

        drawBtn(c);
        drawCharacter(c);

        //textStartX = textStartX - currentNameText.length();
        c.drawText(
                currentNameText,
                textStartX - (currentNameText.length() * 12),
                textStartY, paint
        );


        c.drawBitmap(
                difficultyBox.getGameVideoType().getSprite(0, difficultyChoice),
                difficultyBox.getHitBox().left,
                difficultyBox.getHitBox().top,
                null
        );

        if (showHint) {
            setHintText(c);
        }
    }

    public void drawBtn(Canvas c) {
        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnConfig.isPushed()),
                btnConfig.getHitbox().left,
                btnConfig.getHitbox().top,
                null
        );
        c.drawBitmap(
                ButtonImage.UI_HOLDER1.getBtnImg(nameBar.isPushed()),
                nameBar.getHitbox().left,
                nameBar.getHitbox().top,
                null
        );

    }

    public void drawCharacter(Canvas c) {
        c.drawBitmap(
                elf.getGameVideoType().getSprite(elfState, elf.getAniIndex()),
                elf.getHitBox().left,
                elf.getHitBox().top,
                null
        );
        c.drawBitmap(
                knight.getGameVideoType().getSprite(knightState, knight.getAniIndex()),
                knight.getHitBox().left,
                knight.getHitBox().top,
                null
        );
        c.drawBitmap(
                wizard.getGameVideoType().getSprite(wizardState, wizard.getAniIndex()),
                wizard.getHitBox().left,
                wizard.getHitBox().top,
                null
        );
    }

    @Override
    public void touchEvents(MotionEvent event) {
        btnConfigAction(event);

        pickElf(event);
        pickKnight(event);
        pickWizard(event);

        changeNameAction(event);
        pickDifficultyAction(event);

    }

    private boolean isInBtn(MotionEvent e, CustomButton b) {
        return b.getHitbox().contains(e.getX(), e.getY());
    }

    private boolean isInCharacter(MotionEvent e, VideoFrame b) {
        return b.getHitbox().contains(e.getX(), e.getY());
    }



    private void btnConfigAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isInBtn(event, btnConfig)) { //when pressed button
                btnConfig.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isInBtn(event, btnConfig)) //check if release inside area of button
                if (btnConfig.isPushed()) { //check if click start in area of button

                    if (characterChoice == 1) {
                        game.getPlaying().setPlayer(new Player(GameCharacters.TERESA));
                    } else if (characterChoice == 2) {
                        //game.getPlaying().setPlayer(new Player(GameCharacters.KNIGHT));
                        game.getPlaying().setPlayer(new Player(GameCharacters.WITCH));
                    } else if (characterChoice == 3) {
                        game.getPlaying().setPlayer(new Player(GameCharacters.WARRIOR));
                    }

                    if (ableStart()) {
                        game.getPlaying().getPlayer().setPlayerName(currentNameText);
                        game.getPlaying().getPlayer().setDifficulty(difficultyChoice);
                        game.getPlaying().getPlayer().setCurrentHealth(100 / difficultyChoice);
                        game.setCurrentGameState(Game.GameState.PLAYING);
                    }

                }
            game.getPlaying().initializeAttackBox(characterChoice);
            btnConfig.setPushed(false);
        }
    }


    private void changeNameAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isInBtn(event, nameBar)) //when pressed button
                nameBar.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isInBtn(event, nameBar)) { //check if release inside area of button
                if (nameBar.isPushed()) { //check if click start in area of button
                    dialog.show();
                }
            }
            nameBar.setPushed(false);
        }
    }


    private void pickElf(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isInCharacter(event, elf)) //when pressed button
               elf.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isInCharacter(event, elf)) //check if release inside area of button
                if (elf.isPushed()) { //check if click start in area of button
                    characterChoice = 1;

                    elfState = 0;
                    knightState = 2;
                    wizardState = 2;
                }
            elf.setPushed(false);
        }
    }
    private void pickKnight(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isInCharacter(event, knight)) //when pressed button
                knight.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isInCharacter(event, knight)) //check if release inside area of button
                if (knight.isPushed()) { //check if click start in area of button
                    characterChoice = 2;

                    knightState = 0;
                    elfState =2;
                    wizardState = 2;
                }
            elf.setPushed(false);
        }
    }
    private void pickWizard(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isInCharacter(event, wizard)) //when pressed button
                wizard.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isInCharacter(event, wizard)) //check if release inside area of button
                if (wizard.isPushed()) { //check if click start in area of button
                    characterChoice = 3;

                    wizardState = 0;
                    elfState = 2;
                    knightState = 2;
                }
            wizard.setPushed(false);
        }
    }

    private void pickDifficultyAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isInCharacter(event, difficultyBox)) //when pressed button
                difficultyBox.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isInCharacter(event, difficultyBox)) //check if release inside area of button
                if (difficultyBox.isPushed()) { //check if click start in area of button
                    if (difficultyChoice < 3) {
                        difficultyChoice++;
                    } else if (difficultyChoice == 3) {
                        difficultyChoice = 1;
                    }
                }
            difficultyBox.setPushed(false);
        }
    }

    private void setHintText(Canvas c) {
        if (counter <= 0) {
            showHint = false;
        }
        c.drawText(hintText, textStartX - (hintText.length() * 12), textHintY, paint);
        counter--;

    }

    private void setTimeCounter(int time) {
        showHint = true;
        counter = time * 60;
    }

    private void nameDialog() {
        // 创建 AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getGameContext());
        builder.setTitle("Player's Name:");
        // 创建 EditText 作为对话框的内容
        final EditText editText = new EditText(MainActivity.getGameContext());
        builder.setView(editText);

        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 在这里处理用户输入的文字
                userInput = editText.getText().toString();
                if (isNameValid(userInput)) {
                    if (userInput.length() <= 20) {
                        currentNameText = userInput;
                        validName = true;
                    } else {
                        setTimeCounter(10); //show hint for 10 second
                        hintText = "Maximum 20 Characters";
                    }

                } else {
                    setTimeCounter(10); //show hint for 10 second
                    hintText = "Please Enter a Valid Name";
                }
            }
        });
        // 设置取消按钮
        builder.setNegativeButton("Cancel", null);
        // 创建并显示对话框
        dialog = builder.create();
    }

    //check if player put in a in valid name
    private boolean isNameValid(String name) {
        if (userInput.equals("")) {
            return false;
        }

        boolean r = false;
        for (int i = 0; i < name.length(); i++) {
            if (!(name.charAt(i) == ' ')) {
                r = true;
            }
        }
        if (name.charAt(0) == ' ') {
            r = false;
        }
        return r;
    }

    private boolean ableStart() {
        if (characterChoice > 0 && difficultyChoice > 0 && validName) {
            return true;
        }
        if (characterChoice <= 0) {
            hintText = "Please Pick a Character";
            setTimeCounter(10);
            showHint = true;
        } else if (difficultyChoice <= 0) {
            hintText = "Please Pick a Difficulty";
            setTimeCounter(10);
            showHint = true;
        } else if (!validName) {
            hintText = "Please Enter a Name";
            setTimeCounter(10);
            showHint = true;
        }
        return false;
    }



    private void setUpCharacterAnimation() {
        elf = new GameAnimation(
                elfLocX,
                elfLocY + 10,
                GameVideos.TERESA.getWidth(),
                GameVideos.TERESA.getWidth(),
                GameVideos.TERESA
        );

        knight = new GameAnimation(
                elfLocX + 300,
                elfLocY + 20,
                GameVideos.WITCH.getWidth(),
                GameVideos.WITCH.getWidth(),
                GameVideos.WITCH
        );

        wizard = new GameAnimation(
                elfLocX +  600,
                elfLocY,
                GameVideos.WARRIOR.getWidth(),
                GameVideos.WARRIOR.getHeight(),
                GameVideos.WARRIOR
        );
    }





}
