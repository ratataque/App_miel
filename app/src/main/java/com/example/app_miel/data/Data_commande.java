package com.example.app_miel.data;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Data_commande {

    private static Data_commande instance;
    private ArrayList<Article_commande> liste_article;
    private int                         id_commande;
    private int                         id_client;
    private int                         total_commande;
    private String                      nom_client;
    private String                      prenom_client;
    private String                      adresse_client;


    public Data_commande() {

    }

    public static Data_commande init() {
        instance = new Data_commande();
        return instance;
    }


    public int getId_client() {
        return id_client;
    }


    public ArrayList<Article_commande> getListe_article() {
        return liste_article;
    }

    public int getId_commande() {
        return id_commande;
    }

    public int getTotal_commande() {
        return total_commande;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public String getAdresse_client() {
        return adresse_client;
    }
}
