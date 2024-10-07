package controllers;

public class MedicamentController {
    // Attributs
    private String DCI;
    private String dosage;
    private double prixUnitVente;
    private double prixUnitAchat;
    private int qteStock;

    // Constructeur
    public MedicamentController(String DCI, String dosage, double prixUnitVente, double prixUnitAchat, int qteStock) {
        this.DCI = DCI;
        this.dosage = dosage;
        this.prixUnitVente = prixUnitVente;
        this.prixUnitAchat = prixUnitAchat;
        this.qteStock = qteStock;
    }

    // Méthodes
    public void ajouterMedicament() {
        // Logique pour ajouter un médicament
    }

    public void mettreAJourStock() {
        // Logique pour mettre à jour le stock
    }
}

