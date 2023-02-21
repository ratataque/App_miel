package com.example.app_miel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_miel.http_tool.Acces_HTTP;
import com.example.app_miel.http_tool.AsyncResponse;

import java.util.ArrayList;

// Définiton de la class Login.
public class Login extends AppCompatActivity implements AsyncResponse {

    // Déclaration des variables dont l'ont aura besoin aux types leurs correspondants.
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private static final String LOGINADDR = "http://192.168.223.130/mobile/login.php";
    // IP pc portable karl : 192.168.223.130
    // IP pc EWAN :

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Attache le layout login à la page.
        setContentView(R.layout.login);

        // Lancement de la fonction.
        init();
        ecoute_login();
    }

    // Initialise dans des variables les différents éléments du layout avec qui ont souhaite interéagir
    private void init() {
        username = findViewById(R.id.get_email_login);
        password = findViewById(R.id.get_pass_login);
        btnLogin = findViewById(R.id.login_button);

    }


    private void ecoute_login() {

        //Ajout d'un listener sur le bouton de la page pour pouvoir faire quelque chose lorsqu'il et cliquer
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nom = username.getText().toString();
                String mdp = password.getText().toString();

                // Lance la fonction.
                requete_connexion( nom, mdp);

                // le toast permet d'afficher par une pop up un message, utile pour effectué des test
                //Toast.makeText(Login.this, "USER : "+username.getText()+" MDP : "+password.getText(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void requete_connexion(String username, String mdp) {

        // Voir la page Acces_HTTP pour comprendre
        Acces_HTTP accesDonnees = new Acces_HTTP();

        // Le delegate permet de dédié la requête cette fonction. Le process finish en revenant
        // Saura que c'est a cette fonction qu'il doit revenir.
        accesDonnees.delegate = this;

        // Ajoute des paramètre à accèsDonnees.
        accesDonnees.addParams("login", username);
        accesDonnees.addParams("password", mdp);

        accesDonnees.execute(LOGINADDR);
    }


    @Override
    public void processFinish(String output) {

    }
}
