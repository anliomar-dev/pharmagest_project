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

    // Getters et Setters
    // (à ajouter ici)
}

