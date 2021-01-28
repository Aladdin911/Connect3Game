package com.myappcompany.rob.connect3game;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty
    int count =0;
    int red_win_count=0;
    int yello_win_count=0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view) {

        //

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        Button reset_Button=(Button)findViewById(R.id.rest_score);
        TextView red_result = (TextView) findViewById(R.id.red_result);
        TextView yellow_result = (TextView) findViewById(R.id.yellow_reslt);

        //
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            count++;

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(100);
            boolean their_winner=false;
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Somone has won!

                    gameActive = false;
                    String winner = "";

                    if (activePlayer == 1) {
                        yello_win_count++;
                        winner = "Yellow";
                        their_winner=true;
                    } else {
                        red_win_count++;
                        winner = "Red";
                        their_winner=true;
                    }


                    yellow_result.setText("Yellow\n"+yello_win_count);
                    red_result.setText("Red\n"+red_win_count);
                    winnerTextView.setText(winner + " has won!");
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                  ///
                    red_result.setVisibility(View.VISIBLE);
                    yellow_result.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    reset_Button.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);


                }

            if(their_winner)
            {
                break;
            }
            }
            if(count==9&&gameActive==true)
            {

                winnerTextView.setText("Draw!");
                Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();

                //
                red_result.setVisibility(View.VISIBLE);
                yellow_result.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                reset_Button.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
                //
            }
        }
    }

    public void playAgain(View view) {
        //
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        Button reset_Button = (Button) findViewById(R.id.rest_score);
        //
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        TextView red_result = (TextView) findViewById(R.id.red_result);
        TextView yellow_result = (TextView) findViewById(R.id.yellow_reslt);
        //
        red_result.setVisibility(View.INVISIBLE);
        yellow_result.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        reset_Button.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }
        count=0;
        activePlayer = 0;

        gameActive = true;

    }
    public void reset_result(View view) {
    red_win_count=0;
    yello_win_count=0;
        TextView red_result = (TextView) findViewById(R.id.red_result);
        TextView yellow_result = (TextView) findViewById(R.id.yellow_reslt);
        yellow_result.setText("Yellow\n"+yello_win_count);
        red_result.setText("Red\n"+red_win_count);
        playAgain(null);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
