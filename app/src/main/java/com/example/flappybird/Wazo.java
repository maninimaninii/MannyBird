package com.example.flappybird;

import android.graphics.Rect;

enum TypeOiseau {
    WAZO, POULET
}

public class Wazo{

        private int wazoX, wazoY, frameacc, vitesse;
        public static int framemax;

        public TypeOiseau type;

        public Rect rect ;



        public Wazo (TypeOiseau t){ //on cree l'oiseau de façon à ce qu'il se retrouve au milieu de l'écran
            wazoX = DonneesApp.SCREEN_WIDTH / 2 - DonneesApp.getBitmapBank().getwazoWidth() / 2;
            wazoY = DonneesApp.SCREEN_HEIGHT/2 - DonneesApp.getBitmapBank().getwazoHeight() / 2;
            frameacc = 0;
            if(t == TypeOiseau.WAZO){
            framemax =11;}else{framemax = 2;}
            vitesse = 0;
            //rect = new Rect(wazoX, wazoY, AppConstants.getBitmapBank().getwazoWidth(), AppConstants.getBitmapBank().getwazoHeight());
        }


        public int getVitesse(){
            return vitesse;
        }

        public void setVitesse(int v){
            this.vitesse = v;
        }
        public int getWazoX(){
            return wazoX;
        }

        public int getWazoY(){
            return wazoY;
    }

    public int getFrame(){
            return frameacc;
    }

    public void setFrame(int f){
            this.frameacc = f;
    }

    public void setX(int x){
            this.wazoX = x;
    }

    public void setY(int y){
        this.wazoY = y;
    }


    public Rect getRect() {return new Rect(wazoX, wazoY, DonneesApp.getBitmapBank().getwazoWidth() + wazoX, DonneesApp.getBitmapBank().getwazoHeight() + wazoY);}
    //rectangle qui entoure l'image de l'oiseau et qui sera utilisé pour detecter les impacts 


    public void setRect(Rect rect) {this.rect = rect;}
}
