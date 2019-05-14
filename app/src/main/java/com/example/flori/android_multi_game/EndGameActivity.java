package com.example.flori.android_multi_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        TextView score = findViewById(R.id.endgame_score);
          int monScore = getIntent().getIntExtra("SCORE", 0);
          score.setText("Votre score est de : " + monScore);

          Button leave = findViewById(R.id.endgame_leave);
          leave.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  // TODO Auto-generated method stub
                  finish();
                  System.exit(0);
              }
          });

          Button restart = findViewById(R.id.endgame_menu);
          restart.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  finish();
              }
          });
    }
}
