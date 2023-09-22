package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ConfigScreen extends AppCompatActivity {
    int levelDifficulty = 0;
    int characterChoice = 0;
    private boolean animationState = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_screen);


        //get id and create variable link to it
        EditText playerNameInput = (EditText) findViewById(R.id.playerName);
        TextView playerNameRule = findViewById(R.id.playerNameRule);

        Button difficulty1 = findViewById (R.id.difficulty1);
        Button difficulty2 = findViewById (R.id.difficulty2);
        Button difficulty3 = findViewById (R.id.difficulty3);
        Button startBtn = findViewById (R.id.goes_to_playerView);

        ImageView elf = findViewById(R.id.character1_elf);
        ImageView lizard = findViewById(R.id.character2_lizard);
        ImageView wizzard = findViewById(R.id.character3_wizzard);
        AnimationDrawable elfAnimation = (AnimationDrawable) elf.getBackground();
        AnimationDrawable lizardAnimation = (AnimationDrawable) lizard.getBackground();
        AnimationDrawable wizzardAnimation = (AnimationDrawable) wizzard.getBackground();








        elf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                characterChoice = 1;
                elfAnimation.start();
                lizardAnimation.stop();
                wizzardAnimation.stop();
            }
        });
        lizard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                characterChoice = 2;
                elfAnimation.stop();
                lizardAnimation.start();
                wizzardAnimation.stop();
            }
        });
        wizzard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                characterChoice = 3;
                elfAnimation.stop();
                lizardAnimation.stop();
                wizzardAnimation.start();
            }
        });
        difficulty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty1.setBackgroundResource(R.drawable.button_after_click);
                difficulty2.setBackgroundResource(R.drawable.button_shape);
                difficulty3.setBackgroundResource(R.drawable.button_shape);
                levelDifficulty = 1;
            }
        });
        difficulty2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty2.setBackgroundResource(R.drawable.button_after_click);
                difficulty1.setBackgroundResource(R.drawable.button_shape);
                difficulty3.setBackgroundResource(R.drawable.button_shape);
                levelDifficulty = 2;
            }
        });
        difficulty3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty3.setBackgroundResource(R.drawable.button_after_click);
                difficulty2.setBackgroundResource(R.drawable.button_shape);
                difficulty1.setBackgroundResource(R.drawable.button_shape);
                levelDifficulty = 3;
            }
        });


        //button to Player view
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = playerNameInput.getText().toString();//get player name from text bar
                Intent intent = new Intent();//new intent
                intent.setClass(ConfigScreen.this, PlayerView.class);//intent to open next activity

                //new bundle contain players info
                Bundle bundle = new Bundle();
                bundle.putString("playerName", playerName);
                bundle.putInt("characterChoice", characterChoice);
                bundle.putInt("levelDifficulty", levelDifficulty);
                intent.putExtras(bundle);//add bundle to intent

                //check all information is collect
                if (playerName.equals("") || !(ifNotBlank(playerName)) || levelDifficulty == 0 || characterChoice ==0) {
                    playerNameRule.setText(R.string.playerNameRule);
                } else {
                    //jump screen and close current activity
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    //check if player put in a in valid name
    private boolean ifNotBlank(String name) {
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


}
