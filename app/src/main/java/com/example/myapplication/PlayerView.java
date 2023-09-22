package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerView extends AppCompatActivity {

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
        ImageView character = findViewById(R.id.character);

        name.setText("Player's name: " + playerName);
        characterText.setText("Character#: " + characterChoice);
        difficultyText.setText("Level of difficulty: " + levelDifficulty);
        startHealthText.setText("StartHealth: " + startHealth);


        if (characterChoice == 1) {
            character.setBackgroundResource(R.drawable.elf_f_idle_animation);
        } else if (characterChoice == 2) {
            character.setBackgroundResource(R.drawable.lizard_m_idle_animation);
        } else {
            character.setBackgroundResource(R.drawable.wizzard_m_idle_animation);
        }
        AnimationDrawable characterAnimation = (AnimationDrawable) character.getBackground();
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



    }

}
