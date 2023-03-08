package com.example.app_miel.data;

import java.util.ArrayList;

public class Data_commande {


    private ArrayList<Article_commande> liste_article;
    private Integer                     id_commande;
    private Integer                     id_client;
    private Float                     prix_total_commande;
    private String                      nom_client;
    private String                      prenom_client;
    private String                      adresse_client;


    public Data_commande(ArrayList<Article_commande> liste_article, Float prix_total_commande, String nom_client, String prenom_client, String adresse_client ) {
        this.liste_article = liste_article;
        this.prix_total_commande = prix_total_commande;
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.adresse_client = adresse_client;
    }



    public Integer getId_client() {
        return id_client;
    }


    public ArrayList<Article_commande> getListe_article() {
        return liste_article;
    }

    public Integer getId_commande() {
        return id_commande;
    }

    public Float getPrix_total_commande() {
        return prix_total_commande;
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
