package com.example.app_miel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_miel.data.Commandes;
import com.example.app_miel.data.Data_commande;

import java.util.ArrayList;

public class Modification_commande extends AppCompatActivity {
    private Commandes commandes;
    private TextView text_client;
    private TextView text_adresse;

    private ArrayList<Data_commande> liste_commandes;
    private LinearLayout scroll_modif;

    Intent intent = getIntent();
    private int id_client ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attache le layout menu_commande à la page.
        setContentView(R.layout.modification_commande);

        init();
    }
    private void init() {

        scroll_modif    = findViewById(R.id.lyt_scroll);
        text_client     = findViewById(R.id.tv_nom);
        text_adresse    = findViewById(R.id.tv_adresse);
        id_client       = intent.getIntExtra("id", 0);

        for (Data_commande commande: liste_commandes) {
            
        }

        // Modification de la textView du haut de l'écran pour y afficher le nom et prénom de l'élève
        text_client.setText("");
    }


}
