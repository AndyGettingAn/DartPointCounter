package com.example.cip.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameEnd extends AppCompatActivity implements View.OnClickListener {

    private String winnerName;
    private String loserName;
    private int set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        Intent intent = getIntent();
        winnerName = intent.getStringExtra("winnerName");
        loserName = intent.getStringExtra("loserName");
        set = intent.getIntExtra("set", 0);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.win);
        Button bShare = (Button) findViewById(R.id.buttonShare);
        Button bNewGame = (Button) findViewById(R.id.buttonNewGame);
        bShare.setOnClickListener(this);
        bNewGame.setOnClickListener(this);
        setText();
        mp.start();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonShare){
            ShareSceenshot shareScreenshot = new ShareSceenshot();
            View rootView = findViewById(android.R.id.content).getRootView();
            startActivity(Intent.createChooser(shareScreenshot.getShareIntent(rootView), shareScreenshot.SHARE_MESSAGE));

        }else if (id == R.id.buttonNewGame){
            Intent intent = new Intent(GameEnd.this, PointsCounter.class);
            startActivity(intent);
            finish();
        }
    }

    private void setText() {
        TextView text = (TextView) findViewById(R.id.textGameEnd);
        text.setText("Herzlichen Glückwunsch! \n" + winnerName + " hat " + loserName + "\n in " + set + " Runden geschlagen");
    }
}
