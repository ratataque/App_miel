package com.example.app_miel.data;

import java.util.ArrayList;

public class Data_commandes {

    private ArrayList<Article_commande> liste_article;
    private int                         id_commande;
    private int                         id_client;
    private int                         total_commande;
    private String                      nom_client;
    private String                      prenom_client;
    private String                      adresse_client;


    public Data_commandes() {

    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }


    public ArrayList<Article_commande> getListe_article() {
        return liste_article;
    }

    public void setListe_article(ArrayList<Article_commande> liste_article) {
        this.liste_article = liste_article;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getTotal_commande() {
        return total_commande;
    }

    public void setTotal_commande(int total_commande) {
        this.total_commande = total_commande;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public String getAdresse_client() {
        return adresse_client;
    }

    public void setAdresse_client(String adresse_client) {
        this.adresse_client = adresse_client;
    }
}
