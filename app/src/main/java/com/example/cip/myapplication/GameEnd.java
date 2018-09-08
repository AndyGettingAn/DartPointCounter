package com.example.cip.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEnd extends AppCompatActivity implements View.OnClickListener {

    private final String [] CONGRATULATION = new String[]{"Herzlichen Glückwunsch! \n", " hat ", "\n in ", " Runden geschlagen"};
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
            ShareScreenshot shareScreenshot = new ShareScreenshot();
            View rootView = findViewById(android.R.id.content).getRootView();
            startActivity(Intent.createChooser(shareScreenshot.getShareIntent(rootView,this), shareScreenshot.SHARE_MESSAGE));

        }else if (id == R.id.buttonNewGame){
            Intent intent = new Intent(GameEnd.this, PointsCounter.class);
            startActivity(intent);
            finish();
        }
    }

    private void setText() {
        TextView text = (TextView) findViewById(R.id.textGameEnd);
        text.setText(CONGRATULATION[0] + winnerName + CONGRATULATION[1] + loserName + CONGRATULATION[2] + set + CONGRATULATION[3]);
    }
}
