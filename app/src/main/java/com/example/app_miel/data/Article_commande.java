package com.example.app_miel.data;

public class Article_commande {

    private String nom_miel;
    private int id_miel;
    private int quantite_produit_commande;
    private int total_produit_commande;
    private int prix_miel;

    public Article_commande() {

    }

    public String getNom_miel() {
        return nom_miel;
    }

    public int getQuantite_produit_commande() {
        return quantite_produit_commande;
    }

    public int getTotal_produit_commande() {
        return total_produit_commande;
    }

    public int getPrix_miel() {
        return prix_miel;
    }

    public int getId_miel() {
        return id_miel;
    }
}
