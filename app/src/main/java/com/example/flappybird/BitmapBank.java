package com.example.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank { //class faisant office de stocck d'images

    Bitmap background_game;  //bitmap contenant l'arriere plan pendant le jeu
    Bitmap[] wazos;  // tableau bitmap contenant les frames d'oiseau
    Bitmap[] tuyaux;
    Bitmap[] poulets;  // tableau bitmap contenant les frames d'oiseau
    Bitmap[] score;

    public BitmapBank(Resources resources){
        background_game = BitmapFactory.decodeResource(resources, R.drawable.backgroundgame); //on assigne a la variable l'image correspondate
        background_game = scaleImage(background_game);

        wazos = new Bitmap[12]; 
        wazos[0] = BitmapFactory.decodeResource(resources, R.drawable.lwazo1);
        wazos[1] = BitmapFactory.decodeResource(resources, R.drawable.lwazo2);
        wazos[2] = BitmapFactory.decodeResource(resources, R.drawable.lwazo3);
        wazos[3] = BitmapFactory.decodeResource(resources, R.drawable.lwazo2);
        wazos[4] = BitmapFactory.decodeResource(resources, R.drawable.lwazobleu1);
        wazos[5] = BitmapFactory.decodeResource(resources, R.drawable.lwazobleu2);
        wazos[6] = BitmapFactory.decodeResource(resources, R.drawable.lwazobleu3);
        wazos[7] = BitmapFactory.decodeResource(resources, R.drawable.lwazobleu2);
        wazos[8] = BitmapFactory.decodeResource(resources, R.drawable.lwazorouj1);
        wazos[9] = BitmapFactory.decodeResource(resources, R.drawable.lwazorouj2);
        wazos[10] = BitmapFactory.decodeResource(resources, R.drawable.lwazorouj3);
        wazos[11] = BitmapFactory.decodeResource(resources, R.drawable.lwazorouj2);

        poulets = new Bitmap[2];
        poulets[0] = BitmapFactory.decodeResource(resources, R.drawable.poulet1);
        poulets[1] = BitmapFactory.decodeResource(resources, R.drawable.poulet2);

        tuyaux = new Bitmap[6];

        tuyaux[0] = BitmapFactory.decodeResource(resources,R.drawable.pipe1) ;
        tuyaux[0] = scalepipe0(tuyaux[0]); // on redimennsionne selon la taille de l'ecran
        tuyaux[1] = BitmapFactory.decodeResource(resources,R.drawable.pipe2) ;
        tuyaux[1] = scalepipe1(tuyaux[1]);

        score = new Bitmap[10]; //score affiché via bitmaps, donc tableau correspondant
        score[0] = BitmapFactory.decodeResource(resources, R.drawable.zero);
        score[1] = BitmapFactory.decodeResource(resources, R.drawable.un);
        score[2] = BitmapFactory.decodeResource(resources, R.drawable.deux);
        score[3] = BitmapFactory.decodeResource(resources, R.drawable.trois);
        score[4] = BitmapFactory.decodeResource(resources, R.drawable.quatre);
        score[5] = BitmapFactory.decodeResource(resources, R.drawable.cinq);
        score[6] = BitmapFactory.decodeResource(resources, R.drawable.six);
        score[7] = BitmapFactory.decodeResource(resources, R.drawable.sept);
        score[8] = BitmapFactory.decodeResource(resources, R.drawable.huit);
        score[9] = BitmapFactory.decodeResource(resources, R.drawable.neuf);

    }




    public Bitmap getBird(int frame){
        return wazos[frame];
    } //renvoie l'image affichee acctuellementt

    public Bitmap getPoulet(int frame){
        return poulets[frame];
    } //renvoie l'image affichee acctuellementt

    public Bitmap getTuyo(int f){return tuyaux[f];}  //retourne le tuyau actuel

    public int getwazoWidth(){
        return wazos[0].getWidth();
    } //retourne la largeur de l'image de l'oiseau

    public int getwazoHeight(){
        return wazos[0].getHeight() / 3;
    } //retourne la hauteur (div 3 car trop grandd)

    public int gettuyoWidth(){return tuyaux[0].getWidth() ;} //retourne la largeur du tuyau


    public Bitmap getscore(int score){ //permet de retourner l'image correspondante au score actuel
        return this.score[score];
    }

    public int gettuyoHeigth(){return tuyaux[0].getHeight();}


    //on renvoie le bitmap de l'arriere plan
    public Bitmap getBackground(){
        return background_game;
    }


    public int getBackgroundWidth(){
        return background_game.getWidth();
    }

    public int getBackgroundHeight(){
        return background_game.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){ //permet de readapter les dimensions a celle de l'ecran, puis de prendre les autres bitmap en modele pour redimensionner
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();

        int backgroundScaleWIdth = (int) widthHeightRatio * DonneesApp.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaleWIdth, DonneesApp.SCREEN_HEIGHT, false);
    }

   public Bitmap scalepipe0(Bitmap bm){ 

        return Bitmap.createScaledBitmap(bm, gettuyoWidth() / 4, gettuyoHeigth() / 2 - 200, true);
    }
    public Bitmap scalepipe1(Bitmap bm){ //deuxieme fonction car sinon les valeurs etaient remodifiees a chaque appel de la premiere

        return Bitmap.createScaledBitmap(bm, gettuyoWidth(), gettuyoHeigth() , true);
    }
}
