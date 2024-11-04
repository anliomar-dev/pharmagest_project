package models;

public class LigneVendu {
    private int qteVendue;

    // Association
    private Medicament medicament;
    private Vente vente;

    // Constructeur
    public LigneVendu(int qteVendue, Medicament medicament, Vente vente) {
        this.qteVendue = qteVendue;
        this.medicament = medicament;
        this.vente = vente;
    }

    // Getters et Setters
    public int getQteVendue() {
        return qteVendue;
    }

    public void setQteVendue(int qteVendue) {
        this.qteVendue = qteVendue;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }
}