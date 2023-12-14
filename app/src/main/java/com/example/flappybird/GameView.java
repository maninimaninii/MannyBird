package com.example.flappybird;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;
    public GameView(Context context) {
        super(context);
        Initialisation();
        DonneesApp.gv = this;
    }

    //fonctions par défaut
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        //on lance le thread a la creation de la surfacz
        if(!gameThread.isRunning()){
            gameThread = new GameThread(surfaceHolder);
            gameThread.start();
        }else{
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        //on  assure de stopper le thread a la destruction
        if (gameThread.isRunning()) {
            gameThread.setRunning(false);
            boolean reessayer = true;

            while(reessayer){
                    gameThread.interrupt();
                    reessayer= false;
                }
            }
        }


        public void stopthread(){
        this.gameThread.setRunning(false);
        this.gameThread.interrupt();
        this.gameThread.surfaceHolder.removeCallback(this);
        }
    void Initialisation(){
        //creation du thread 
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);

        gameThread = new GameThread(holder);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //detection des appuis pour les sauts
        int action = event.getAction();
        //touché d'écran

        if(action == MotionEvent.ACTION_DOWN){
            GameEngine.etat = 1;
            DonneesApp.getGameEngine().wazo.setVitesse(DonneesApp.vitessesaut);
        }
        return true;
    }
}
