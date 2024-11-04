package models;

import java.util.Date;

public class Ordonnance {
    private int numOrd;
    private Date dateOrd;
    private String nomMedecin;

    // Associations
    private Client client;

    // Constructeur
    public Ordonnance(int numOrd, Date dateOrd, String nomMedecin, Client client) {
        this.numOrd = numOrd;
        this.dateOrd = dateOrd;
        this.nomMedecin = nomMedecin;
        this.client = client;
    }

    public Date getDateOrd() {
        return dateOrd;
    }

    public void setDateOrd(Date dateOrd) {
        this.dateOrd = dateOrd;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNumOrd() {
        return numOrd;
    }

    public void setNumOrd(int numOrd) {
        this.numOrd = numOrd;
    }

    // Getters et Setters
    // (à ajouter ici)
}

