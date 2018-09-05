package com.example.cip.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameEnd extends AppCompatActivity {

    Button bShare,
        bNewGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.win);
        mp.start();

        bShare = (Button) findViewById(R.id.buttonShare);
        bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "You Body Here";
                String shareSub = "Your Subjet here";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "Erfolg teilen"));
            }
        });

        bNewGame = (Button) findViewById(R.id.buttonNewGame);
        bNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameEnd.this, PointsCounter.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
