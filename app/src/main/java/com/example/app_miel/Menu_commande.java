package com.example.app_miel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_miel.data.Commandes;
import com.example.app_miel.data.Data_commande;

import java.util.ArrayList;

// Définition de la classe Menu_commande
// AppCompatActivity Sers à ratacher un layout à la Classe
public class Menu_commande extends AppCompatActivity {

    private Commandes commandes;
    private TextView text_eleve;
    private ArrayList<Data_commande> liste_commandes;
    private LinearLayout scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Attache le layout menu_commande à la page.
        setContentView(R.layout.menu_commande);

        init();
    }


    private void init() {
        commandes = Commandes.getInstance();
        liste_commandes = commandes.getListe_commandes();

        scroll = findViewById(R.id.lyt_scroll);

        text_eleve = findViewById(R.id.tv_nom_eleve);
        text_eleve.setText(commandes.getNom_eleve()+" "+commandes.getPrenom_eleve());


        for ( Data_commande commande: liste_commandes){

            TextView btn_cmd = new Button(this);
            btn_cmd.setText(commande.getNom_client()+" "+commande.getPrenom_client());
            btn_cmd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            scroll.addView(btn_cmd);
        }
        
        
    }
}
