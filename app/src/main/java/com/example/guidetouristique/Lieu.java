package com.example.guidetouristique;

public class Lieu {
    private String nom;
    private String description;
    private int imageResourceId;

    private String adresse;

    // Constructeur avec les nouveaux champs
    public Lieu(String nom, String description, int imageResourceId, String adresse) {
        this.nom = nom;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.adresse = adresse;
    }

    // Getters
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public int getImageResourceId() { return imageResourceId; }

    public String getAdresse() { return adresse;}
}
