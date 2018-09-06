package com.example.cip.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Statistic extends AppCompatActivity {

    String LOG_TAG = Statistic.class.getSimpleName();

    TextView result;

    private StatisticDbSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        Log.d(LOG_TAG, "Fehler  0");
        dataSource = new StatisticDbSource(this);
        dataSource.open();
        Log.d(LOG_TAG, "Fehler  1");
        TextView result = (TextView) findViewById(R.id.textView4);
        Log.d(LOG_TAG, "Fehler  2");
        List<GameHistory> gameHistoryList = dataSource.getAllGameHistory();
        Log.d(LOG_TAG, "Fehler  3");
        GameHistory test = gameHistoryList.get(0);
        Log.d(LOG_TAG, "Fehler  4");
        result.setText(test.getPlayers_name()[0]);
        Log.d(LOG_TAG, "Fehler  5");
        dataSource.close();
    }





}
