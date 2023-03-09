package com.example.app_miel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_miel.data.Article_commande;
import com.example.app_miel.data.Commandes;
import com.example.app_miel.data.Data_commande;
import com.example.app_miel.http_tool.Acces_HTTP;
import com.example.app_miel.http_tool.AsyncResponse;
import com.example.app_miel.http_tool.Params;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Modification_commande extends AppCompatActivity implements AsyncResponse {
    private static final String ANNULADDR = "http://"+ Params.IP +"/mobile/delete.php";

    private Commandes commandes;
    private Map<Integer, Data_commande> liste_commandes;
    private ArrayList<Article_commande> liste_article;
    private Data_commande commande_client;

    private TextView text_client;
    private TextView text_adresse;
    private Button btn_valider;
    private Button btn_annuler;
    private Button btn_ajouter;

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
        ecoute_annuler();
        ecoute_ajouter();
    }



    private void init() {
        scroll_modif    = findViewById(R.id.lyt_scrol);
        text_client     = findViewById(R.id.tv_nom);
        text_adresse    = findViewById(R.id.tv_adresse);
        btn_valider     = findViewById(R.id.btn_valide);
        btn_annuler     = findViewById(R.id.btn_valid_modif);
        btn_ajouter     = findViewById(R.id.btn_ajout);
        id_client       = getIntent().getIntExtra("id", 0);

        commandes = Commandes.getInstance();
        liste_commandes = commandes.getListe_commandes();
//        Log.d("test", "init: "+ id_client);
        commande_client = liste_commandes.get(id_client);
        liste_article = commande_client.getListe_article();
        // Modification de la textView du haut de l'écran pour y afficher le nom et prénom du client sélectionné
        text_client.setText(commande_client.getNom_client()+" "+commande_client.getPrenom_client());

        // Modification de la textView de l'adresse du client pour y afficher le client
        text_adresse.setText(commande_client.getAdresse_client());
        affichage();
    }

    private void affichage(){
        int i = 0;
        Log.d("affichage", "affichage: je suis la");
        for (Article_commande article : liste_article) {

            LinearLayout lyt_global = new LinearLayout(this);
            lyt_global.setPadding(50,35,0,0);

            int color = ((i % 2 == 0) ? Color.LTGRAY : Color.WHITE);
            lyt_global.setBackgroundColor(color);

            lyt_global.setId(article.getId_miel());
            lyt_global.setOrientation(LinearLayout.VERTICAL);

            LinearLayout lyt_haut = new LinearLayout(this);
            lyt_haut.setOrientation(LinearLayout.VERTICAL);
            lyt_global.addView(lyt_haut);

            TextView txt_nom_miel = new TextView(this);
            txt_nom_miel.setText(article.getNom_miel());
            lyt_haut.addView(txt_nom_miel);

            LinearLayout lyt_bas = new LinearLayout(this);
            lyt_bas.setOrientation(LinearLayout.HORIZONTAL);
            lyt_global.addView(lyt_bas);

            TextView txt_prix = new TextView(this);
            txt_prix.setText("Prix : ");
            lyt_bas.addView(txt_prix);

            TextView prix = new TextView(this);
            prix.setText(Float.toString(article.getPrix_miel()));
            lyt_bas.addView(prix);

            TextView txt_qte = new TextView(this);
            txt_qte.setText("     Quantité : ");
            lyt_bas.addView(txt_qte);

            TextView qte = new TextView(this);
            qte.setInputType(InputType.TYPE_CLASS_NUMBER);
            qte.setText(Integer.toString(article.getQuantite_produit_commande()));
            lyt_bas.addView(qte);

            scroll_modif.addView(lyt_global);
            i++;
        }

        };

    private void ecoute_ajouter() {
        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), commande_a_modifie.class);
                intent.putExtra("id", id_client);

//                    Log.d("test", "onClick: "+ commande.getId_client().toString());

                startActivity(intent);
                finish();
            }
        });
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
    private void ecoute_annuler() {

        btn_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envoi_annulation(Integer.toString(id_client));
            }
        });
    }

    private void envoi_annulation(String id_client) {

        // Voir la page Acces_HTTP pour comprendre
        Acces_HTTP accesDonnees = new Acces_HTTP();

        // Le delegate permet de dédié la requête cette fonction. Le process finish en revenant
        // Saura que c'est a cette fonction qu'il doit revenir.
        accesDonnees.delegate = this;

        // Ajoute des paramètre à accèsDonnees.
        accesDonnees.addParams("id_client", id_client);
        accesDonnees.addParams("id_eleve", Integer.toString(Commandes.getInstance().getId_eleve()));

        accesDonnees.execute(ANNULADDR);
    }

    @Override
    public void processFinish(String output) {
        Log.d("json", "processFinish: "+output);
        try {
            JSONObject reponse = new JSONObject(output);

            if (!reponse.getString("state").equals("false")) {


                Map<Integer, Data_commande> liste_commandes = Commandes.getInstance().getListe_commandes();

                liste_commandes.remove(id_client);

                Intent intent = new Intent(getApplicationContext(), Menu_commande.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "La commande ne c'est pas intégré correctement", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("erreur", "erreurJSON: " +e);
        }
    }
}
