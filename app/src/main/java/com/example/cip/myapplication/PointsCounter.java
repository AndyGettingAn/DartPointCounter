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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Arrays;

public class PointsCounter extends AppCompatActivity implements View.OnClickListener{

    private  static final String LOG_TAG = PointsCounter.class.getSimpleName();

    private final int finishModus = 170,
            numberOfPlayers = 2,
            player1 = 0,
            player2 = 1,
            dartsPerTurn = 3,
            firstThrow = 0,
            secondThrow = 1,
            thirdThrow = 2;
    //Array mit Länge 2: Feld 0: Spieler 1, Feld 1: Spieler 2
    private int[] gameState = new int[numberOfPlayers],
            sets = new int[numberOfPlayers],
            counter_100 = new int[numberOfPlayers],
            counter_160 = new int[numberOfPlayers],
            counter_180 = new int[numberOfPlayers],
            highestThrow = new int[numberOfPlayers];
    int startPoints,
            currentPlayer,
            currentThrow;
    private double[] average = new double[numberOfPlayers];
    private String [] playerNames = new String [numberOfPlayers];
    private TextView setsView[] = new TextView[numberOfPlayers],
            averageView[] = new TextView[numberOfPlayers],
            gameStateView[] = new TextView[numberOfPlayers],
            playerNamesView [] = new TextView[numberOfPlayers],
            inputPoints[] = new TextView[dartsPerTurn];
    private Button buttonFinish[] = new Button[numberOfPlayers];


    private PointCounterWidget counter= new PointCounterWidget();


    private Boolean dartboardRequired,
            gameVariant;

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
        updateWidget();
    }

    private void initialize() {
        inputPoints[firstThrow] = (TextView) findViewById(R.id.player_throw_points1);
        inputPoints[secondThrow] = (TextView) findViewById(R.id.player_throw_points2);
        inputPoints[thirdThrow] = (TextView) findViewById(R.id.player_throw_points3);
        setsView[player1] = (TextView) findViewById(R.id.player1Set);
        setsView[player2] = (TextView) findViewById(R.id.player2Set);
        averageView[player1] = (TextView) findViewById(R.id.player1Average);
        averageView[player2] = (TextView) findViewById(R.id.player2Average);
        buttonFinish[player1] = (Button) findViewById(R.id.player1Finish);
        buttonFinish[player2] = (Button) findViewById(R.id.player2Finish);
        gameStateView[player1] = (TextView) findViewById(R.id.player1GameState);
        gameStateView[player2] = (TextView) findViewById(R.id.player2GameState);
        playerNamesView[player1] = (TextView) findViewById(R.id.playerName1);
        playerNamesView[player2] = (TextView) findViewById(R.id.playerName2);
        buttonFinish[player1].setOnClickListener(this);
        buttonFinish[player2].setOnClickListener(this);
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
        playerNamesView[player1].setText(name1);
        playerNamesView[player2].setText(name2);
        playerNames[player1] = name1;
        playerNames[player2] = name2;

    }

    private void setDefaultValues() {
        if (gameVariant){
            startPoints = 501;
        }else {
            startPoints = 301;
        }
        for (int player = 0; player < numberOfPlayers; player++) {
            setsView[player].setText("Sets: 0");
            averageView[player].setText("Durchschnitt: 0");
            buttonFinish[player].setText("");
            gameStateView[player].setText(Integer.toString(startPoints));
            gameState[player] = startPoints;
            sets[player] = 0;
            average[player] = 0;
            // playerNames[player] = "";
        }
        for (int dart = 0; dart < dartsPerTurn; dart++) {
            inputPoints[dart].setText("");
        }
        currentPlayer = player1;
        currentThrow = firstThrow;
        playerNamesView[player1].setTextColor(Color.RED);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button0 || id == R.id.button1 || id == R.id.button2 || id == R.id.button3 || id == R.id.button4 || id == R.id.button5 || id == R.id.button6 || id == R.id.button7 || id == R.id.button8 || id == R.id.button9) {
            Button pressedButton = (Button) v;
            String buttonValue = pressedButton.getText().toString();
            updateInput(buttonValue);
        } else if (id == R.id.buttonOK) {
            if (currentThrow < dartsPerTurn-1) {
                currentThrow++;
            } else {
                updateGameState();
                clearInputAll();
            }
        } else if (id == R.id.buttonClear) {
            clearInput();
        } else if (id == R.id.player1Finish) {
            openPlayerDartboard(player1);
        } else if (id == R.id.player2Finish) {
            openPlayerDartboard(player2);
        }
    }

    private void updateInput(String inputValue) {
        String oldValue = inputPoints[currentThrow].getText().toString();
        String newValue = oldValue + inputValue;
        inputPoints[currentThrow].setText(newValue);
    }

    private void clearInputAll() {
        for (int thow = 0; thow < dartsPerTurn; thow++) {
            inputPoints[thow].setText("");
        }
    }

    private void clearInput(){
        inputPoints[currentThrow].setText("");
    }

    private void updateGameState(){
        //Spielstände werden nicht negativ -> Überworfen. Darts zählen trotzdem
        String inputValue1 = inputPoints[firstThrow].getText().toString();
        String inputValue2 = inputPoints[secondThrow].getText().toString();
        String inputValue3 = inputPoints[thirdThrow].getText().toString();
        if (inputValue1 == ""){inputValue1 = "0";}
        if (inputValue2 == ""){inputValue2 = "0";}
        if (inputValue3 == ""){inputValue3 = "0";}
        int inputPoints1 = Integer.parseInt(inputValue1);
        int inputPoints2 = Integer.parseInt(inputValue2);
        int inputPoints3 = Integer.parseInt(inputValue3);
        int sum = inputPoints1+inputPoints2+inputPoints3;
        checkValues(inputPoints1,inputPoints2,inputPoints3);
        if (gameState[currentPlayer]-sum >= 0) {
            gameState[currentPlayer] -= sum;
        }
        sets[currentPlayer]++;
        average[currentPlayer] = (startPoints - gameState[currentPlayer]) / (sets[currentPlayer] * dartsPerTurn);
        updateGameView();
        updateWidget();
        checkPlayerWin();
        playersChange();
    }

    private void checkValues(int input1,int input2,int input3){
        int[] inputs = new int[]{input1,input2,input3};
        for(int i = 0; i < inputs.length; i++){
            if(inputs[i] == 180){
                counter_180[currentPlayer]++;
            }else if(inputs[i] >= 160){
                counter_160[currentPlayer]++;
            }else if(inputs[i] >= 100){
                counter_100[currentPlayer]++;
            }
            if (inputs[i] > highestThrow[currentPlayer]){
                highestThrow[currentPlayer] = inputs[i];
            }
        }
    }

    private void updateGameView() {
        averageView[currentPlayer].setText("Durchschnitt: "+ average[currentPlayer]);
        setsView[currentPlayer].setText("Set: "+ sets[currentPlayer]);
        if (gameState[currentPlayer]<= finishModus && gameState[currentPlayer] != 0 && dartboardRequired){
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
            GameHistory gamehistory = new GameHistory(counter_100, counter_160, counter_180, average, highestThrow, playerNames);
            StatisticDbSource source = new StatisticDbSource(this);
            source.addHistory(gamehistory);
            Log.e(LOG_TAG, "DB erstellt!");
            startActivity(intent);
            finish();
        }
    }
    //Spielerwechsel
    private void playersChange() {
        currentPlayer = getOtherPlayer();
        playerNamesView[currentPlayer].setTextColor(Color.RED);
        playerNamesView[getOtherPlayer()].setTextColor(Color.BLACK);
        currentThrow = firstThrow;
    }

    private void openPlayerDartboard(int player){
        Intent intent = new Intent(PointsCounter.this, Dartboard.class);
        if (gameState[player]<= finishModus && FinishValues.getFinish(gameState[player])[0] != ""){
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
        if (currentPlayer == player1){
            return player2;
        }else {
            return player1;
        }
    }
}

