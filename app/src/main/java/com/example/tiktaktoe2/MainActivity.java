package com.example.tiktaktoe2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new  Button[3][3];
    Button home,exit;

    private Boolean player1Turn = true;

    private int roundCount;

    private  int player1Point;
    private  int player2Point;

    public String rec1;
    public String rec2;
    public TextView textViewPlayer1;
    public TextView textViewPlayer2;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rec1=getIntent().getStringExtra("p1");
        rec2=getIntent().getStringExtra("p2");

        textViewPlayer1 = findViewById(R.id.tv_p1);
        textViewPlayer2 = findViewById(R.id.tv_p2);
        textViewPlayer1.setText(rec1+" : 0");
        textViewPlayer2.setText(rec2+" : 0");



        for (int i = 0; i < 3; i++)  /* this loop is for view binding of buttons */
        {
            for (int j = 0; j < 3; j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.btn_reset);
        buttonReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

        home=findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent j=new Intent(getApplicationContext(),start_page.class);
                startActivity(j);
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) /* checking buttons are empty or not*/
        {
            return;
        }

        if (player1Turn)
        {
            ((Button) view).setText("❌");
        }
        else
            {
            ((Button) view).setText("⭕");
            }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1TWins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin(){
        String[][] field = new  String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return  true;
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return  true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return  true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return  true;
        }

        return false;
    }

    private void player1TWins() {
        player1Point++;
        Toast.makeText(this,rec1+" Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void  player2Wins() {
        player2Point++;
        Toast.makeText(this,rec2+" Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText(rec1+" : " + player1Point);
        textViewPlayer2.setText(rec2+" : " + player2Point);
    }

    private void  resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Point = 0;
        player2Point = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1point", player1Point);
        outState.putInt("player2point", player2Point);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Point = savedInstanceState.getInt("player1Points");
        player2Point = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }

}


