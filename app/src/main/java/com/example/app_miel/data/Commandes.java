package com.example.app_miel.data;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Commandes {

    private static Commandes instance;
    private int                         id_eleve ;
    private String                      nom_eleve;
    private String                      prenom_eleve;
    private Map<Integer, Data_commande> liste_commandes;

    public Commandes(int id_eleve, String nom_eleve, String prenom_eleve) {
        this.nom_eleve = nom_eleve;
        this.prenom_eleve = prenom_eleve;
        this.id_eleve = id_eleve;
        liste_commandes = new Hashtable<>();
    }

    public static  synchronized Commandes init(int id_eleve, String nom_eleve, String prenom_eleve) {
        if (instance == null) {
            instance = new Commandes(id_eleve, nom_eleve, prenom_eleve);
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

    public String getNom_eleve() {
        return nom_eleve;
    }

    public String getPrenom_eleve() {
        return prenom_eleve;
    }

    public int getId_eleve() {
        return id_eleve;
    }

    public Map<Integer, Data_commande> getListe_commandes() {
        return liste_commandes;
    }
}
