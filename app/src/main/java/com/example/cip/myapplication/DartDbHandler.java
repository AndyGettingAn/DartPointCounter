package com.example.cip.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DartDbHandler extends SQLiteOpenHelper {

    String LOG_TAG = DartDbHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gameHistoryDB.db";
    public static final String TABLE_NAME = "gameHistoryTable";
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

    public static final String CREATE_STRING = "CREATE TABLE " + TABLE_NAME + "(" +
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
            COLUMN_WINNER + "TEXT)";


    public DartDbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_STRING);
        }
        catch (Exception ex){
        }
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

}
