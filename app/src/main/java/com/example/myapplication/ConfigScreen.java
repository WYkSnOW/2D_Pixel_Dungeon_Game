package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ConfigScreen extends AppCompatActivity {
    int levelDifficulty = 0;
    int characterChoice = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_screen);


        EditText playerNameInput = (EditText) findViewById(R.id.playerName);
        TextView playerNameRule = findViewById(R.id.playerNameRule);
        Button character1 = findViewById (R.id.character1);
        Button character2 = findViewById (R.id.character2);
        Button character3 = findViewById (R.id.character3);
        Button difficulty1 = findViewById (R.id.difficulty1);
        Button difficulty2 = findViewById (R.id.difficulty2);
        Button difficulty3 = findViewById (R.id.difficulty3);
        Button startBtn = findViewById (R.id.goes_to_playerView);

        character1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                character1.setBackgroundResource(R.drawable.button_after_click);
                character2.setBackgroundResource(R.drawable.button_shape);
                character3.setBackgroundResource(R.drawable.button_shape);
                characterChoice = 1;
            }
        });
        character2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                character2.setBackgroundResource(R.drawable.button_after_click);
                character1.setBackgroundResource(R.drawable.button_shape);
                character3.setBackgroundResource(R.drawable.button_shape);
                characterChoice = 2;
            }
        });
        character3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                character3.setBackgroundResource(R.drawable.button_after_click);
                character2.setBackgroundResource(R.drawable.button_shape);
                character1.setBackgroundResource(R.drawable.button_shape);
                characterChoice = 3;
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


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = playerNameInput.getText().toString();
                Intent intent = new Intent();
                intent.setClass(ConfigScreen.this, PlayerView.class);

                Bundle bundle = new Bundle();
                bundle.putString("playerName", playerName);
                bundle.putInt("characterChoice", characterChoice);
                bundle.putInt("levelDifficulty", levelDifficulty);
                intent.putExtras(bundle);
                if (playerName.equals("") || !(ifNotBlank(playerName)) || levelDifficulty == 0 || characterChoice ==0) {
                    playerNameRule.setText(R.string.playerNameRule);
                    //android:text="Player view"
                } else {
                    startActivity(intent);
                    finish();
                }
                /*if (levelDifficulty != 0 && characterChoice != 0 && !(playerName.equals("")) && ifNotBlank(playerName)) {
                    startActivity(intent);
                    finish();
                }*/
            }
        });

    }

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
