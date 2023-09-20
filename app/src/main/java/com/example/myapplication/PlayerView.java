package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        name.setText("Player's name: " + playerName);
        characterText.setText("Character: " + characterChoice);
        difficultyText.setText("Level of difficulty: " + levelDifficulty);
        startHealthText.setText("StartHealthT: " + startHealth);





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
