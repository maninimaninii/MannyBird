package com.example.flappybird;

public class BackgroundImage { //classe qui gere l'image de fond
    private int x, y, velocite;

    public BackgroundImage(){
        x = 0;
        y = 0;

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.x = y;
    }

}
