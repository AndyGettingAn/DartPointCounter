package com.example.cip.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StatisticDbSource {

    private String LOG_TAG = StatisticDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DartDbHandler dbHelper;

    String[] Columns = new String[]{dbHelper.COLUMN_ID,dbHelper.COLUMN_WINNER,dbHelper.COLUMN_PLAYER1_NAME,dbHelper.COLUMN_PLAYER1_AVERAGE,
            dbHelper.COLUMN_PLAYER1_COUNTER100,dbHelper.COLUMN_PLAYER1_COUNTER160, dbHelper.COLUMN_PLAYER1_COUNTER180,
            dbHelper.COLUMN_PLAYER1_HIGHESTTHROW, dbHelper.COLUMN_PLAYER2_NAME, dbHelper.COLUMN_PLAYER2_AVERAGE, dbHelper.COLUMN_PLAYER2_COUNTER100,
            dbHelper.COLUMN_PLAYER2_COUNTER160, dbHelper.COLUMN_PLAYER2_COUNTER180, dbHelper.COLUMN_PLAYER2_HIGHESTTHROW};

    public StatisticDbSource(Context context){
        dbHelper = new DartDbHandler(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void openRead(){
        database = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void addHistory(GameHistory gameHistory) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_PLAYER1_NAME, gameHistory.getPlayers_name()[0]);
        values.put(dbHelper.COLUMN_PLAYER1_AVERAGE, gameHistory.getAverage_points()[0]);
        values.put(dbHelper.COLUMN_PLAYER1_COUNTER100, gameHistory.getCounter_100()[0]);
        values.put(dbHelper.COLUMN_PLAYER1_COUNTER160, gameHistory.getCounter_160()[0]);
        values.put(dbHelper.COLUMN_PLAYER1_COUNTER180, gameHistory.getCounter_180()[0]);
        values.put(dbHelper.COLUMN_PLAYER1_HIGHESTTHROW, gameHistory.getHighestThrow()[0]);
        values.put(dbHelper.COLUMN_PLAYER2_NAME, gameHistory.getPlayers_name()[1]);
        values.put(dbHelper.COLUMN_PLAYER2_AVERAGE, gameHistory.getAverage_points()[1]);
        values.put(dbHelper.COLUMN_PLAYER2_COUNTER100, gameHistory.getCounter_100()[1]);
        values.put(dbHelper.COLUMN_PLAYER2_COUNTER160, gameHistory.getCounter_160()[1]);
        values.put(dbHelper.COLUMN_PLAYER2_COUNTER180, gameHistory.getCounter_180()[1]);
        values.put(dbHelper.COLUMN_PLAYER2_HIGHESTTHROW, gameHistory.getHighestThrow()[1]);
        open();
        database.insert(dbHelper.TABLE_NAME, null, values);
        close();
        Log.d(LOG_TAG, "added");
    }

    private GameHistory cursorToGameHistory (Cursor cursor){
        int idGame = cursor.getColumnIndex(DartDbHandler.COLUMN_ID);
        int idName1 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER1_NAME);
        int idAverage1 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER1_AVERAGE);
        int id1Counter100 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER1_COUNTER100);
        int id1Counter160 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER1_COUNTER160);
        int id1Counter180 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER1_COUNTER180);
        int idHighestThrow1 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER1_HIGHESTTHROW);

        int idName2 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER2_NAME);
        int idAverage2 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER2_AVERAGE);
        int id2Counter100 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER2_COUNTER100);
        int id2Counter160 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER2_COUNTER160);
        int id2Counter180 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER2_COUNTER180);
        int idHighestThrow2 = cursor.getColumnIndex(DartDbHandler.COLUMN_PLAYER2_HIGHESTTHROW);

        String name_player1 = cursor.getString(idName1);
        double average_player1 = cursor.getDouble(idAverage1);
        int counter100_player1 = cursor.getInt(id1Counter100);
        int counter160_player1 = cursor.getInt(id1Counter160);
        int counter180_player1 = cursor.getInt(id1Counter180);
        int highest_throw1 = cursor.getInt(idHighestThrow1);
        String name_player2 = cursor.getString(idName2);
        double average_player2 = cursor.getDouble(idAverage2);
        int counter100_player2 = cursor.getInt(id2Counter100);
        int counter160_player2 = cursor.getInt(id2Counter160);
        int counter180_player2 = cursor.getInt(id2Counter180);
        int highest_throw2 = cursor.getInt(idHighestThrow2);

        String[] names = new String[]{name_player1,name_player2};
        double[] averages = new double[]{average_player1,average_player2};
        int[] counter100 = new int[]{counter100_player1,counter100_player2};
        int[] counter160 = new int[]{counter160_player1,counter160_player2};
        int[] counter180 = new int[]{counter180_player1,counter180_player2};
        int[] highest_throw = new int[]{highest_throw1,highest_throw2};
        GameHistory result = new GameHistory(counter100,counter160,counter180,averages, highest_throw, names);
        return  result;
    }

    public List<GameHistory> getAllGameHistory(){
        List<GameHistory> gameHistoryList = new ArrayList<>();
        Log.d(LOG_TAG, "8");
        Cursor cursor = database.query(dbHelper.TABLE_NAME, Columns,null,null,null,null,null);
        Log.d(LOG_TAG, "7");
        cursor.moveToFirst();
        GameHistory gameHistory;

        while(!cursor.isAfterLast()){
            gameHistory = cursorToGameHistory(cursor);
            gameHistoryList.add(gameHistory);
            cursor.moveToNext();
        }
        cursor.close();
        close();

        return gameHistoryList;
    }

}
