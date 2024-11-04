package models;

import java.util.Date;

public class CommandeLivraison {
    private int numCommande;
    private Date dateCommande;
    private Date dateLivraison;
    private String noBondeLivraisonFm;

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public String getNoBondeLivraisonFm() {
        return noBondeLivraisonFm;
    }

    public void setNoBondeLivraisonFm(String noBondeLivraisonFm) {
        this.noBondeLivraisonFm = noBondeLivraisonFm;
    }

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

