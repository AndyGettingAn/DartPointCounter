package com.example.cip.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameEnd extends AppCompatActivity implements View.OnClickListener {

    private File imagePath;
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
            Bitmap bitmap = takeScreenshot();
            saveBitmap(bitmap);
            shareIt();
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

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        //String shareBody = "In Tweecher, My highest score with screen shot";
        //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Tweecher score");
        //sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        //sharingIntent.putExtra(Intent.EXTRA_TITLE,  shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(sharingIntent, "Teilen über"));
    }
}
