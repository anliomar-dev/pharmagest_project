package controllers;

import java.util.Date;

public class VenteController {
    // Attributs
    private int numVente;
    private Date dateVente;
    private String status;
    private double montant;

    // Constructeur
    public VenteController(int numVente, Date dateVente, double montant, String status) {
        this.numVente = numVente;
        this.dateVente = dateVente;
        this.montant = montant;
        this.status = status;
    }

    // Méthodes
    public void enregistrerVente() {
        // Logique pour enregistrer une vente
    }

    public void annulerVente() {
        // Logique pour annuler une vente
    }
}

