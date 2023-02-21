package com.example.app_miel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
// Définiton de la class Login.
public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Attache le layout login à la page.
        setContentView(R.layout.login);
    }
}
