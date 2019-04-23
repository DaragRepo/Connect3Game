package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


//    GridLayout grid;

//    int i ;

    // <red,0> , <yellow,1> <empty,2>


    private int [] gameStates = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    private final static int [][] winningStates = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private int activePlayer = 0;

    boolean gameActive = true;

    
    Button startAgain;

    String theWinner = "";



    TextView winOrDrawView;


    private int boardIsFul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winOrDrawView = (TextView) findViewById(R.id.textView);
        startAgain = (Button) findViewById(R.id.startAgain);


    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;


        int tag = Integer.parseInt(counter.getTag().toString());


        if (gameStates[tag] == 2 && gameActive ) {

            gameStates[tag] = activePlayer;

            boardIsFul++;

            System.out.println(boardIsFul);

            for (int[] winningState : winningStates) {

                if (gameStates[winningState[0]] == gameStates[winningState[1]]
                        && gameStates[winningState[1]] == gameStates[winningState[2]]
                        && gameStates[winningState[2]] != 2) {
                   
                    if (activePlayer == 0) {
                        theWinner += "Red";
                    } else {
                        theWinner += "Yellow";
                    }

                    wonOrDraw(true);

                    gameActive = false;

                    View line ;

                    if (winningState[0] == 0 && winningState[1] == 1 && winningState[2] == 2) {
                        line = (View) findViewById(R.id.firstLine);

                        line.setVisibility(View.VISIBLE);
                    } else if (winningState[0] == 3 && winningState[1] == 4 && winningState[2] == 5) {
                        line = (View) findViewById(R.id.secondLine);

                        line.setVisibility(View.VISIBLE);
                    } else if (winningState[0] == 6 && winningState[1] == 7 && winningState[2] == 8) {
                        line = (View) findViewById(R.id.thirdLine);

                        line.setVisibility(View.VISIBLE);
                    } else if (winningState[0] == 2 && winningState[1] == 4 && winningState[2] == 6) {
                        line = (View) findViewById(R.id.fourthLine);

                        line.setVisibility(View.VISIBLE);
                    } else if (winningState[0] == 0 && winningState[1] == 4 && winningState[2] == 8) {
                        line = (View) findViewById(R.id.fifthLine);

                        line.setVisibility(View.VISIBLE);
                    } else if (winningState[0] == 0 && winningState[1] == 3 && winningState[2] == 6) {
                        line = (View) findViewById(R.id.sixthLine);

                        line.setVisibility(View.VISIBLE);
                    } else if (winningState[0] == 1 && winningState[1] == 4 && winningState[2] == 7) {
                        line = (View) findViewById(R.id.seventhLine);

                        line.setVisibility(View.VISIBLE);
                    }  else if (winningState[0] == 2 && winningState[1] == 5 && winningState[2] == 8) {
                        line = (View) findViewById(R.id.eighthLine);

                        line.setVisibility(View.VISIBLE);
                    }

                }

                if (boardIsFul == 9 && gameActive == true) {
                    wonOrDraw(false);
                }


            }

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            counter.setTranslationY(-1500);

            counter.animate().translationYBy(1500).rotationBy(3600).setDuration(300);
        }


    }

    public void resetGame(View view) {
        recreate();
    }
    
    
    public void wonOrDraw(boolean won) {
        if (won) {
            winOrDrawView.setText(theWinner + " has won");
            winOrDrawView.setVisibility(View.VISIBLE);
            startAgain.setVisibility(View.VISIBLE);
        } else {
            winOrDrawView.setText("Draw");
            winOrDrawView.setVisibility(View.VISIBLE);
            startAgain.setVisibility(View.VISIBLE);
        }
    }
}
