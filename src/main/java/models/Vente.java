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


    public int getNumVente() {
        return numVente;
    }

    public void setNumVente(int numVente) {
        this.numVente = numVente;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}

