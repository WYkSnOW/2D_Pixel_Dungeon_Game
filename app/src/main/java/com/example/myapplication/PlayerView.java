package com.example.myapplication;

import static com.example.myapplication.MainActivity.endMusic;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerView extends AppCompatActivity {

    private ImageView character;
    private AnimationDrawable characterAnimation;

    private int characterRunFile;
    private  int characterIdleFile;
    private  int characterLeft; //character left location(x-axis)
    private int characterTop; //character right location(y-axis)

    private String hardLevelText;

    private int movingSpeed;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;
    private boolean isCharacterMoving = false;
    private Timer timer;
    private Timer timer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_view);

        movingSpeed = 15;


        //receive info form config screen
        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        int characterChoice = intent.getIntExtra("characterChoice", 0);
        int levelDifficulty = intent.getIntExtra("levelDifficulty", 0);
        int startHealth = 10 / levelDifficulty;

        //Create variable that link to TextView
        TextView name = findViewById(R.id.nameBar);
        TextView characterText = findViewById(R.id.characterBar);
        TextView difficultyText = findViewById(R.id.difficulty);
        TextView startHealthText = findViewById(R.id.startHealth);
        character = findViewById(R.id.character);
        character.setFocusable(true);
        character.setFocusableInTouchMode(true);
        character.requestFocus();
        //get character's location
        characterLeft = character.getLeft();
        characterTop = character.getTop();

        //create animation variable base on character choice
        String characterNameText;
        if (characterChoice == 1) {
            characterIdleFile = getResources().getIdentifier("elf_f_idle_animation",
                    "drawable", getPackageName());
            characterRunFile = getResources().getIdentifier("elf_f_run_animation",
                    "drawable", getPackageName());
            characterNameText = "Elf";
        } else if (characterChoice == 2) {
            characterIdleFile = getResources().getIdentifier("knight_m_idle_animation",
                    "drawable", getPackageName());
            characterRunFile = getResources().getIdentifier("knight_m_run_animation",
                    "drawable", getPackageName());
            characterNameText = "Knight";
        } else {
            characterIdleFile = getResources().getIdentifier("wizzard_m_idle_animation",
                    "drawable", getPackageName());
            characterRunFile = getResources().getIdentifier("wizzard_m_run_animation",
                    "drawable", getPackageName());
            characterNameText = "Wizard";
        }
        character.setBackgroundResource(characterIdleFile);
        characterAnimation = (AnimationDrawable) character.getBackground();
        characterAnimation.start();

        if (levelDifficulty == 1) {
            hardLevelText = "Easy";
        } else if (levelDifficulty == 2) {
            hardLevelText = "Normal";
        } else if (levelDifficulty == 3) {
            hardLevelText = "Hard";
        }

        //Display player's info
        name.setText("Player's name: " + playerName);
        characterText.setText("Character: " + characterNameText);
        difficultyText.setText("Level of difficulty: " + hardLevelText);
        startHealthText.setText("StartHealth: " + startHealth);

        //to next activity
        Button button = findViewById(R.id.goes_to_endingScreen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PlayerView.this, EndingScreen.class);
                startActivity(intent);
                endMusic();
                finish();
            }
        });




        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                    setCharacterToIdle();
            }
        }, 0, 300);




        //move character
        character.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    setCharacterToRun();
                    if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_D) {
                        isMovingRight = true;
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_A) {
                        isMovingLeft = true;
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_W) {
                        isMovingUp = true;
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_S) {
                        isMovingDown = true;
                    }

                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_D) {
                        isMovingRight = false;
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_A) {
                        isMovingLeft = false;
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_W) {
                        isMovingUp = false;
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_S) {
                        isMovingDown = false;
                    }
                }

                if (isMovingDown) {
                    moveDown();
                    return true;
                }
                if (isMovingUp) {
                    moveUp();
                    return true;
                }
                if (isMovingLeft) {
                    moveLeft();
                    return true;
                }
                if (isMovingRight) {
                    moveRight();
                    return true;
                }

                return false;
            }
        });
    }



    private void moveRight() {
        characterLeft += movingSpeed;
        character.setScaleX(1f);
        character.setTranslationX(characterLeft);
    }
    private void moveLeft() {
        characterLeft -= movingSpeed;
        character.setScaleX(-1f);
        character.setTranslationX(characterLeft);
    }
    private void moveUp() {
        characterTop -= movingSpeed;
        character.setTranslationY(characterTop);
    }
    private void moveDown() {
        characterTop += movingSpeed;
        character.setTranslationY(characterTop);
    }

    private void setCharacterToIdle() {
        character.setBackgroundResource(characterIdleFile);
        characterAnimation = (AnimationDrawable) character.getBackground();
        characterAnimation.start();
    }

    private void setCharacterToRun() {
        character.setBackgroundResource(characterRunFile);
        characterAnimation = (AnimationDrawable) character.getBackground();
        characterAnimation.start();
    }

    private void deflateMove() {
        isMovingDown = false;
        isMovingUp = false;
        isMovingLeft = false;
        isMovingRight = false;
    }




}
