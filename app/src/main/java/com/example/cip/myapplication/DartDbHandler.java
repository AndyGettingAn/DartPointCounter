package com.example.cip.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DartDbHandler extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Note_Manager";

    // Table name: Note.
    private static final String TABLE_NOTE = "Note";

    private static final String COLUMN_NOTE_ID ="Note_Id";
    private static final String COLUMN_NOTE_TITLE ="Note_Title";
    private static final String COLUMN_NOTE_CONTENT = "Note_Content";

    public DartDbHandler(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY," + COLUMN_NOTE_TITLE + " TEXT,"
                + COLUMN_NOTE_CONTENT
                + " TEXT" + ")";
        // Execute Script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);

        // Create tables again
        onCreate(db);
    }

/*
    // If Note table has no data
    // default, Insert 2 records.
    public void createDefaultNotesIfNeed()  {
        int count = this.getNotesCount();
        if(count ==0 ) {
            GameHistory note1 = new GameHistory("Firstly see Android ListView",
                    "See Android ListView Example in o7planning.org");
            GameHistory note2 = new GameHistory("Learning Android SQLite",
                    "See Android SQLite Example in o7planning.org");
            this.addNote(note1);
            this.addNote(note2);
        }
    }*/


    public void addNote(GameHistory note) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());

        // Inserting Row
        db.insert(TABLE_NOTE, null, values);

        // Closing database connection
        db.close();
    }


    public GameHistory getNote(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTE, new String[] { COLUMN_NOTE_ID,
                        COLUMN_NOTE_TITLE, COLUMN_NOTE_CONTENT
                        }, COLUMN_NOTE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        GameHistory note = new GameHistory(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return note
        return note;
    }


    public List<GameHistory> getAllNotes() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<GameHistory> noteList = new ArrayList<GameHistory>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GameHistory note = new GameHistory();
                note.setNoteId(Integer.parseInt(cursor.getString(0)));
                note.setNoteTitle(cursor.getString(1));
                note.setNoteContent(cursor.getString(2));
                // Adding note to list
                noteList.add(note);
            } while (cursor.moveToNext());
        }

        // return note list
        return noteList;
    }

    public int getNotesCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
}
/*
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gameHistoryDB.db";
    public static final String TABLE_NAME = "gameHistoryTable";
    public static final String COLUMN_ID = "HistoryID";
    //public static final String COLUMN_WINNER = "Winner";
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

    public DartDbHandler(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                //COLUMN_WINNER + " TEXT,"+
                COLUMN_PLAYER1_NAME + " TEXT,"+
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
                COLUMN_PLAYER2_HIGHESTTHROW + "INTEGER" +
                 ")";
        // Execute script.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addGameHistory(GameHistory gameHistory) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + gameHistory.getPlayers_name().toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
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
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public GameHistory getGameHistory(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID,
                        COLUMN_PLAYER1_NAME,
                        COLUMN_PLAYER1_AVERAGE,
                        COLUMN_PLAYER1_COUNTER100,
                        COLUMN_PLAYER1_COUNTER160,
                        COLUMN_PLAYER1_COUNTER180,
                        COLUMN_PLAYER1_HIGHESTTHROW,
                        COLUMN_PLAYER2_NAME,
                        COLUMN_PLAYER2_AVERAGE,
                        COLUMN_PLAYER2_COUNTER100,
                        COLUMN_PLAYER2_COUNTER160,
                        COLUMN_PLAYER2_COUNTER180,
                        COLUMN_PLAYER2_HIGHESTTHROW}, COLUMN_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


        GameHistory history = new GameHistory(
                cursor.getInt(0),// int history_id
                new int[cursor.getInt(1)], // int[] counter_100
                new int[cursor.getInt(2)],//int[] counter_160
                new int[cursor.getInt(3)],///int[] counter_180
                new double[cursor.getInt(4)],//double[] average_points
                new int[cursor.getInt(5)], //int[] highestThrow
                new String[Integer.parseInt(cursor.getString(6))]);//String[] players_name

/*
                cursor.getInt(0),// int history_id
                new int[cursor.getInt(3),cursor.getInt(9)], // int[] counter_100
                new int[cursor.getInt(4), cursor.getInt(10))],//int[] counter_160
                new int[cursor.getInt(5), cursor.getInt(11)],///int[] counter_180
                new double[cursor.getInt(4)],//double[] average_points
                new int[cursor.getInt(5)], //int[] highestThrow
                new String[cursor.getString(2)]);//String[] players_name*//*
        return history;
    }

    public List<GameHistory> getGameHistoryAll() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<GameHistory> historyList = new ArrayList<GameHistory>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GameHistory history = new GameHistory();
                history.setID_counter(cursor.getInt(0));
                history.setCounter_100(new int[cursor.getInt(1)]);
                history.setCounter_160(new int[cursor.getInt(2)]);
                history.setCounter_180(new int[cursor.getInt(3)]);
                history.setAverage_points(new double[cursor.getInt(4)]);
                history.setHighestThrow(new int[cursor.getInt(5)]);
                history.setPlayers_name(new String[Integer.parseInt(cursor.getString(6))]);
                // Adding note to list
                historyList.add(history);
            } while (cursor.moveToNext());
        }

        // return note list
        return historyList;
    }

   public GameHistory createDefaultNotesIfNeed()  {
            GameHistory note1 = new GameHistory(1, new int []{0,1}, new int []{1,2}, new int []{0,4}, new double[]{2.2, 1.5}, new int[]{151, 234}, new String []{"hh", "GG"});
            return note1;
    }
*/

    /*String LOG_TAG = DartDbHandler.class.getSimpleName();

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
    }*/


