package com.example.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Tuyau {
    private int tuyauX, tuyauY;
    public static int vitesse;
    public Rect rect;
    Bitmap bm; // contrairement à l'oiseau, plusieurs tuyaux sont créés, nous avons donc décidé de placer le Bitmap à l'interieur de la classe


    public Tuyau(){
        tuyauX = DonneesApp.SCREEN_WIDTH / 2 - DonneesApp.getBitmapBank().getwazoWidth() / 2;
        tuyauY = DonneesApp.SCREEN_HEIGHT/2 + DonneesApp.getBitmapBank().getwazoHeight() * 2;
        vitesse = 10 * DonneesApp.SCREEN_WIDTH/1000;
    }

    public Tuyau(int x, int y){
        this.tuyauX = x;
        this.tuyauY = y;
        vitesse = 10 * DonneesApp.SCREEN_WIDTH/1000;
        //rect = new Rect(tuyauX, tuyauY, DonneesApp.getBitmapBank().gettuyoWidth() , DonneesApp.getBitmapBank().gettuyoHeigth());
    }

    public int getTuyauX() {return tuyauX;}

    public int getTuyauY() {return tuyauY;}

    public void setTuyauX(int tuyauX) {this.tuyauX = tuyauX;}

    public void setTuyauY(int tuyauY) {this.tuyauY = tuyauY;}


    public void setVitesse(int vitesse) {this.vitesse = vitesse;}

    public void setbm(Bitmap b){
        bm = b;
    }

    public void spawnaleatoire(){
        Random r = new Random();
        this.tuyauY = r.nextInt(100) - 100;
    }

    public Rect getRect(){
        return new Rect(tuyauX, tuyauY, DonneesApp.getBitmapBank().gettuyoWidth() + tuyauX , DonneesApp.getBitmapBank().gettuyoHeigth() + tuyauY);
    }

    public void draw(Canvas canvas){
        tuyauX -= vitesse;
        canvas.drawBitmap(this.bm, tuyauX, tuyauY, null);
    }
}
