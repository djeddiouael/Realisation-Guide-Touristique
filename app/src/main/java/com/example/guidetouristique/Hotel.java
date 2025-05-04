package com.example.guidetouristique;

public class Hotel {
    private String nom;
    private String etoiles;
    private int imageResourceId;
    private String email;
    private String telephone;
    private String adresse;

    // Constructeur
    public Hotel(String nom, String etoiles, int imageResourceId, String email, String telephone, String adresse) {
        this.nom = nom;
        this.etoiles = etoiles;
        this.imageResourceId = imageResourceId;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    // Getters
    public String getNom() { return nom; }
    public String getEtoiles() { return etoiles; }
    public int getImageResourceId() { return imageResourceId; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getAdresse() { return adresse; }
}
