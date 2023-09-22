package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerView extends AppCompatActivity {

    private ImageView character;
    private AnimationDrawable characterAnimation;
    private  int characterLeft;
    private int characterTop;

    private int characterIdleFile;
    private int characterRunFile;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_view);

        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        int characterChoice = intent.getIntExtra("characterChoice",0);
        int levelDifficulty = intent.getIntExtra("levelDifficulty",0);
        int startHealth = 100 / levelDifficulty;


        TextView name = findViewById(R.id.nameBar);
        TextView characterText = findViewById(R.id.characterBar);
        TextView difficultyText = findViewById(R.id.difficulty);
        TextView startHealthText = findViewById(R.id.startHealth);
        character = findViewById(R.id.character);

        name.setText("Player's name: " + playerName);
        characterText.setText("Character#: " + characterChoice);
        difficultyText.setText("Level of difficulty: " + levelDifficulty);
        startHealthText.setText("StartHealth: " + startHealth);





        if (characterChoice == 1) {
            characterIdleFile = getResources().getIdentifier("elf_f_idle_animation", "drawable", getPackageName());
            characterRunFile = getResources().getIdentifier("elf_f_run_animation", "drawable", getPackageName());
        } else if (characterChoice == 2) {
            characterIdleFile = getResources().getIdentifier("knight_m_idle_animation", "drawable", getPackageName());
            characterRunFile = getResources().getIdentifier("knight_m_run_animation", "drawable", getPackageName());
        } else {
            characterIdleFile = getResources().getIdentifier("wizzard_m_idle_animation", "drawable", getPackageName());
            characterRunFile = getResources().getIdentifier("wizzard_m_run_animation", "drawable", getPackageName());
        }
        character.setBackgroundResource(characterIdleFile);
        characterAnimation = (AnimationDrawable) character.getBackground();
        characterAnimation.start();



        Button button = findViewById(R.id.goes_to_endingScreen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PlayerView.this, EndingScreen.class);
                startActivity(intent);
                finish();
            }
        });

        characterLeft = character.getLeft();
        characterTop = character.getTop();


        character.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                        // 右箭头被按下
                        moveRight(); // 移动 ImageView 的方法
                        return true; // 返回 true 表示事件已经被处理
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                        // 右箭头被按下
                        moveLeft(); // 移动 ImageView 的方法
                        return true; // 返回 true 表示事件已经被处理
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                        // 右箭头被按下
                        moveUp(); // 移动 ImageView 的方法
                        return true; // 返回 true 表示事件已经被处理
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                        // 右箭头被按下
                        moveDown(); // 移动 ImageView 的方法
                        return true; // 返回 true 表示事件已经被处理
                    }
                }

                return false; // 返回 false 表示事件未被处理
            }
        });

        character.setFocusable(true);
        character.setFocusableInTouchMode(true);
    }


    private void moveRight() {
        characterLeft += 10;
        character.setScaleX(1f);
        character.setTranslationX(characterLeft);
    }
    private void moveLeft() {
        characterLeft -= 10;
        character.setScaleX(-1f);
        character.setTranslationX(characterLeft);
    }
    private void moveUp() {
        characterTop -= 10;
        character.setTranslationY(characterTop);
    }
    private void moveDown() {
        characterTop += 10;
        character.setTranslationY(characterTop);
    }

    private void startRunAnimation() {
        if (!isRunningAnimationPlaying()) {
            character.setBackgroundResource(characterRunFile);
            AnimationDrawable characterRunAnimation = (AnimationDrawable) character.getBackground();
            characterRunAnimation.start();
        }
    }
    private void stopRunAnimation() {
        if (isRunningAnimationPlaying()) {
            AnimationDrawable characterRunAnimation = (AnimationDrawable) character.getBackground();
            characterRunAnimation.stop();
        }
    }

    private boolean isRunningAnimationPlaying() {
        Drawable background = character.getBackground();
        return (background instanceof AnimationDrawable) && ((AnimationDrawable) background).isRunning();
    }

}
