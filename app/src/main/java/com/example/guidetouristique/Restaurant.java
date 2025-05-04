package com.example.guidetouristique;

public class Restaurant {
    private String nom;
    private String specialite;
    private int imageResourceId;
    private String email;      // Contact email
    private String telephone;  // Numéro de téléphone

    private String adresse;


    public Restaurant(String nom, String specialite, int imageResourceId, String email, String telephone, String adresse) {
        this.nom = nom;
        this.specialite = specialite;
        this.imageResourceId = imageResourceId;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    // Getters
    public String getNom() { return nom; }
    public String getSpecialite() { return specialite; }
    public int getImageResourceId() { return imageResourceId; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getAdresse() { return adresse; }
}
