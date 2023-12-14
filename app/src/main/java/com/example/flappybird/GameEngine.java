package com.example.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;

import java.util.ArrayList;

public class GameEngine { //toutes les fonctions de dessin, d'affichage...
    BackgroundImage background;

    Wazo wazo;

    ArrayList <Tuyau> tuyos; //les tuyeaux
    int separation, nombre;  //distance qui separe les tuyaux

    static int score, etat;
    // 0 = pas commence, 1 = en cours, 2 == fini


    public GameEngine(){ //avant debut on cree une instance de cheque element du jeu et on met le score a zero
        score = 0;
        etat = 0;
        background = new BackgroundImage();
        wazo = new Wazo(TypeOiseau.POULET);
        tuyos = new ArrayList();
        inituyo();

    }

    public void inituyo(){  //initiation de la liste des tuyeaux
        this.separation = 300*DonneesApp.SCREEN_HEIGHT/1920; //on assigne a la separation sa valeur
        nombre = 6;
        for(int i = 0; i < 6; i++){
            if(i < 3){ //guyau du haut
                tuyos.add(new Tuyau(DonneesApp.SCREEN_WIDTH + i * ((DonneesApp.SCREEN_WIDTH + 200*DonneesApp.SCREEN_WIDTH/1080) / 3), 0));
                tuyos.get(i).setbm(DonneesApp.getBitmapBank().tuyaux[1]);
                tuyos.get(tuyos.size() - 1).spawnaleatoire();
            }else{
                //tuyau du bas dans la hauteur est determinee en fonction de celui du haut et de la separation
                tuyos.add(new Tuyau(tuyos.get(i - nombre / 2).getTuyauX(),  tuyos.get(i - nombre / 2).getTuyauY() + DonneesApp.getBitmapBank().gettuyoHeigth() + separation));
                tuyos.get(i).setbm(DonneesApp.getBitmapBank().tuyaux[0]);
            }
        }
    }

    public void DessinBg(Canvas c){  //afficher l'arriere plan

        c.drawBitmap(DonneesApp.getBitmapBank().getBackground(), background.getX(), background.getY(), null);

        if(background.getX() <= DonneesApp.getBitmapBank().getBackgroundWidth() - DonneesApp.SCREEN_WIDTH){ //si le background depasse l'écran
            c.drawBitmap(DonneesApp.getBitmapBank().getBackground(), background.getX() + DonneesApp.getBitmapBank().getBackgroundWidth(), background.getY(), null );
        }
    }


    public void DessinOiseau(Canvas canvas) { // afficher l'oiseau sur l'ecran et veiller a son integrite


        if(wazo.type == TypeOiseau.WAZO){
        int debut = 0; //variables pour controller la couleur de l'oiseau affiché
        int fin = 3;
        if (etat == 1) { //jeu débuté
            if (wazo.getWazoY() < (DonneesApp.SCREEN_HEIGHT - DonneesApp.getBitmapBank().getwazoHeight()) || wazo.getVitesse() < 0) { //chute de l'oiseau
                wazo.setVitesse(wazo.getVitesse() + DonneesApp.gravite); 
                wazo.setY(wazo.getWazoY() + wazo.getVitesse());//loiseau chute
            }}

            int frame = wazo.getFrame(); //on recupere l'image a afficher

            canvas.drawBitmap(DonneesApp.getBitmapBank().getBird(frame), wazo.getWazoX(), wazo.getWazoY(), null); //on dessine l'ioseau

            // changement de couleur en fonction du score
            frame++; // on incremente pour changer la frame de l'oiseau et creer le mouvement

            // changement de couleur en fonction du score
            if (score % 30 <= 10) { //si le score est 10, 40, 70... on affiche le premier oiseau
                debut = 0;
                fin = 3;
            } else {
                if (score % 30 <= 20) { //20,50,80...
                    debut = 4;
                    fin = 7;
                } else { //30,60,90..
                    debut = 8;
                    fin = 11;
                }
            }

            if (frame >= fin) { // si le mouvement complet a ete affiché
                frame = debut;
            }

            wazo.setFrame(frame);

    }else{

            int debut = 0; //variables pour controller la couleur de l'oiseau affiché
            int fin = 2;
            if (etat == 1) { //jeu débuté
                if (wazo.getWazoY() < (DonneesApp.SCREEN_HEIGHT - DonneesApp.getBitmapBank().getwazoHeight()) || wazo.getVitesse() < 0) { //chute de l'oiseau
                    wazo.setVitesse(wazo.getVitesse() + DonneesApp.gravite);
                    wazo.setY(wazo.getWazoY() + wazo.getVitesse());//loiseau chute
                }}

            int frame = wazo.getFrame(); //on recupere l'image a afficher

            canvas.drawBitmap(DonneesApp.getBitmapBank().getPoulet(frame), wazo.getWazoX(), wazo.getWazoY(), null); //on dessine l'ioseau

            // changement de couleur en fonction du score
            frame++; // on incremente pour changer la frame de l'oiseau et creer le mouvement

            if (frame >= fin) { // si le mouvement complet a ete affiché
                frame = debut;
            }

            wazo.setFrame(frame);


        }}

     public void DessinTuyaux(Canvas canvas){
        if(etat == 1){
            for(int i = 0; i < nombre; i++){
                //conditions d'arret

                if(wazo.getRect().intersect(tuyos.get(i).getRect()) || tuyos.get(i).getRect().intersect(wazo.getRect())){ //collision
                    etat = 2; //jeu fini
                    DonneesApp.stopmusc();
                    Context context = DonneesApp.gameacContext;
                    DonneesApp.gv.stopthread();
                    Intent intent = new Intent(context, Fin.class);
                    intent.putExtra("score", score);

                    context.startActivity(intent); //on relance l'activité de base
                    ((Activity) context).finish(); 

                }
                

                // si l'oiseau dépasse un tuyeau le score augmente
                if(wazo.getWazoX() + DonneesApp.getBitmapBank().getwazoWidth() > tuyos.get(i).getTuyauX() + DonneesApp.getBitmapBank().gettuyoWidth() / 2 //deuxième condition car ça ajoutait plus que nécessaire au score
                && wazo.getWazoX() + DonneesApp.getBitmapBank().getwazoWidth() <= tuyos.get(i).getTuyauX() + DonneesApp.getBitmapBank().gettuyoWidth() / 2  + Tuyau.vitesse && i < 3 ){
                    score++;
                }
                if(tuyos.get(i).getTuyauX() < -DonneesApp.getBitmapBank().gettuyoWidth()){ //tuyau arrivé en fin d'écran
                    tuyos.get(i).setTuyauX(DonneesApp.SCREEN_WIDTH);  //on le remet au debut
                    if(i < nombre /2){ //tyuyau du haut
                       tuyos.get(i).spawnaleatoire(); //hauteur aleatoire
                    }else{ //bas
                        tuyos.get(i).setTuyauY(tuyos.get(i - nombre / 2).getTuyauY() + DonneesApp.getBitmapBank().gettuyoHeigth() + separation);
                    }
                }
                tuyos.get(i).draw(canvas);
            }
        }
     }



     public void DessinScore(Canvas canvas) {

         if (this.score < 10) { // score petit un seul nombre a afficher
             canvas.drawBitmap(DonneesApp.getBitmapBank().getscore(this.score), DonneesApp.SCREEN_WIDTH / 2, DonneesApp.SCREEN_HEIGHT - 300, null);

       }else{ //sinon deux nombres a afficher
            canvas.drawBitmap(DonneesApp.getBitmapBank().getscore(this.score / 10), DonneesApp.SCREEN_WIDTH / 2 - 25, DonneesApp.SCREEN_HEIGHT - 300, null );
            canvas.drawBitmap(DonneesApp.getBitmapBank().getscore(this.score % 10), DonneesApp.SCREEN_WIDTH / 2 + 25, DonneesApp.SCREEN_HEIGHT - 300, null );
        }



             //canvas.drawBitmap(DonneesApp.getBitmapBank().getscore(0), DonneesApp.SCREEN_WIDTH / 2,DonneesApp.SCREEN_HEIGHT - 300,  null);

         }

     }

