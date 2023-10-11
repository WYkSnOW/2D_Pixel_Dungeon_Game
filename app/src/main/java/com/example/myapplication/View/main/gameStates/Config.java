package com.example.myapplication.View.main.gameStates;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myapplication.Model.gameStatesLogic.ConfigLogic;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;
import com.example.myapplication.ViewModel.MainViewModel;
import com.example.myapplication.ViewModel.gameStatesVideoModel.ConfigViewModel;

public class Config extends BaseState implements GameStateInterFace {
    private Paint paint = new Paint();
    private final CustomButton btnConfig;
    private final CustomButton nameBar;
    private final GameAnimation configBackground;

    private int characterChoice;
    private int difficultyChoice;
    private final GameAnimation difficultyBox;
    private GameAnimation teresa;
    private GameAnimation witch;
    private GameAnimation warrior;
    private int teresaState;
    private int witchState;
    private int warriorState;
    private String currentNameText;
    private String userInput;
    private AlertDialog dialog;
    private int counter;
    private boolean showHint;
    private String hintText;
    private boolean validName;
    private Context context;
    private ConfigLogic configLogic;
    private final ConfigViewModel viewModel;

    public Config(Game game, Context context) {
        super(game);
        this.context = context;

        paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);

        btnConfig = new CustomButton(
                GameConstants.UiLocation.CONFIG_START_BTN_POS_X,
                GameConstants.UiLocation.CONFIG_START_BTN_POS_Y,
                ButtonImage.MENU_START.getWidth(),
                ButtonImage.MENU_START.getHeight()
        );

        nameBar = new CustomButton(
                GameConstants.UiLocation.NAME_BAR_POS_X,
                GameConstants.UiLocation.NAME_BAR_POS_Y,
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

        configBackground = new GameAnimation(
                0,
                0,
                GameConstants.UiSize.GAME_WIDTH,
                GameConstants.UiSize.GAME_HEIGHT,
                GameVideos.CONFIG_BACK_VIDEO
        );


        configLogic = new ConfigLogic();
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

        viewModel.getCharacterChoice().observe((LifecycleOwner) context, new Observer<Integer>() {
            @Override
            public void onChanged(Integer choice) {
                characterChoice = choice;
            }
        });
        viewModel.getConfigButtonClicked().observe((LifecycleOwner) context,
                new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean clicked) {
                    if (clicked) {
                        viewModel.btnConfigRespond(
                                game, currentNameText, difficultyChoice, characterChoice
                        );
                    }
                }
            });
        viewModel.getPickTeresa().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean clicked) {
                if (clicked) {
                    if (teresa.isPushed()) { //check if click start in area of button
                        viewModel.setCharacterChoice(1);
                        teresaState = 0;
                        witchState = 2;
                        warriorState = 2;
                    }
                }
            }
        });
        viewModel.getPickWitch().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean clicked) {
                if (clicked) {
                    if (witch.isPushed()) { //check if click start in area of button
                        viewModel.setCharacterChoice(2);
                        witchState = 0;
                        teresaState = 2;
                        warriorState = 2;
                    }
                }
            }
        });
        viewModel.getPickWarrior().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean clicked) {
                if (clicked) {
                    if (warrior.isPushed()) { //check if click start in area of button
                        viewModel.setCharacterChoice(3);
                        warriorState = 0;
                        teresaState = 2;
                        witchState = 2;
                    }
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
        characterChoice = 0;
        difficultyChoice = 0;
        teresaState = 2;
        witchState = 2;
        warriorState = 2;
    }

    @Override
    public void update(double delta) {
        configBackground.update(delta);
        teresa.update(delta);
        witch.update(delta);
        warrior.update(delta);
    }

    @Override
    public void touchEvents(MotionEvent event) {
        btnConfigAction(event);
        pickTeresa(event);
        pickWitch(event);
        pickWarrior(event);
        changeNameAction(event);
        pickDifficultyAction(event);
    }

    @Override
    public void render(Canvas c) {
        drawBackground(c);
        drawBtn(c);
        drawCharacter(c);
        drawUi(c);
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
        c.drawText(
                currentNameText,
                GameConstants.UiLocation.NAME_TEXT_POS_X - (currentNameText.length() * 12),
                GameConstants.UiLocation.NAME_TEXT_POS_Y, paint
        );
        c.drawBitmap(
                difficultyBox.getGameVideoType().getSprite(0, difficultyChoice),
                difficultyBox.getHitBox().left,
                difficultyBox.getHitBox().top,
                null
        );
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
                teresa.getGameVideoType().getSprite(teresaState, teresa.getAniIndex()),
                teresa.getHitBox().left,
                teresa.getHitBox().top,
                null
        );
        c.drawBitmap(
                witch.getGameVideoType().getSprite(witchState, witch.getAniIndex()),
                witch.getHitBox().left,
                witch.getHitBox().top,
                null
        );
        c.drawBitmap(
                warrior.getGameVideoType().getSprite(warriorState, warrior.getAniIndex()),
                warrior.getHitBox().left,
                warrior.getHitBox().top,
                null
        );
    }


    private void btnConfigAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInBtn(event, btnConfig)) { //when pressed button
                btnConfig.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInBtn(event, btnConfig)) { //check if release inside area of button
                if (btnConfig.isPushed()) { //check if click start in area of button
                    viewModel.initPlayerCharacter(characterChoice);
                    if (viewModel.ableStart(characterChoice, difficultyChoice, validName)) {
                        viewModel.onBtnConfigClicked();
                    } else {
                        if (characterChoice <= 0) {
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
    private void pickTeresa(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInCharacter(event, teresa)) { //when pressed button
                teresa.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInCharacter(event, teresa)) { //check if release inside area of button
                viewModel.onPickTeresa();
            }
            teresa.setPushed(false);
        }
    }
    private void pickWitch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInCharacter(event, witch)) { //when pressed button
                witch.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInCharacter(event, witch)) { //check if release inside area of button
                viewModel.onPickWitch();
            }
            teresa.setPushed(false);
        }
    }
    private void pickWarrior(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInCharacter(event, warrior)) { //when pressed button
                warrior.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInCharacter(event, warrior)) { //check if release inside area of button
                viewModel.onPickWarrior();
            }
            warrior.setPushed(false);
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
                GameConstants.UiLocation.HINT_TEXT_POS_Y, paint
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
                    if (userInput.length() <= 20) {
                        //currentNameText = userInput;
                        viewModel.setPlayerName(userInput);
                        validName = true;
                    } else {
                        setTimeCounter(); //show hint for 10 second
                        hintText = "Maximum 20 Characters";
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
                GameConstants.Videos.PICK_TERESA_POS_X,
                GameConstants.Videos.PICK_TERESA_POS_Y,
                GameVideos.TERESA.getWidth(),
                GameVideos.TERESA.getWidth(),
                GameVideos.TERESA
        );
        witch = new GameAnimation(
                GameConstants.Videos.PICK_WITCH_POS_X,
                GameConstants.Videos.PICK_WITCH_POS_Y,
                GameVideos.WITCH.getWidth(),
                GameVideos.WITCH.getWidth(),
                GameVideos.WITCH
        );
        warrior = new GameAnimation(
                GameConstants.Videos.PICK_WARRIOR_POS_X,
                GameConstants.Videos.PICK_WARRIOR_POS_Y,
                GameVideos.WARRIOR.getWidth(),
                GameVideos.WARRIOR.getHeight(),
                GameVideos.WARRIOR
        );
    }





}
