package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {// ecran d acceuil

    ImageView playb;  //bouton de lancement

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DonneesApp.initialisation(this.getApplicationContext()); // initialisation
        playb = findViewById(R.id.play); //onb relie la variable au bouton

        playb.setOnClickListener(new View.OnClickListener() { //lancement du jeu en appuyant sur play
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent); //lancement fde l'activite de jeu
                finish(); // on arrete l'activite d'acceuil
            }
        });


    }
}