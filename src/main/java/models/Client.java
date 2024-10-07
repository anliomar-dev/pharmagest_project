package models;
import java.util.Date;
public class Client {
    private String nom;
    private String prenom;
    private String civilite;
    private Date ddn; // Date de naissance

    // Constructeur
    public Client(String nom, String prenom, String civilite, Date ddn) {
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.ddn = ddn;
    }

    // Getters et Setters
    // (à ajouter ici)
}

