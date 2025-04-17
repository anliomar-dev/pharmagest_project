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

    public Medicament(String DCI, double prixUnitVente, double prixUnitAchat) {
        this.DCI = DCI;
        this.prixUnitVente = prixUnitVente;
        this.prixUnitAchat = prixUnitAchat;
    }

    public Medicament(String DCI) {
        this.DCI = DCI;
    }

    public Medicament(String DCI, String dosage, double prixUnitVente, double prixUnitAchat, int qteStock, Forme forme, Famille famille) {
        this.DCI = DCI;
        this.dosage = dosage;
        this.prixUnitVente = prixUnitVente;
        this.prixUnitAchat = prixUnitAchat;
        this.qteStock = qteStock;
        this.forme = forme;
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "DCI='" + DCI + '\'' +
                ", dosage='" + dosage + '\'' +
                ", forme=" + forme +
                ", famille=" + famille +
                '}';
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDCI() {
        return DCI;
    }

    public void setDCI(String DCI) {
        this.DCI = DCI;
    }

    public double getPrixUnitVente() {
        return prixUnitVente;
    }

    public void setPrixUnitVente(double prixUnitVente) {
        this.prixUnitVente = prixUnitVente;
    }

    public double getPrixUnitAchat() {
        return prixUnitAchat;
    }

    public void setPrixUnitAchat(double prixUnitAchat) {
        this.prixUnitAchat = prixUnitAchat;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    public Forme getForme() {
        return forme;
    }

    public void setForme(Forme forme) {
        this.forme = forme;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    // Getters et Setters
    // (générer en conséquence)
}