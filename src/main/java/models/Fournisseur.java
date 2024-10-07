package models;

public class Fournisseur {
    private int id; // ID auto-incrémenté
    private String nom;
    private String adresse;

    // Constructeur
    public Fournisseur(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    // Setter pour l'ID
    public void setId(int id) {
        this.id = id;
    }
}
