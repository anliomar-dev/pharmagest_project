package controllers;

import java.util.Date;

public class CommandeLivraisonController {
    // Attributs
    private int numCommande;
    private Date dateCommande;
    private Date dateLivraison;
    private String noBondeLivraisonFrn;

    // Constructeur
    public CommandeLivraisonController(int numCommande, Date dateCommande, String noBondeLivraisonFrn) {
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.noBondeLivraisonFrn = noBondeLivraisonFrn;
    }

    // Méthodes
    public void creerCommande() {
        // Logique pour créer une commande
    }

    public void livrerCommande() {
        // Logique pour livrer une commande
    }
}

