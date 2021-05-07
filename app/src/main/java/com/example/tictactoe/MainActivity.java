package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0-X
    //1-O
    //2-Blank
    int activePlayer=0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winPos = {{0,1,2},{3,4,5},{6,7,8},
                      {0,3,6},{1,4,7},{2,5,8},
                      {0,4,8},{2,4,6}};
    boolean gameActive = true;
    public void playertap(View view){
        ImageView img = (ImageView) view;
        String winnerStr;
        TextView status = findViewById(R.id.status);
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage]==2){
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                status.setText("O's turn, tap to play!");
            }
            else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                status.setText("X's turn, tap to play!");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for (int[] winPosition:winPos){
            if(gameState[winPosition[0]]==gameState[winPosition[1]]&&gameState[winPosition[1]]==gameState[winPosition[2]]&&gameState[winPosition[0]]!=2)
            {//somebody has won
                if(gameState[winPosition[0]]==0){
                    winnerStr = "X has won";
                }
                else
                    winnerStr = "O has won";
                //update the status bar for winner announcement
                status.setText(winnerStr);
                gameActive=false;
            }
        }

    }
    public void gameReset(View view){
        gameActive = true;
        TextView status = findViewById(R.id.status);
        activePlayer=0;
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        status.setText("X's turn, tap to play!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}