package com.example.flappybird;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread{ //thread qui fonctionne avec Gameview en utilisant lmes fonctions de GameEngine

    SurfaceHolder surfaceHolder;

    boolean isRunning;
    long startTime, loopTime;
    long DELAY = 33;

    public GameThread(SurfaceHolder s){
        this.surfaceHolder = s;
        isRunning = true;
    }

    public void run(){

        while(isRunning){
            startTime = SystemClock.uptimeMillis();

            Canvas canvas = surfaceHolder.lockCanvas(null); //on verouille afin de pouvoir dessinner dessus
            if(canvas != null){
                    //on appelle les fonctions de dessin contenues dans gamme engine
                    DonneesApp.getGameEngine().DessinBg(canvas); //on dessine l'arriere plan
                    DonneesApp.getGameEngine().DessinOiseau(canvas);//on dessine l'oiseau
                    DonneesApp.getGameEngine().DessinTuyaux(canvas);//on dessine les tuyaux
                    DonneesApp.getGameEngine().DessinScore(canvas);//on dessine le score

                    surfaceHolder.unlockCanvasAndPost(canvas);//on deverouille pour afficher

            }

            loopTime = SystemClock.uptimeMillis() - startTime;

            if(loopTime < DELAY){ //pour assurer le bon éroulement du thread
                try{
                    Thread.sleep(DELAY - loopTime);
                }catch(InterruptedException e){
                    Log.e("Interrupted", "Interrupted while sleeping");
                }
            }
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void setRunning(boolean i){
        isRunning = i;
    }

    public void stopThread(){
        isRunning = false;
        this.interrupt();
    }
}
