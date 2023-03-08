package com.example.app_miel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_miel.data.Data_commande;
import com.example.app_miel.data.Data_miel;
import com.example.app_miel.data.Miel;

import java.security.PrivateKey;
import java.util.ArrayList;

public class Nouvelle_commande extends AppCompatActivity {

    private EditText nom_client;
    private EditText prenom_client;
    private EditText adresse_client;
    private Button btn_validation;
    private Button btn_annuler;
    private LinearLayout miel_scroll;


    private Miel miel;
    private ArrayList<Data_miel> liste_miel;
    private Data_commande data_commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attache le layout menu_commande à la page.
        setContentView(R.layout.nouvelle_commande);

        init();
        ecoute_annuler();
    }

    private void init() {
        prenom_client = findViewById(R.id.etxt_prenom);
        nom_client = findViewById(R.id.etxt_nom);
        adresse_client = findViewById(R.id.etxt_adresse);
        btn_validation = findViewById(R.id.btn_new_commande);
        btn_annuler = findViewById(R.id.btn_annuler);
        miel_scroll = findViewById(R.id.scrl_lyt_miels);


        miel = Miel.getInstance();
        liste_miel = miel.getListe_miel();

        int i = 0;
        for (Data_miel objet_miel : liste_miel) {

            LinearLayout lyt_global = new LinearLayout(this);
            lyt_global.setPadding(50,35,0,0);

            int color = ((i % 2 == 0) ? Color.LTGRAY : Color.WHITE);
            lyt_global.setBackgroundColor(color);
            
            lyt_global.setId(objet_miel.getId_miel());
            lyt_global.setOrientation(LinearLayout.VERTICAL);

                LinearLayout lyt_haut = new LinearLayout(this);
                lyt_haut.setOrientation(LinearLayout.VERTICAL);
                lyt_global.addView(lyt_haut);

                    TextView txt_nom_miel = new TextView(this);
                    txt_nom_miel.setText(objet_miel.getNom_miel());
                    lyt_haut.addView(txt_nom_miel);

                LinearLayout lyt_bas = new LinearLayout(this);
                lyt_bas.setOrientation(LinearLayout.HORIZONTAL);
                lyt_global.addView(lyt_bas);

                    TextView txt_prix = new TextView(this);
                    txt_prix.setText("Prix : ");
                    lyt_bas.addView(txt_prix);

                    TextView prix = new TextView(this);
                    prix.setText(Float.toString(objet_miel.getPrix_miel()));
                    lyt_bas.addView(prix);

                    TextView txt_qte = new TextView(this);
                    txt_qte.setText("     Quantité : ");
                    lyt_bas.addView(txt_qte);

                    EditText qte = new EditText(this);
                    qte.setInputType(InputType.TYPE_CLASS_NUMBER);
                    qte.setHint("0");
                    lyt_bas.addView(qte);

            miel_scroll.addView(lyt_global);
            i++;
        }
    }

    private void ecoute_annuler() {
        btn_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu_commande.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void ecoute_valider(){

        btn_validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom      = nom_client.getText().toString();
                String prenom   = prenom_client.getText().toString();
                String adresse  = adresse_client.getText().toString();

                for (int i = 0; i < miel_scroll.getChildCount();i++) {
                    LinearLayout lyt_miel = (LinearLayout) miel_scroll.getChildAt(i);

                    LinearLayout lyt_prix_quantite = (LinearLayout) lyt_miel.getChildAt(1);
                    // Integer quantite = (LinearLayout) lyt_prix_quantite

                }

                    envoi_commande(adresse, nom, prenom);
            }
        });
    }

    private void envoi_commande(String adresse, String nom, String prenom){

    }

}
