package com.example.app_miel.data;

public class Article_commande {

    private String nom_miel;
    private int _id_miel;
    private int quantite_produit_commande;
    private int total_produit_commande;
    private int prix_miel;

    public Article_commande() {

    }

    public String getNom_miel() {
        return nom_miel;
    }

    public void setNom_miel(String nom_miel) {
        this.nom_miel = nom_miel;
    }

    public int getQuantite_produit_commande() {
        return quantite_produit_commande;
    }

    public void setQuantite_produit_commande(int quantite_produit_commande) {
        this.quantite_produit_commande = quantite_produit_commande;
    }

    public int getTotal_produit_commande() {
        return total_produit_commande;
    }

    public void setTotal_produit_commande(int total_produit_commande) {
        this.total_produit_commande = total_produit_commande;
    }

    public int getPrix_miel() {
        return prix_miel;
    }

    public void setPrix_miel(int prix_miel) {
        this.prix_miel = prix_miel;
    }
}
