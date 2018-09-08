package com.example.cip.myapplication;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class PointsCounter extends AppCompatActivity implements View.OnClickListener{

    private final int FINISH_MODUS = 170,
            NUMBER_OF_PLAYERS = 2,
            PLAYER_1 = 0,
            PLAYER_2 = 1,
            DARTS_PER_TURN = 3,
            FIRST_THROW = 0,
            SECOND_THROW = 1,
            THIRD_THROW = 2;
    private int[] gameState = new int[NUMBER_OF_PLAYERS],
            sets = new int[NUMBER_OF_PLAYERS],
            counter_100 = new int[NUMBER_OF_PLAYERS],
            counter_160 = new int[NUMBER_OF_PLAYERS],
            counter_180 = new int[NUMBER_OF_PLAYERS],
            highestThrow = new int[NUMBER_OF_PLAYERS];
    private int startPoints, currentPlayer, currentThrow;
    private double[] average = new double[NUMBER_OF_PLAYERS];
    private String [] playerNames = new String [NUMBER_OF_PLAYERS];
    private TextView setsView[] = new TextView[NUMBER_OF_PLAYERS],
            averageView[] = new TextView[NUMBER_OF_PLAYERS],
            gameStateView[] = new TextView[NUMBER_OF_PLAYERS],
            playerNamesView [] = new TextView[NUMBER_OF_PLAYERS],
            inputPoints[] = new TextView[DARTS_PER_TURN];
    private Button buttonFinish[] = new Button[NUMBER_OF_PLAYERS];
    private Boolean dartboardRequired,
            gameVariant;
    private final PointCounterWidget counter= new PointCounterWidget();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_counter);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        dartboardRequired = sharedPref.getBoolean(Settings.KEY_DARTBOARD, false);
        String p1Name = sharedPref.getString(Settings.KEY_PLAYER1_NAME, "Spieler 1");
        String p2Name = sharedPref.getString(Settings.KEY_PLAYER2_NAME, "Spieler 2");
        gameVariant = sharedPref.getBoolean(Settings.KEY_GAME_VARIANT, false);
        initialize();
        setDefaultValues();
        setPlayerNames(p1Name, p2Name);
    }

    private void initialize() {
        inputPoints[FIRST_THROW] = (TextView) findViewById(R.id.player_throw_points1);
        inputPoints[SECOND_THROW] = (TextView) findViewById(R.id.player_throw_points2);
        inputPoints[THIRD_THROW] = (TextView) findViewById(R.id.player_throw_points3);
        setsView[PLAYER_1] = (TextView) findViewById(R.id.player1Set);
        setsView[PLAYER_2] = (TextView) findViewById(R.id.player2Set);
        averageView[PLAYER_1] = (TextView) findViewById(R.id.player1Average);
        averageView[PLAYER_2] = (TextView) findViewById(R.id.player2Average);
        buttonFinish[PLAYER_1] = (Button) findViewById(R.id.player1Finish);
        buttonFinish[PLAYER_2] = (Button) findViewById(R.id.player2Finish);
        gameStateView[PLAYER_1] = (TextView) findViewById(R.id.player1GameState);
        gameStateView[PLAYER_2] = (TextView) findViewById(R.id.player2GameState);
        playerNamesView[PLAYER_1] = (TextView) findViewById(R.id.playerName1);
        playerNamesView[PLAYER_2] = (TextView) findViewById(R.id.playerName2);
        buttonFinish[PLAYER_1].setOnClickListener(this);
        buttonFinish[PLAYER_2].setOnClickListener(this);
        findViewById(R.id.buttonOK).setOnClickListener(this);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
    }

    private void setPlayerNames(String name1, String name2) {
        playerNamesView[PLAYER_1].setText(name1);
        playerNamesView[PLAYER_2].setText(name2);
        playerNames[PLAYER_1] = name1;
        playerNames[PLAYER_2] = name2;
    }

    private void setDefaultValues() {
        if (gameVariant){
            startPoints = 501;
        }else {
            startPoints = 301;
        }
        for (int player = 0; player < NUMBER_OF_PLAYERS; player++) {
            setsView[player].setText("Sets: 0");
            averageView[player].setText("Durchschnitt: 0");
            buttonFinish[player].setText("");
            gameStateView[player].setText(Integer.toString(startPoints));
            gameState[player] = startPoints;
            sets[player] = 0;
            average[player] = 0;
        }
        for (int dart = 0; dart < DARTS_PER_TURN; dart++) {
            inputPoints[dart].setText("");
        }
        currentPlayer = PLAYER_1;
        currentThrow = FIRST_THROW;
        playerNamesView[PLAYER_1].setTextColor(Color.RED);
        setWidgetValues();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button0 || id == R.id.button1 || id == R.id.button2 || id == R.id.button3 || id == R.id.button4 || id == R.id.button5 || id == R.id.button6 || id == R.id.button7 || id == R.id.button8 || id == R.id.button9) {
            Button pressedButton = (Button) v;
            String buttonValue = pressedButton.getText().toString();
            updateInput(buttonValue);
        } else if (id == R.id.buttonOK) {
            if (currentThrow < DARTS_PER_TURN-1) {
                currentThrow++;
            } else {
                updateGameState();
                clearInputAll();
            }
        } else if (id == R.id.buttonClear) {
            clearInput();
        } else if (id == R.id.player1Finish) {
            openPlayerDartboard(PLAYER_1);
        } else if (id == R.id.player2Finish) {
            openPlayerDartboard(PLAYER_2);
        }
    }

    private void updateInput(String inputValue) {
        String oldValue = inputPoints[currentThrow].getText().toString();
        String newValue = oldValue + inputValue;
        int maxDartpointsLength = 2;
        if (newValue.length() <= maxDartpointsLength) {
            inputPoints[currentThrow].setText(newValue);
        }
    }

    private void clearInputAll() {
        for (int dart = 0; dart < DARTS_PER_TURN; dart++) {
            inputPoints[dart].setText("");
        }
    }

    private void clearInput(){
        inputPoints[currentThrow].setText("");
    }

    private void updateGameState(){
        String inputValue1 = inputPoints[FIRST_THROW].getText().toString();
        String inputValue2 = inputPoints[SECOND_THROW].getText().toString();
        String inputValue3 = inputPoints[THIRD_THROW].getText().toString();
        int inputPoints1 = getInputPoints(inputValue1, "1");
        int inputPoints2 = getInputPoints(inputValue2, "2");
        int inputPoints3 = getInputPoints(inputValue3, "3");
        int sum = inputPoints1+inputPoints2+inputPoints3;
        checkValues(inputPoints1,inputPoints2,inputPoints3);
        if (gameState[currentPlayer]-sum >= 0) {
            gameState[currentPlayer] -= sum;
        }else {
            Toast.makeText(PointsCounter.this, "Überworfen", Toast.LENGTH_LONG).show();
        }
        sets[currentPlayer]++;
        average[currentPlayer] = (startPoints - gameState[currentPlayer]) / (sets[currentPlayer] * DARTS_PER_TURN);
        updateGameView();
        updateWidget();
        checkPlayerWin();
        playersChange();
    }

    private int getInputPoints(String pointsValue, String inputDart) {
        if (pointsValue == ""){
            return 0;
        }else {
            int points = Integer.parseInt(pointsValue);
            int maxDartpoints = 60;
            if (points > maxDartpoints) {
                Toast.makeText(PointsCounter.this, "Dart " + inputDart + " hat die maximale erreichbare Dart-Punktzahl überschritten", Toast.LENGTH_LONG).show();
                return 0;
            } else {
                return points;
            }
        }
    }

    private void checkValues(int input1,int input2,int input3){
        int[] inputs = new int[]{input1,input2,input3};
        for (int input : inputs) {
            if (input == 180) {
                counter_180[currentPlayer]++;
            } else if (input >= 160) {
                counter_160[currentPlayer]++;
            } else if (input >= 100) {
                counter_100[currentPlayer]++;
            }
            if (input > highestThrow[currentPlayer]) {
                highestThrow[currentPlayer] = input;
            }
        }
    }

    private void updateGameView() {
        averageView[currentPlayer].setText("Durchschnitt: "+ average[currentPlayer]);
        setsView[currentPlayer].setText("Set: "+ sets[currentPlayer]);
        if (gameState[currentPlayer]<= FINISH_MODUS && gameState[currentPlayer] != 0 && dartboardRequired){
            openPlayerDartboard(currentPlayer);
        }
        gameStateView[currentPlayer].setText(String.valueOf(gameState[currentPlayer]));
    }

    private void updateWidget() {
        setWidgetValues();
        Context context = getApplicationContext();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), PointCounterWidget.class));
        counter.onUpdate(this,appWidgetManager,ids);
    }

    private void checkPlayerWin() {
        if (gameState[currentPlayer] == 0){
            Intent intent = new Intent(PointsCounter.this, GameEnd.class);
            intent.putExtra("winnerName", playerNames[currentPlayer]);
            intent.putExtra("loserName", playerNames[getOtherPlayer()]);
            intent.putExtra("set", sets[currentPlayer]);
            setGameHistory();
            startActivity(intent);
            finish();
        }
    }

    private void playersChange() {
        currentPlayer = getOtherPlayer();
        playerNamesView[currentPlayer].setTextColor(Color.RED);
        playerNamesView[getOtherPlayer()].setTextColor(Color.BLACK);
        currentThrow = FIRST_THROW;
    }

    private void openPlayerDartboard(int player){
        Intent intent = new Intent(PointsCounter.this, Dartboard.class);
        if (gameState[player]<= FINISH_MODUS && FinishValues.getFinish(gameState[player])[0] != ""){
            String [] finishValues = FinishValues.getFinish(gameState[player]);
            buttonFinish[player].setText(Arrays.toString(finishValues));
            intent.putExtra("finishValues", finishValues);
        }
        startActivity(intent);
    }

    private void setWidgetValues(){
        counter.setGameState(gameState);
        counter.setSets(sets);
        counter.setPlayerNames(playerNames);
    }

    private int getOtherPlayer(){
        if (currentPlayer == PLAYER_1){
            return PLAYER_2;
        }else {
            return PLAYER_1;
        }
    }

    private void setGameHistory(){
        DartDbHandler db = new DartDbHandler(this);
        String title = playerNames[PLAYER_1] + " gegen " + playerNames[PLAYER_2] + "\n  am "  + getDate();
        String content = playerNames[0] + "/" + counter_100[0] + "/" + counter_160[0] + "/" + counter_180[0] + "/" + average[0] + "/" + highestThrow[0] +
                "/" + playerNames[1] + "/" + counter_100[1] + "/" + counter_160[1] + "/" + counter_180[1] + "/" + average[1] + "/" + highestThrow[1] + "/" + currentPlayer;
        GameHistory history =  new GameHistory(title,content);
        db.addNote(history);
    }

    private String getDate(){
       return new SimpleDateFormat("dd.MM.yyyy 'um' HH:mm:ss", Locale.GERMANY).format(new Date());
    }
}

