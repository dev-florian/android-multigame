package com.example.flori.android_multi_game;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.flori.android_multi_game.utils.GameUtils;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        final Handler handler = new Handler();
        int TIME_OUT = 1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GameUtils.launchView(SplashScreen.this, CreatePlayerActivity.class, true);
            }
        }, TIME_OUT);
    }
}
