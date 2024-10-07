package models;

import java.util.Date;

public class CommandeLivraison {
    private int numCommande;
    private Date dateCommande;
    private Date dateLivraison;
    private String noBondeLivraisonFm;

    // Constructeur
    public CommandeLivraison(int numCommande, Date dateCommande, Date dateLivraison, String noBondeLivraisonFm) {
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.noBondeLivraisonFm = noBondeLivraisonFm;
    }

    // Getters et Setters
    // (à ajouter ici)

    // Méthodes
    public void creerCommande() { /* Implémentation */ }
    public void livrerCommande() { /* Implémentation */ }
}

