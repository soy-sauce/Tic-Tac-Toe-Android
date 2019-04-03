package com.example.connie.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //0=dog, 1=cat
    //note:dog goes first

    int activePlayer=0;
    int[] gameState ={2,2,2,2,2,2,2,2,2};
    int[][] winPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean activeGame=true;


    public void dropIn(View view){
        if(activeGame==true) {
            TextView turn=(TextView)findViewById(R.id.playTurn);

            Random rand = new Random();
            int n = rand.nextInt(140);
            n = n - 70;

            ImageView counter = (ImageView) view;

            int tappedCount = Integer.parseInt(counter.getTag().toString());
            if (gameState[tappedCount] == 2) {
                counter.setTranslationY(-1000f);

                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.dog);
                    turn.setText("Cat's Turn");
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.cat);
                    turn.setText("Dog's Turn");
                    activePlayer = 0;
                }
                counter.animate().translationYBy(1000f).rotation(n).setDuration(300);
                gameState[tappedCount] = activePlayer;

                for (int[] winPosition : winPos) {
                    if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                            gameState[winPosition[1]] == gameState[winPosition[2]] &&
                            gameState[winPosition[0]] != 2) {
                        turn.setText(" ");
                        //System.out.println(gameState[winPosition[0]]);
                        String winner = "Dog";
                        if (gameState[winPosition[0]] == 0) {
                            winner = "Cat";
                        }
                        TextView winnerMessage = (TextView) findViewById(R.id.winMessage);
                        winnerMessage.setText(winner + " has won!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLay);
                        layout.bringToFront();
                        layout.setVisibility(View.VISIBLE);
                        activeGame=false;
                    }
                    else if(activeGame){
                        boolean gameOver=true;
                        for(int countState:gameState){
                            if(countState==2){
                                gameOver=false;
                            }
                        }
                        if (gameOver){
                            turn.setText(" ");
                            String winner = "Dog";
                            TextView winnerMessage = (TextView) findViewById(R.id.winMessage);
                            winnerMessage.setText("Draw");
                            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLay);
                            layout.bringToFront();
                            layout.setVisibility(View.VISIBLE);
                            activeGame=false;
                        }
                    }
                }
            }
        }
    }


    public void playAgain(View view) {
        activeGame=true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLay);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLay);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
