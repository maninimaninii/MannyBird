package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    GameView gameView;
    //MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DonneesApp.gameacContext= this; //on assigne le contexte de l'activitte
        DonneesApp.startmusc();
        gameView = new GameView(this);
        setContentView(gameView);

    }

    public void stop(){
        gameView.gameThread.stop();
    }
}