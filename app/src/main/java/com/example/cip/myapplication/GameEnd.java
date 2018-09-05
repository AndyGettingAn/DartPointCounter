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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameEnd extends AppCompatActivity {

    Button bShare,
        bNewGame;
    private File imagePath;

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
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
                shareIt();
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
