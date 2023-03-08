package com.example.app_miel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button btn_valider;

    private LinearLayout scroll_modif;

//    private Intent intent = getIntent();
    private int id_client ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attache le layout menu_commande à la page.
        setContentView(R.layout.modification_commande);

        init();
        ecoute_valider();
    }

    private void init() {
        scroll_modif    = findViewById(R.id.lyt_scroll);
        text_client     = findViewById(R.id.tv_nom);
        text_adresse    = findViewById(R.id.tv_adresse);
        btn_valider     = findViewById(R.id.btn_valide);
        id_client       = getIntent().getIntExtra("id", 0);

        commandes = Commandes.getInstance();
        liste_commandes = commandes.getListe_commandes();
//        Log.d("test", "init: "+ id_client);
        commande_client = liste_commandes.get(id_client);

        // Modification de la textView du haut de l'écran pour y afficher le nom et prénom du client sélectionné
        text_client.setText(commande_client.getNom_client()+" "+commande_client.getPrenom_client());

        // Modification de la textView de l'adresse du client pour y afficher le client
        text_adresse.setText(commande_client.getAdresse_client());

    }

    private void ecoute_valider() {
        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu_commande.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
