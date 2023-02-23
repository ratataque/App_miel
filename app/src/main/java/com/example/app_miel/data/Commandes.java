package com.example.app_miel.data;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Commandes {

    private static Commandes instance;
    private int                         id_eleve ;
    private ArrayList<Data_commande>   liste_commandes;

    public Commandes(int id_eleve) {
        this.id_eleve = id_eleve;
        liste_commandes = new ArrayList<Data_commande>();
    }

    public static  synchronized Commandes init(int id_eleve) {
        if (instance == null) {
            instance = new Commandes(id_eleve);
        }
        return instance;
    }

    public static synchronized Commandes new_instance(String json) {
//        Log.d("commande", "commande: "+ json);

        if (instance == null) {
            instance = new Gson().fromJson(json, Commandes.class);
        }
        return instance;
    }

    public static synchronized Commandes getInstance() {
        return instance;
    }

    public int getId_eleve() {
        return id_eleve;
    }

    public ArrayList<Data_commande> getListe_commandes() {
        return liste_commandes;
    }
}
