package com.example.app_miel.data;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;


public class Miel {

    private static Miel instance;



    private ArrayList<Data_miel> liste_miel;


    public Miel() {
        liste_miel = new ArrayList<Data_miel>();
    }

    public static synchronized Miel new_instance(String json) {
//        Log.d("Miel", "Miel: "+ json);

        if (instance == null) {
            instance = new Gson().fromJson(json, Miel.class);
        }
        return instance;
    }
    public static synchronized Miel getInstance() {
        return instance;
    }

    public ArrayList<Data_miel> getListe_miel() {
        return liste_miel;
    }
}
