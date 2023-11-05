package com.example.myapplication.View.main.gameStates;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharOne;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharThree;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharTwo;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.gameStatesLogic.ConfigLogic;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.HelpMethods;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;
import com.example.myapplication.Model.ui.GameImages;
import com.example.myapplication.ViewModel.MainViewModel;
import com.example.myapplication.ViewModel.gameStatesVideoModel.ConfigViewModel;

public class Config extends BaseState implements GameStateInterFace {
    private Paint paint = new Paint();
    private final CustomButton btnConfig;
    private final CustomButton nameBar;
    private final CustomButton configBtnLeft;
    private final CustomButton configBtnRight;
    private final CustomButton configBtnSelect;
    private final GameAnimation configBackground;

    private int difficultyChoice;
    private final GameAnimation difficultyBox;
    private final GameAnimation charaterData;
    private GameAnimation teresa;
    private GameAnimation witch;
    private GameAnimation warrior;

    private String currentNameText;
    private String userInput;
    private AlertDialog dialog;
    private int counter;
    private boolean showHint;
    private String hintText;
    private boolean validName;
    private final ConfigViewModel viewModel;
    private boolean resetSelect;
    private boolean selectCharacter;
    private final PointF configBoardPos = new PointF(
            (int) ((GameConstants.UiSize.GAME_WIDTH / 2.0)
                    - (GameImages.CONFIG_BOARD.getWidth() / 2.0)),
            (int) ((GameConstants.UiSize.GAME_HEIGHT / 2.0)
                    - (GameImages.CONFIG_BOARD.getHeight() / 2.0)));

    private final PointF nameBarPos = new PointF(
            configBoardPos.x + (int) (GameImages.CONFIG_BOARD.getWidth() / 2.1),
            configBoardPos.y + 15);

    public Config(Game game, Context context) {
        super(game);

        paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);

        configBtnLeft = new CustomButton(
                configBoardPos.x + 37 * 3,
                configBoardPos.y + 219 * 3,
                ButtonImage.CONFIG_BTN_LEFT.getWidth(),
                ButtonImage.CONFIG_BTN_LEFT.getHeight()
        );

        btnConfig = new CustomButton(
                GameConstants.UiLocation.CONFIG_START_BTN_POS_X,
                GameConstants.UiLocation.CONFIG_START_BTN_POS_Y,
                ButtonImage.MENU_START.getWidth(),
                ButtonImage.MENU_START.getHeight()
        );

        configBtnRight = new CustomButton(
                configBoardPos.x + 130 * 3,
                configBoardPos.y + 219 * 3,
                ButtonImage.CONFIG_BTN_RIGHT.getWidth(),
                ButtonImage.CONFIG_BTN_RIGHT.getHeight()
        );

        configBtnSelect = new CustomButton(
                configBoardPos.x + 64 * 3,
                configBoardPos.y + 218 * 3,
                ButtonImage.CONFIG_BTN_SELECT.getWidth(),
                ButtonImage.CONFIG_BTN_SELECT.getHeight()
        );

        nameBar = new CustomButton(
                nameBarPos.x,
                nameBarPos.y,
                ButtonImage.UI_HOLDER1.getWidth(),
                ButtonImage.UI_HOLDER1.getHeight()
        );

        difficultyBox = new GameAnimation(
                GameConstants.UiLocation.DIFFICULTY_BOX_POS_X,
                GameConstants.UiLocation.DIFFICULTY_BOX_POS_Y,
                GameVideos.DIFFICULTY_BOX.getWidth(),
                GameVideos.DIFFICULTY_BOX.getHeight(),
                GameVideos.DIFFICULTY_BOX
        );

        charaterData = new GameAnimation(
                configBoardPos.x + 198 * 3,
                configBoardPos.y + 64 * 3,
                GameVideos.START_DATA.getWidth(),
                GameVideos.START_DATA.getHeight(),
                GameVideos.START_DATA
        );

        configBackground = new GameAnimation(
                0,
                0,
                GameConstants.UiSize.GAME_WIDTH,
                GameConstants.UiSize.GAME_HEIGHT,
                GameVideos.CONFIG_BACK_VIDEO
        );


        ConfigLogic configLogic = new ConfigLogic();
        setUpCharacterAnimation();
        nameDialog();
        initConfig();

        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ConfigViewModel.class);

        viewModel.getPlayerName().observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String playerName) {
                currentNameText = playerName;
            }
        });

        viewModel.getDifficulty().observe((LifecycleOwner) context, new Observer<Integer>() {
            @Override
            public void onChanged(Integer diff) {
                difficultyChoice = diff;
            }
        });


        viewModel.getConfigButtonClicked().observe((LifecycleOwner) context,
                new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean clicked) {
                    if (clicked) {
                        viewModel.btnConfigRespond(
                                game, currentNameText, difficultyChoice
                        );
                    }
                }
            });
        viewModel.getPickDifficulty().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean clicked) {
                if (clicked) {
                    viewModel.loopDifficulty(difficultyChoice);
                }
            }
        });
    }

    public void initConfig() {
        currentNameText = "Enter your name here";
        userInput = null;
        showHint = false;
        validName = false;
        counter = 0;
        difficultyChoice = 0;
        resetSelect = false;
        selectCharacter = false;
        Player.getInstance().setCharStrategy(new CharThree());
        Player.getInstance().setCurrentStates(PlayerStates.WALK);
        configBtnSelect.setPushed(false);
    }

    @Override
    public void update(double delta) {
        if (game.getCurrentGameState() != Game.GameState.CONFIG) {
            return;
        }
        configBackground.update();
        teresa.update();
        witch.update();
        warrior.update();
    }

    @Override
    public void touchEvents(MotionEvent event) {
        if (game.getCurrentGameState() != Game.GameState.CONFIG) {
            return;
        }
        btnConfigAction(event);
        configBtnSelectAction(event);
        configBtnLeftAction(event);
        configBtnRightAction(event);
        changeNameAction(event);
        pickDifficultyAction(event);
    }

    @Override
    public void render(Canvas c) {
        if (game.getCurrentGameState() != Game.GameState.CONFIG) {
            return;
        }
        drawBackground(c);
        drawUi(c);
        drawBtn(c);
        drawCharacter(c);
        if (showHint) {
            setHintText(c);
        }
    }



    public void drawBackground(Canvas c) {
        c.drawBitmap(
                configBackground.getGameVideoType().getSprite(0, configBackground.getAniIndex()),
                configBackground.getHitBox().left,
                configBackground.getHitBox().top,
                null
        );
    }

    public void drawUi(Canvas c) {
        c.drawBitmap(
                GameImages.CONFIG_BOARD.getImage(),
                configBoardPos.x,
                configBoardPos.y,
                null
        );



        c.drawText(
                currentNameText,
                (int) (nameBarPos.x + (ButtonImage.UI_HOLDER1.getWidth() / 2.0)
                        - (currentNameText.length() * 12)),
                nameBarPos.y + (int) (ButtonImage.UI_HOLDER1.getHeight() / 1.6),
                paint
        );


        c.drawBitmap(
                difficultyBox.getGameVideoType().getSprite(0, difficultyChoice),
                difficultyBox.getHitBox().left,
                difficultyBox.getHitBox().top,
                null
        );

        c.drawBitmap(
                charaterData.getGameVideoType().getSprite(0, getStartData()),
                charaterData.getHitBox().left,
                charaterData.getHitBox().top,
                null
        );

    }


    private int getStartData() {
        if (Player.getInstance().getGameCharType() == GameCharacters.CENTAUR) {
            return 0;
        } else if (Player.getInstance().getGameCharType() == GameCharacters.WITCH2) {
            return 1;
        } else {
            return 2;
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
                ButtonImage.CONFIG_BTN_LEFT.getBtnImg(configBtnLeft.isPushed()),
                configBtnLeft.getHitbox().left,
                configBtnLeft.getHitbox().top,
                null
        );
        c.drawBitmap(
                ButtonImage.CONFIG_BTN_SELECT.getBtnImg(configBtnSelect.isPushed()),
                configBtnSelect.getHitbox().left,
                configBtnSelect.getHitbox().top,
                null
        );

        c.drawBitmap(
                ButtonImage.CONFIG_BTN_RIGHT.getBtnImg(configBtnRight.isPushed()),
                configBtnRight.getHitbox().left,
                configBtnRight.getHitbox().top,
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


        double scale = 2.0;
        c.drawBitmap(HelpMethods.getScaledBitmap(scale, Player.getInstance().getGameCharType()
                        .getSprite(Player.getInstance().getCurrentStates().getAnimRow(),
                                Player.getInstance().getAniIndex())),
                configBoardPos.x + 117
                        - (int) (Player.getInstance().getCharacterWidth() * scale / 4.0),
                configBoardPos.y + 603
                        - (int) (Player.getInstance().getCharacterHeight() * scale),
                null);
        Player.getInstance().updatePlayerAnim();

    }


    private void btnConfigAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInBtn(event, btnConfig)) { //when pressed button
                btnConfig.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInBtn(event, btnConfig)) { //check if release inside area of button
                if (btnConfig.isPushed()) { //check if click start in area of button
                    if (viewModel.ableStart(
                            selectCharacter, difficultyChoice, validName)) {
                        viewModel.onBtnConfigClicked();
                    } else {
                        if (!selectCharacter) {
                            hintText = "Please Pick a Character";
                            setTimeCounter();
                            showHint = true;
                        } else if (difficultyChoice <= 0) {
                            hintText = "Please Pick a Difficulty";
                            setTimeCounter();
                            showHint = true;
                        } else if (!validName) {
                            hintText = "Please Enter a Name";
                            setTimeCounter();
                            showHint = true;
                        }
                    }
                }
            }
            //game.getPlaying().initializeAttackBox(characterChoice);
            btnConfig.setPushed(false);
        }
    }



    private  void configBtnSelectAction(MotionEvent event) {
        if (!selectCharacter) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (viewModel.isInBtn(event, configBtnSelect)) {
                    configBtnSelect.setPushed(true);
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (viewModel.isInBtn(event, configBtnSelect)) {
                    selectCharacter = true;
                    Player.getInstance().setCurrentStates(PlayerStates.RUNNING);
                }
            }
        } else {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (viewModel.isInBtn(event, configBtnSelect)) {
                    resetSelect = true;
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (viewModel.isInBtn(event, configBtnSelect) && resetSelect) {
                    resetSelect = false;
                    configBtnSelect.setPushed(false);
                    selectCharacter = false;
                    Player.getInstance().setCurrentStates(PlayerStates.WALK);
                }
            }
        }
    }

    private  void configBtnLeftAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInBtn(event, configBtnLeft)) {
                configBtnLeft.setPushed(true);
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInBtn(event, configBtnLeft)) {
                if (configBtnLeft.isPushed()) {
                    configBtnSelect.setPushed(false);
                    selectCharacter = false;

                    if (Player.getInstance().getGameCharType() == GameCharacters.WARRIOR2) {
                        Player.getInstance().setCharStrategy(new CharTwo());
                    } else if (Player.getInstance().getGameCharType() == GameCharacters.WITCH2) {
                        Player.getInstance().setCharStrategy(new CharOne());
                    } else {
                        Player.getInstance().setCharStrategy(new CharThree());
                    }
                    Player.getInstance().setCurrentStates(PlayerStates.WALK);
                }
            }
            configBtnLeft.setPushed(false);
        }
    }


    private  void configBtnRightAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInBtn(event, configBtnRight)) {
                configBtnRight.setPushed(true);
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInBtn(event, configBtnRight)) {
                if (configBtnRight.isPushed()) {
                    configBtnSelect.setPushed(false);
                    selectCharacter = false;

                    if (Player.getInstance().getGameCharType() == GameCharacters.CENTAUR) {
                        Player.getInstance().setCharStrategy(new CharTwo());
                    } else if (Player.getInstance().getGameCharType() == GameCharacters.WITCH2) {
                        Player.getInstance().setCharStrategy(new CharThree());
                    } else {
                        Player.getInstance().setCharStrategy(new CharOne());
                    }
                    Player.getInstance().setCurrentStates(PlayerStates.WALK);
                }
            }
            configBtnRight.setPushed(false);
        }
    }

    private void changeNameAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInBtn(event, nameBar)) { //when pressed button
                nameBar.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInBtn(event, nameBar)) { //check if release inside area of button
                if (nameBar.isPushed()) { //check if click start in area of button
                    dialog.show();
                }
            }
            nameBar.setPushed(false);
        }
    }


    private void pickDifficultyAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInCharacter(event, difficultyBox)) { //when pressed button
                difficultyBox.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            //check if release inside area of button
            if (viewModel.isInCharacter(event, difficultyBox)) {
                if (difficultyBox.isPushed()) { //check if click start in area of button
                    viewModel.onPickDifficulty();
                }
            }
            difficultyBox.setPushed(false);
        }
    }

    private void setHintText(Canvas c) {
        if (counter <= 0) {
            showHint = false;
        }
        c.drawText(
                hintText,
                GameConstants.UiLocation.NAME_TEXT_POS_X - (hintText.length() * 12),
                configBoardPos.y + GameImages.CONFIG_BOARD.getHeight() - 50,
                paint
        );
        counter--;
    }

    private void setTimeCounter() {
        showHint = true;
        counter = 10 * 60;
    }

    private void nameDialog() {
        // 创建 AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(MainViewModel.getGameContext());
        builder.setTitle("Player's Name:");
        // 创建 EditText 作为对话框的内容
        final EditText editText = new EditText(MainViewModel.getGameContext());
        builder.setView(editText);

        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 在这里处理用户输入的文字
                userInput = editText.getText().toString();
                if (viewModel.isNameValid(userInput)) {
                    if (viewModel.nameLengthBelowLimit(userInput)) {
                        //currentNameText = userInput;
                        viewModel.setPlayerName(userInput);
                        validName = true;
                    } else {
                        setTimeCounter(); //show hint for 10 second
                        hintText = "Maximum 15 Characters";
                    }

                } else {
                    setTimeCounter(); //show hint for 10 second
                    hintText = "Please Enter a Valid Name";
                }
                viewModel.cleanUi();
            }
        });
        // 设置取消按钮
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                viewModel.cleanUi();
            }
        });
        // 创建并显示对话框
        dialog = builder.create();
    }

    private void setUpCharacterAnimation() {
        teresa = new GameAnimation(
                0,
                0,
                GameVideos.TERESA.getWidth(),
                GameVideos.TERESA.getWidth(),
                GameVideos.TERESA
        );
        witch = new GameAnimation(
                200,
                0,
                GameVideos.WITCH.getWidth(),
                GameVideos.WITCH.getWidth(),
                GameVideos.WITCH
        );
        warrior = new GameAnimation(
                400,
                0,
                GameVideos.WARRIOR.getWidth(),
                GameVideos.WARRIOR.getHeight(),
                GameVideos.WARRIOR
        );
    }





}
