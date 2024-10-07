package controllers;

import java.util.Date;

public class ClientController {
    // Attributs
    private String nom;
    private String prenom;
    private String civilite;
    private Date ddn; // Date de naissance

    // Constructeur
    public ClientController(String nom, String prenom, String civilite, Date ddn) {
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.ddn = ddn;
    }

    // Méthodes
    public void ajouterClient() {
        // Logique pour ajouter un client
    }

    public void supprimerClient() {
        // Logique pour supprimer un client
    }
}

