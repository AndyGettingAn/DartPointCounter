package com.example.cip.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Settings extends AppCompatActivity {

    public static final String  KEY_DARTBOARD = "dartboard_preference",
                            KEY_PLAYER1_NAME = "p1_name",
                            KEY_PLAYER2_NAME = "p2_name",
                            KEY_GAME_VARIANT = "game_preference";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }



}
