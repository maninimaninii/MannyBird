package com.example.flappybird;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Fin extends AppCompatActivity { //ecran de game Over

    TextView tscore;
    Button brejouer, bquitter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int score = getIntent().getExtras().getInt("score"); // on recupere le score
        brejouer = findViewById(R.id.button); // on lie nles variables aux boutons
        bquitter = findViewById(R.id.button2);
    
        tscore = findViewById(R.id.tvScore);
        tscore.setText("" + score); //on affiçche le score réalise=é recupere via l'intent

        brejouer.setOnClickListener(new View.OnClickListener() { // bouyton rejouer relance le jeu
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bquitter.setOnClickListener(new View.OnClickListener() { //btouon quitter quitte et ferme l'appli
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
