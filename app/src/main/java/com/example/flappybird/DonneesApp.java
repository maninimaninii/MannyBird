package com.example.flappybird;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DonneesApp { //Contient toutes les valeurs utilisées dans le jeu
        static BitmapBank bitmapBank; //Contient toutes les images qui seront utilisees

        static GameView gv;

        static GameEngine gameEngine; //Contien les fonctione qui gerent le fonctionnement du jeu

        static int SCREEN_WIDTH, SCREEN_HEIGHT; //Les constantes de taille de l'écran

        static int gravite; //gravite vitesse de chute

        static int vitessesaut; //vitesse gagnee grace a un saut

        static Context gameacContext;//contexte de l'activite ou se deroule le jeu
        static MediaPlayer mp; //musique de fond


        public static void initialisation(Context context){
            setScreenSize(context);
            bitmapBank = new BitmapBank(context.getResources());
            gameEngine = new GameEngine();
            DonneesApp.gravite = 3;
            DonneesApp.vitessesaut = -30;

        }

        public static BitmapBank  getBitmapBank(){
            return bitmapBank;
        }


        public static GameEngine getGameEngine(){
            return gameEngine;
        }

        public static void startmusc(){ //lancer la musique et la faire se repeter
            mp = MediaPlayer.create(gameacContext, R.raw.musique); mp.setLooping(true);
            mp.start();
        }
        public static void stopmusc(){ //arreter la musqieu a l'arret du jeu
            mp.stop();
        }

        private static void setScreenSize(Context context){ //permet de calibrer a l'écran actuel
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display dis = wm.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            dis.getMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;

            DonneesApp.SCREEN_WIDTH = width;
            DonneesApp.SCREEN_HEIGHT = height;
        }
}
