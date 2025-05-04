package com.example.guidetouristique;

public class Ville {
    private String id;
    private int imageResId;

    public Ville(String id, int imageResId) {
        this.id = id;
        this.imageResId = imageResId;
    }

    public String getId() {
        return id;
    }

    public int getImageResId() {
        return imageResId;
    }

    // Supprimer getNom() ici. On utilisera getId() pour charger dynamiquement le nom.
}

