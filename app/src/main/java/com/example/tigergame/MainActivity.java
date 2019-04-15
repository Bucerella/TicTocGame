package com.example.tigergame;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, TWO, NO
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerChoise[0] = Player.NO;
        playerChoise[1] = Player.NO;
        playerChoise[2] = Player.NO;
        playerChoise[3] = Player.NO;
        playerChoise[4] = Player.NO;
        playerChoise[5] = Player.NO;
        playerChoise[6] = Player.NO;
        playerChoise[7] = Player.NO;
        playerChoise[8] = Player.NO;

        btnReset = findViewById(R.id.butonReset);
        grid = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void resetGame() {
        for(int i = 0 ; i <grid.getChildCount(); i++){
            ImageView imageView = (ImageView) grid.getChildAt(i);
            imageView.setImageDrawable(null);
            playerChoise[i]=Player.NO;
            imageView.setAlpha(0f);
        }
        gameOver = false;
        currentPlayer = Player.ONE;


    }



    Player currentPlayer = Player.ONE;
    Player[] playerChoise = new Player[9];
    int[][] winnerRow = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private Boolean gameOver = false;
    private Button btnReset;
    private GridLayout grid;

    public void imageTapped(View imageView) {
        ImageView tapImage = (ImageView) imageView;
        int tiTag = Integer.parseInt(tapImage.getTag().toString());

        if(playerChoise[tiTag] == Player.NO && gameOver == false){


        tapImage.setTranslationX(-1000);




        playerChoise[tiTag] = currentPlayer;


        if (currentPlayer == Player.ONE) {
            tapImage.setImageResource(R.drawable.lion);
            currentPlayer = Player.TWO;
        } else if (currentPlayer == Player.TWO) {
            tapImage.setImageResource(R.drawable.tiger);
            currentPlayer = Player.ONE;
        }
        tapImage.animate().translationXBy(1000).alpha(1).rotation(720).setDuration(1000);

        for (int[] winnerColums : winnerRow) {
            if (playerChoise[winnerColums[0]] == playerChoise[winnerColums[1]] && playerChoise[winnerColums[1]] ==
                    playerChoise[winnerColums[2]] && playerChoise[winnerColums[0]] != Player.NO){
                Toast.makeText(this,"We have winner", Toast.LENGTH_LONG).show();

                btnReset.setVisibility(View.VISIBLE);
                gameOver = true;
                String user = "";
                if(currentPlayer == Player.ONE){
                    user = "Player Two";
                }else{
                    user = "Player One";
                }
                Toast.makeText(this,user + " is winner", Toast.LENGTH_LONG).show();

            }
        }

    }

    }


}
