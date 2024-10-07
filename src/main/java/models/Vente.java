package models;

import java.util.Date;

public class Vente {
    private int numVente;
    private Date dateVente;
    private String status;
    private double montant;

    // Associations
    private Client client;
    private Utilisateur utilisateur;

    // Constructeur
    public Vente(int numVente, Date dateVente, String status, double montant, Client client, Utilisateur utilisateur) {
        this.numVente = numVente;
        this.dateVente = dateVente;
        this.status = status;
        this.montant = montant;
        this.client = client;
        this.utilisateur = utilisateur;
    }

    // Getters et Setters
    // (à ajouter ici)
}

