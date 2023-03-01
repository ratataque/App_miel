package com.example.app_miel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_miel.data.Commandes;
import com.example.app_miel.data.Data_commande;

import java.util.ArrayList;

// Définition de la classe Menu_commande
// AppCompatActivity Sers à ratacher un layout à la Classe
public class Menu_commande extends AppCompatActivity {

    // 1. Définition dess différentes variables qui contiendront les objets dont l'on a besoin.
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

        // 2. Appel des constructeur des classes/ Objet dont on as besoin.
        commandes = Commandes.getInstance();
        liste_commandes = commandes.getListe_commandes();

        // 3. Association avec la vue des objets avec lesquels on souhaite intéragir.
        scroll = findViewById(R.id.lyt_scroll);
        text_eleve = findViewById(R.id.tv_nom_eleve);

        // Modification de la textView du haut de l'écran pour y afficher le nom et prénom de l'élève
        text_eleve.setText(commandes.getNom_eleve()+" "+commandes.getPrenom_eleve());

        // Boucle for permettant l'affichage de toute les commande créer dans la base de données par l'élève  en question.
        for ( Data_commande commande: liste_commandes){

            // Création d'un objet bouton pour l'utiliser
            TextView btn_cmd = new Button(this);
            // Définition du texte du bouton.
            btn_cmd.setText(commande.getNom_client()+" "+commande.getPrenom_client());

            // Définition de l'id du bouton
            btn_cmd.setId(commande.getId_client());

            btn_cmd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Modification_commande.class);
                    intent.putExtra("id", commande.getId_client());
                    startActivity(intent);
                    finish();

                }
            });

            // Modification de certain des paramètres du bouton
            btn_cmd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            // Ajout du bouton dans le layout de la scrollView
            scroll.addView(btn_cmd);
        }
        
        
    }
}
