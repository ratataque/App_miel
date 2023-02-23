package com.example.app_miel.data;

import java.util.ArrayList;
import java.util.List;

public class Commande {

    private static                      Commande instance;
    private int                         id_eleve ;
    private ArrayList<Data_commandes>   liste_commandes;

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
