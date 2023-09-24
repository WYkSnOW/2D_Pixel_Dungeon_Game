package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ConfigScreen extends AppCompatActivity {
    private  int levelDifficulty = 0;
    private  int characterChoice = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_screen);


        //get id and create variable link to it
        EditText playerNameInput = (EditText) findViewById(R.id.playerName);
        TextView playerNameRule = findViewById(R.id.playerNameRule);

        Button difficulty1 = findViewById(R.id.difficulty1);
        Button difficulty2 = findViewById(R.id.difficulty2);
        Button difficulty3 = findViewById(R.id.difficulty3);
        Button startBtn = findViewById(R.id.goes_to_playerView);

        ImageView elf = findViewById(R.id.character1_elf);
        ImageView knight = findViewById(R.id.character2_knight);
        ImageView wizard = findViewById(R.id.character3_wizzard);

        ImageView selectBox1 = findViewById(R.id.selectBox1);
        ImageView selectBox2 = findViewById(R.id.selectBox2);
        ImageView selectBox3 = findViewById(R.id.selectBox3);

        //create animationDrawable
        AnimationDrawable elfAnimation = (AnimationDrawable) elf.getBackground();
        AnimationDrawable knightAnimation = (AnimationDrawable) knight.getBackground();
        AnimationDrawable wizardAnimation = (AnimationDrawable) wizard.getBackground();


        //set up background video
        Video mainBackgroundVideo = new Video(this, R.raw.congif_background);
        FrameLayout backgroundVideoContainer = findViewById(R.id.configBackground);
        backgroundVideoContainer.addView(mainBackgroundVideo);

        //start/stop animation base on player's click
        elf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                characterChoice = 1;
                elfAnimation.start();
                knightAnimation.stop();
                wizardAnimation.stop();

                selectBox1.setBackgroundResource(R.drawable.selecter_animation);
                AnimationDrawable boxAnimation = (AnimationDrawable) selectBox1.getBackground();
                boxAnimation.start();
                selectBox2.setBackgroundColor(0);
                selectBox3.setBackgroundColor(0);

            }
        });
        knight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                characterChoice = 2;
                elfAnimation.stop();
                knightAnimation.start();
                wizardAnimation.stop();

                selectBox2.setBackgroundResource(R.drawable.selecter_animation);
                AnimationDrawable boxAnimation = (AnimationDrawable) selectBox2.getBackground();
                boxAnimation.start();
                selectBox1.setBackgroundColor(0);
                selectBox3.setBackgroundColor(0);
            }
        });
        wizard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                characterChoice = 3;
                elfAnimation.stop();
                knightAnimation.stop();
                wizardAnimation.start();

                selectBox3.setBackgroundResource(R.drawable.selecter_animation);
                AnimationDrawable boxAnimation = (AnimationDrawable) selectBox3.getBackground();
                boxAnimation.start();
                selectBox1.setBackgroundColor(0);
                selectBox2.setBackgroundColor(0);

            }
        });

        //Change difficulty value and btn's setting by clicking on button
        difficulty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty1.setBackgroundResource(R.drawable.button_difficulty1);
                difficulty2.setBackgroundResource(R.drawable.button_before_click1);
                difficulty3.setBackgroundResource(R.drawable.button_before_click1);
                levelDifficulty = 1;
            }
        });
        difficulty2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty2.setBackgroundResource(R.drawable.button_difficulty2);
                difficulty1.setBackgroundResource(R.drawable.button_before_click1);
                difficulty3.setBackgroundResource(R.drawable.button_before_click1);
                levelDifficulty = 2;
            }
        });
        difficulty3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty3.setBackgroundResource(R.drawable.button_difficulty3);
                difficulty2.setBackgroundResource(R.drawable.button_before_click1);
                difficulty1.setBackgroundResource(R.drawable.button_before_click1);
                levelDifficulty = 3;
            }
        });


        //move to next activity
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get player name from text bar
                String playerName = playerNameInput.getText().toString();
                Intent intent = new Intent(); //new intent
                //intent to open next activity
                intent.setClass(ConfigScreen.this, PlayerView.class);

                //new bundle contain player's info
                Bundle bundle = new Bundle();
                bundle.putString("playerName", playerName);
                bundle.putInt("characterChoice", characterChoice);
                bundle.putInt("levelDifficulty", levelDifficulty);
                intent.putExtras(bundle); //add bundle to intent

                //check all information is collect
                if (playerName.equals("") || !(ifNotBlank(playerName)) || levelDifficulty == 0
                        || characterChoice == 0) {
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
