package com.example.cip.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PointsCounter extends AppCompatActivity implements View.OnClickListener {

    private final int startPoints = 501,
            finishModus = 170,
            dartsPerTurn = 3,
            single = 1,
            doubles = 2,
            triple = 3,
            numberOfPlayers = 2,
            player1 = 0,
            player2 = 1;


    //Array mit Länge 2: Feld 0: Spieler 1, Feld 1: Spieler 2
    private int [] gameState = new int [numberOfPlayers],
            sets = new int [numberOfPlayers];

    int currentPlayer;

    private double [] average = new double [numberOfPlayers];


    private TextView inputPoints;

    private  TextView setsView [] = new TextView[numberOfPlayers],
            averageView [] = new TextView[numberOfPlayers],
            gameStateView [] = new TextView[numberOfPlayers];

    private Button buttonFinish [] = new Button[numberOfPlayers];
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_counter);
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
        findViewById(R.id.buttonOK).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        inputPoints = (TextView) findViewById(R.id.player_throw_points);
        setsView[player1] = (TextView) findViewById(R.id.player1Set);
        setsView[player2] = (TextView) findViewById(R.id.player2Set);
        averageView[player1] = (TextView) findViewById(R.id.player1Average);
        averageView[player2] = (TextView) findViewById(R.id.player2Average);
        buttonFinish[player1] = (Button) findViewById(R.id.player1Finish);
        buttonFinish [player2] = (Button) findViewById(R.id.player2Finish);
        gameStateView[player1] = (TextView) findViewById(R.id.player1GameState);
        gameStateView[player2] = (TextView) findViewById(R.id.player2GameState);
        setPlayerNames("Andi", "Hami");
        setDefaultValues();

    }

    private void setPlayerNames(String player1, String player2) {
        TextView name1 = (TextView) findViewById(R.id.playerName1);
        TextView name2 = (TextView) findViewById(R.id.playerName2);
        name1.setText(player1);
        name2.setText(player2);
    }

    private void setDefaultValues() {
        for (int player = 0; player < numberOfPlayers; player++){
            //view
            setsView[player].setText("Sets: 0");
            averageView[player].setText("Durchschnitt: 0");
            buttonFinish[player].setText("");
            gameStateView[player].setText(Integer.toString(startPoints));
            //values
            gameState[player] = startPoints;
            sets[player] = 0;
            average[player] = 0;
        }
        currentPlayer = player1;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button0 || id == R.id.button1 || id == R.id.button2 || id == R.id.button3 || id == R.id.button4 || id == R.id.button5 || id == R.id.button6 || id == R.id.button7 || id == R.id.button8 || id == R.id.button9){
            Button pressedButton = (Button)v;
            String buttonValue = pressedButton.getText().toString();
            updateInput(buttonValue);
        }else if(id == R.id.buttonOK){
            updateGameState();
        }else if (id == R.id.buttonClear){
            clearInput();
        }
    }

    private void updateInput (String inputValue){
        String oldValue = inputPoints.getText().toString();
        String newValue = oldValue + inputValue;
        inputPoints.setText(newValue);
    }

    private void clearInput(){
        inputPoints.setText("");
    }

    private void updateGameState(){
        String inputValue = inputPoints.getText().toString();
        int inputPoints = Integer.parseInt(inputValue);
        gameState[currentPlayer] -= inputPoints;
        sets[currentPlayer]++;
        average[currentPlayer] = (startPoints-gameState[currentPlayer])/(sets[currentPlayer]*dartsPerTurn);
        updateGameView();
        //Spielerwechsel
        if (currentPlayer == player1){
            currentPlayer = player2;
        }else{
            currentPlayer = player1;
        }
    }

    private void updateGameView() {
        buttonFinish[currentPlayer].setText("Finish??");
        averageView[currentPlayer].setText("Durchschnitt: "+ average[currentPlayer]);
        setsView[currentPlayer].setText("Set: "+ sets[currentPlayer]);
        buttonFinish[currentPlayer].setText("Finish??");
        gameStateView[currentPlayer].setText(String.valueOf(gameState[currentPlayer]));
    }
}