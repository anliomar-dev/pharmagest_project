package controllers;
import java.util.Date;

public class OrdonnanceController {
    // Attributs
    private int numOrd;
    private Date dateOrd;
    private String nomMedecin;

    // Constructeur
    public OrdonnanceController(int numOrd, Date dateOrd, String nomMedecin) {
        this.numOrd = numOrd;
        this.dateOrd = dateOrd;
        this.nomMedecin = nomMedecin;
    }

    // Méthodes
    public void creerOrdonnance() {
        // Logique pour créer une ordonnance
    }

    public void supprimerOrdonnance() {
        // Logique pour supprimer une ordonnance
    }
}

