package com.example.app_miel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

// Définition de la class Splash_screen.
// Extends AppCompatActivity Permet d'hériter des propriété d'une view
public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Définition du layout d'affichage.
        setContentView(R.layout.splash_screen);

        // Runnable permet de lancer un sleep de X temps.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Définition de la nouvelle page qu'on chargera une fois le sleep terminé.
                Intent intent = new Intent(getApplicationContext(), Login.class);

                // Lancement de l'activité préparé plu haut.
                startActivity(intent);

                // Tue l'activité en cours.
                finish();
            }
        };
        // Définition du temps qu'on souhaite attendre ici 3000 milliseconde.
        new Handler().postDelayed(runnable,3000);

    }
}