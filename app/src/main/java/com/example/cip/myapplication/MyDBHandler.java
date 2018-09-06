package com.example.cip.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gameHistoryDB.db";
    public static final String TABLE_NAME = "gameHistory";
    public static final String COLUMN_ID = "HistoryID";
    public static final String COLUMN_WINNER = "Winner";
    public static final String COLUMN_PLAYER1_NAME = "Player1Name";
    public static final String COLUMN_PLAYER1_AVERAGE = "Player1Average";
    public static final String COLUMN_PLAYER1_COUNTER100 = "Player1Counter100";
    public static final String COLUMN_PLAYER1_COUNTER160 = "Player1Counter160";
    public static final String COLUMN_PLAYER1_COUNTER180 = "Player1Counter180";
    public static final String COLUMN_PLAYER1_HIGHESTTHROW = "Player1HighestThrow";
    public static final String COLUMN_PLAYER2_NAME = "Player2Name";
    public static final String COLUMN_PLAYER2_AVERAGE = "Player2Average";
    public static final String COLUMN_PLAYER2_COUNTER100 = "Player2Counter100";
    public static final String COLUMN_PLAYER2_COUNTER160 = "Player2Counter160";
    public static final String COLUMN_PLAYER2_COUNTER180 = "Player2Counter180";
    public static final String COLUMN_PLAYER2_HIGHESTTHROW = "Player2HighestThrow";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + "INTEGER PRIMARYKEY," +
                COLUMN_PLAYER1_NAME + "TEXT," +
                COLUMN_PLAYER1_AVERAGE + "INTEGER," +
                COLUMN_PLAYER1_COUNTER100 + "INTEGER," +
                COLUMN_PLAYER1_COUNTER160 + "INTEGER," +
                COLUMN_PLAYER1_COUNTER180 + "INTEGER," +
                COLUMN_PLAYER1_HIGHESTTHROW + "INTEGER," +
                COLUMN_PLAYER2_NAME + "TEXT," +
                COLUMN_PLAYER2_AVERAGE + "INTEGER," +
                COLUMN_PLAYER2_COUNTER100 + "INTEGER," +
                COLUMN_PLAYER2_COUNTER160 + "INTEGER," +
                COLUMN_PLAYER2_COUNTER180 + "INTEGER," +
                COLUMN_PLAYER2_HIGHESTTHROW + "INTEGER," +
                COLUMN_WINNER + "TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(GameHistory gameHistory) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, gameHistory.getID_counter());
        values.put(COLUMN_PLAYER1_NAME, gameHistory.getPlayers_name()[0]);
        values.put(COLUMN_PLAYER1_AVERAGE, gameHistory.getAverage_points()[0]);
        values.put(COLUMN_PLAYER1_COUNTER100, gameHistory.getCounter_100()[0]);
        values.put(COLUMN_PLAYER1_COUNTER160, gameHistory.getCounter_160()[0]);
        values.put(COLUMN_PLAYER1_COUNTER180, gameHistory.getCounter_180()[0]);
        values.put(COLUMN_PLAYER1_HIGHESTTHROW, gameHistory.getHighestThrow()[0]);
        values.put(COLUMN_PLAYER2_NAME, gameHistory.getPlayers_name()[1]);
        values.put(COLUMN_PLAYER2_AVERAGE, gameHistory.getAverage_points()[1]);
        values.put(COLUMN_PLAYER2_COUNTER100, gameHistory.getCounter_100()[1]);
        values.put(COLUMN_PLAYER2_COUNTER160, gameHistory.getCounter_160()[1]);
        values.put(COLUMN_PLAYER2_COUNTER180, gameHistory.getCounter_180()[1]);
        values.put(COLUMN_PLAYER2_HIGHESTTHROW, gameHistory.getHighestThrow()[1]);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /*
    public GameHistory findHandler(int GameID) {

        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_ID + " = " + "'" + GameID + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        GameHistory gameHistory = new GameHistory();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            gameHistory.setID_counter(Integer.parseInt(cursor.getString(0)));
            String[] names = new String[]{cursor.getString(0),cursor.getString(1)};
            gameHistory.setPlayers_name(names);
            cursor.close();
        } else {
            gameHistory = null;
        }
        db.close();
        return gameHistory;

    }
    */

}
