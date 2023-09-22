package com.example.myapplication;

import static com.example.myapplication.MainActivity.endMusic;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerView extends AppCompatActivity {

    private ImageView character;
    private  int characterLeft; //character left location(x-axis)
    private int characterTop; //character right location(y-axis)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_view);

        //receive info form config screen
        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        int characterChoice = intent.getIntExtra("characterChoice",0);
        int levelDifficulty = intent.getIntExtra("levelDifficulty",0);
        int startHealth = 100 / levelDifficulty;

        //Create variable that link to TextView
        TextView name = findViewById(R.id.nameBar);
        TextView characterText = findViewById(R.id.characterBar);
        TextView difficultyText = findViewById(R.id.difficulty);
        TextView startHealthText = findViewById(R.id.startHealth);

        character = findViewById(R.id.character);
        //get character's location
        characterLeft = character.getLeft();
        characterTop = character.getTop();

        //Display player's info
        name.setText("Player's name: " + playerName);
        characterText.setText("Character#: " + characterChoice);
        difficultyText.setText("Level of difficulty: " + levelDifficulty);
        startHealthText.setText("StartHealth: " + startHealth);

        //create animation variable base on character choice
        int characterRunFile;
        int characterIdleFile;
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
        AnimationDrawable characterAnimation = (AnimationDrawable) character.getBackground();
        characterAnimation.start();

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

        //move character
        character.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                        moveRight();
                        return true;
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                        moveLeft();
                        return true;
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                        moveUp();
                        return true;
                    }
                    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                        moveDown();
                        return true;
                    }
                }

                return false;
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


}
