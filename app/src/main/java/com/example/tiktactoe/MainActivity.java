package com.example.tiktactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    //0=x;
    //1=o;
    //2=null;
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0, 1, 2,}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void playerTab(View view) {
        ImageView img = (ImageView) view;
        //created imgage view and taking tag of image

        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;

                TextView status = findViewById(R.id.status);
                status.setText("O 's Turn -Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X 's Turn -Tap to play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }

        // check if any player has won

//        for(int[] winpositon :winPositions){
//
//            if(gameState[winpositon[0]]==gameState[winpositon[1]] &&
//                    gameState[winpositon[1]]==gameState[winpositon[2]] &&
//                        gameState[winpositon[0]]!=2){

        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {

                //Someone is won lets find out Who
                String winnerStr;
                gameActive = false;

                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has Won";

                } else {
                    winnerStr = "O has Won";
                }

                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);


            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.restart_id:
                Toast.makeText(this, "Restart game", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}