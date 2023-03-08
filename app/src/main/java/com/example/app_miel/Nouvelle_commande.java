package com.example.app_miel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_miel.data.Article_commande;
import com.example.app_miel.data.Commandes;
import com.example.app_miel.data.Data_commande;
import com.example.app_miel.data.Data_miel;
import com.example.app_miel.data.Miel;
import com.example.app_miel.http_tool.Acces_HTTP;
import com.example.app_miel.http_tool.AsyncResponse;
import com.example.app_miel.http_tool.Params;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Nouvelle_commande extends AppCompatActivity implements AsyncResponse {
    private static final String INSERTADDR = "http://"+ Params.IP +"/mobile/insert.php";

    private EditText nom_client;
    private EditText prenom_client;
    private EditText adresse_client;
    private Button btn_validation;
    private Button btn_annuler;
    private LinearLayout miel_scroll;
    private Integer id_eleve;
    private Miel miel;
    private ArrayList<Data_miel> liste_miel;
    private ArrayList<Article_commande> new_liste_article;
    private Data_commande data_commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attache le layout menu_commande à la page.
        setContentView(R.layout.nouvelle_commande);

        init();
        ecoute_annuler();
        ecoute_valider();
    }

    private void init() {
        prenom_client = findViewById(R.id.etxt_prenom);
        nom_client = findViewById(R.id.etxt_nom);
        adresse_client = findViewById(R.id.etxt_adresse);
        btn_validation = findViewById(R.id.btn_nouvelle_commande);
        btn_annuler = findViewById(R.id.btn_annuler);
        miel_scroll = findViewById(R.id.scrl_lyt_miels);
        new_liste_article = new ArrayList<Article_commande>();
        id_eleve = Commandes.getInstance().getId_eleve();
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
                String nom          = nom_client.getText().toString();
                String prenom       = prenom_client.getText().toString();
                String adresse      = adresse_client.getText().toString();
                Float prix_total = 0f;
                for (int i = 0; i < miel_scroll.getChildCount();i++) {
                    LinearLayout lyt_miel = (LinearLayout) miel_scroll.getChildAt(i);
                    Integer id_miel = lyt_miel.getId();

                    LinearLayout lyt_prix_quantite = (LinearLayout) lyt_miel.getChildAt(1);
                    EditText edt_quantite = (EditText) lyt_prix_quantite.getChildAt(3);

                    Integer quantite = -1;
                    if (!edt_quantite.getText().toString().equals("")) {
                        quantite = Integer.parseInt(edt_quantite.getText().toString());
                    }
                    //Toast.makeText(Nouvelle_commande.this, "Quantité : "+quantite.toString(), Toast.LENGTH_SHORT).show();

                    if (quantite >0){
                        TextView txt_prix = (TextView) lyt_prix_quantite.getChildAt(1);
                        Float prix = Float.parseFloat(txt_prix.getText().toString());

                        LinearLayout lyt_nom_miel = (LinearLayout) lyt_miel.getChildAt(0);
                        TextView txt_nom_miel = (TextView) lyt_nom_miel.getChildAt(0);
                        String nom_miel = txt_nom_miel.getText().toString();

                        Float total_produit_commande = prix * quantite;

                        Article_commande article_commande = new Article_commande(id_miel,nom_miel, quantite, total_produit_commande, prix);
                        new_liste_article.add(article_commande);
                        prix_total += total_produit_commande;
                    }
                }

                data_commande = new Data_commande(new_liste_article, prix_total, nom, prenom, adresse);
                Gson gson = new Gson();

                envoi_commande(gson.toJson(data_commande));
            }
        });
    }

    private void envoi_commande(String gson){



        // Voir la page Acces_HTTP pour comprendre
        Acces_HTTP accesDonnees = new Acces_HTTP();

        // Le delegate permet de dédié la requête cette fonction. Le process finish en revenant
        // Saura que c'est a cette fonction qu'il doit revenir.
        accesDonnees.delegate = this;

        // Ajoute des paramètre à accèsDonnees.
        accesDonnees.addParams("id_eleve", id_eleve.toString());
        accesDonnees.addParams("payload", gson);

        Log.d("new_cmd", "requete_connexion: "+gson);
        accesDonnees.execute(INSERTADDR);
    }

    @Override
    public void processFinish(String output) {
        Log.d("insert", "processFinish: "+output);

        try {
            JSONObject reponse = new JSONObject(output);

            if (!reponse.getString("state").equals("false")) {
                Integer id_client = Integer.parseInt(reponse.getString("id_client"));
                data_commande.setId_commande(Integer.parseInt(reponse.getString("id_commande")));
                data_commande.setId_client(id_client);

                Map<Integer, Data_commande> liste_commandes = Commandes.getInstance().getListe_commandes();
//                Log.d("test", "processFinish: "+id_client);

                liste_commandes.put(id_client, data_commande);

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
