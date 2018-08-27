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
            triple = 3;

    private int player1GameState,
            player2GameState,
            player1Sets,
            player2Sets;

    private double player1Average,
            player2Average;


    private TextView inputPoints,
            p1Set,
            p2Set,
            p1Average,
            p2Average,
            p1GameState,
            p2GameState;

    private Button p1Finish,
            p2Finish;

    //wer momentan spielt. True: player 1 spielt und gibt eine Werte ein
    private boolean isPlayer1Turn;


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
        p1Set = (TextView) findViewById(R.id.player1Set);
        p2Set = (TextView) findViewById(R.id.player2Set);
        p1Average = (TextView) findViewById(R.id.player1Average);
        p2Average = (TextView) findViewById(R.id.player2Average);
        p1Finish = (Button) findViewById(R.id.player1Finish);
        p2Finish = (Button) findViewById(R.id.player2Finish);
        p1GameState = (TextView) findViewById(R.id.player1GameState);
        p2GameState = (TextView) findViewById(R.id.player2GameState);
        p1Set.setText("Sets: 0");
        p2Set.setText("Sets: 0");
        p1Average.setText("Durchschnitt: 0");
        p2Average.setText("Durchschnitt: 0");
        p1Finish.setText("");
        p2Finish.setText("");
        p1GameState.setText(Integer.toString(startPoints));
        p2GameState.setText(Integer.toString(startPoints));
        
        isPlayer1Turn = true;
        player1GameState = startPoints;
        player2GameState = startPoints;
        player1Average = 0;
        player2Average = 0;
        player1Sets = 0;
        player2Sets = 0;
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
        if (isPlayer1Turn){
            player1GameState -= inputPoints;
            player1Sets++;
            //pro Set 3 Darts -> Anzahl der Setz mal 3 um den Durchschnitt pro Dart zu erhalten
            player1Average = (startPoints-player1GameState)/(player1Sets*3);
        }else{
            player2GameState -= inputPoints;
            player2Sets++;
            player2Average = (startPoints-player1GameState)/(player2Sets*3);
        }
        updateGameView();
        //Spielerwechsel
       isPlayer1Turn = !isPlayer1Turn;
    }

    private void updateGameView() {
        if (isPlayer1Turn){
            p1Set.setText("Sets: " + player1Sets);
            p1Average.setText("Durchschnitt: "+ player1Average);
            p1Finish.setText("Finish??");
            p1GameState.setText(String.valueOf(player1GameState));
        }else{
            p2Set.setText("Sets: " + player2Sets);
            p2Average.setText("Durchschnitt: "+ player2Average);
            p2Finish.setText("Finish??");
            p2GameState.setText(String.valueOf(player2GameState));
        }
    }
}