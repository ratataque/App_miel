package com.example.app_miel.data;

import java.util.ArrayList;
import java.util.List;

public class Commande {

    private static Commande instance;
    private int     id_client ;
    private String  nom_client;
    private String  prenom_client;
    private String  adresse_client;
    private int     id_commande;
    private int     total_commande;
    private ArrayList<Article_commande> liste_article;

    public Commande() {

    }

    public static synchronized Commande new_commande() {
        instance = new Commande();
        return instance;
    }

    public static synchronized Commande getInstance() {
        return instance;
    }





}
