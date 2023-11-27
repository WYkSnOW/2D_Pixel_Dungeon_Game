package com.example.myapplication.View.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import com.example.myapplication.ViewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.initialize(this);

        setContentView(new GamePanel(MainViewModel.getGameContext()));
        //this is the newest version 11/12
    }
}