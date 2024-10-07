package models;

public class Medicament {
    private String DCI;
    private String dosage;
    private double prixUnitVente;
    private double prixUnitAchat;
    private int qteStock;

    // Associations
    private Forme forme;
    private Famille famille;

    // Constructeur
    public Medicament(String DCI, String dosage, double prixUnitVente, double prixUnitAchat, int qteStock, Forme forme, Famille famille) {
        this.DCI = DCI;
        this.dosage = dosage;
        this.prixUnitVente = prixUnitVente;
        this.prixUnitAchat = prixUnitAchat;
        this.qteStock = qteStock;
        this.forme = forme;
        this.famille = famille;
    }

    // Getters et Setters
    // (générer en conséquence)
}

