package com.sygic.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int activePlayer = 0;
    private int gameState[] = {2,2,2,2,2,2,2,2,2};
    private int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {3,5,8}, {0,4,8}, {2,4,6}};
    private boolean gameActive = true;
    private int moveTracker = 0;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        TextView winMessage = (TextView) findViewById(R.id.text_winner);
        Button playAgain = (Button) findViewById(R.id.btn_playAgain);
        int taggedPosition = Integer.parseInt(counter.getTag().toString());
        String message;

        if(gameState[taggedPosition] == 2 && gameActive) {
            counter.setTranslationY(-1500);
            gameState[taggedPosition] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);
            moveTracker++;

            for(int[] winningPosition : winningPositions){
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    if(activePlayer == 1){
                        message = "Red has won!";
                    } else {
                        message = "Yellow has won!";
                    }
                    winMessage.setText(message);
                    winMessage.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }

                if(moveTracker == 9 && gameActive){
                    gameActive = false;
                    message = "Nobody has won!";
                    winMessage.setText(message);
                    winMessage.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        TextView winMessage = (TextView) findViewById(R.id.text_winner);
        Button playAgain = (Button) findViewById(R.id.btn_playAgain);
        winMessage.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        gameActive = true;
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount();i++){
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }

        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}