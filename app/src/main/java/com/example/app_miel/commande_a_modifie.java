package com.example.app_miel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_miel.http_tool.AsyncResponse;

public class commande_a_modifie extends AppCompatActivity implements AsyncResponse {

    EditText nom_client;
    EditText prenom_client;
    EditText adressse_client;
    Button validation;
    LinearLayout lyt_affichage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Attache le layout menu_commande à la page.
        setContentView(R.layout.commande_a_modifie);

        init();
    }
    private void init() {

    }

    @Override
    public void processFinish(String output) {

    }
}
