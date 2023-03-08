package com.example.app_miel.data;

public class Article_commande {

    private String nom_miel;
    private Integer id_miel;
    private Integer quantite_produit_commande;
    private Float total_produit_commande;
    private Float prix_miel;

    public Article_commande( Integer id_miel, String nom_miel, Integer quantite_produit_commande, Float total_produit_commande, Float prix_miel) {
        this.id_miel = id_miel;
        this.nom_miel = nom_miel;
        this.quantite_produit_commande = quantite_produit_commande;
        this.total_produit_commande = total_produit_commande;
        this.prix_miel = prix_miel;
    }

    public String getNom_miel() {
        return nom_miel;
    }

    public Integer getQuantite_produit_commande() {
        return quantite_produit_commande;
    }

    public Float getTotal_produit_commande() {
        return total_produit_commande;
    }

    public Float getPrix_miel() {
        return prix_miel;
    }

    public Integer getId_miel() {
        return id_miel;
    }
}
