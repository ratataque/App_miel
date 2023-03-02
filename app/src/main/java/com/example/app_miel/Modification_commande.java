package com.example.app_miel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_miel.data.Commandes;
import com.example.app_miel.data.Data_commande;

import java.util.ArrayList;
import java.util.Map;

public class Modification_commande extends AppCompatActivity {
    private Commandes commandes;
    private Map<Integer, Data_commande> liste_commandes;
    private Data_commande commande_client;

    private TextView text_client;
    private TextView text_adresse;

    private LinearLayout scroll_modif;

//    private Intent intent = getIntent();
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
        id_client       = getIntent().getIntExtra("id", 0);

        commandes = Commandes.getInstance();
        liste_commandes = commandes.getListe_commandes();
        commande_client = liste_commandes.get(id_client);

        // Modification de la textView du haut de l'écran pour y afficher le nom et prénom de l'élève
        text_client.setText(commande_client.getNom_client());
    }


}
