package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MedicamentCommande {
    private final SimpleStringProperty nom;
    private final SimpleIntegerProperty stock;
    private final SimpleIntegerProperty seuil;
    private final SimpleIntegerProperty max;
    private final SimpleIntegerProperty aCommander;
    private final SimpleDoubleProperty pu;
    private final SimpleDoubleProperty montant;
    private final SimpleStringProperty fournisseur;

    public MedicamentCommande(String nom, int stock, int seuil, int max, double pu, String fournisseur) {
        this.nom = new SimpleStringProperty(nom);
        this.stock = new SimpleIntegerProperty(stock);
        this.seuil = new SimpleIntegerProperty(seuil);
        this.max = new SimpleIntegerProperty(max);
        this.aCommander = new SimpleIntegerProperty(max - stock);
        this.pu = new SimpleDoubleProperty(pu);
        this.montant = new SimpleDoubleProperty((max - stock) * pu);
        this.fournisseur = new SimpleStringProperty(fournisseur);
    }

    public String getNom() { return nom.get(); }
    public int getStock() { return stock.get(); }
    public int getSeuil() { return seuil.get(); }
    public int getMax() { return max.get(); }
    public int getACommander() { return aCommander.get(); }
    public double getPu() { return pu.get(); }
    public double getMontant() { return montant.get(); }
    public String getFournisseur() { return fournisseur.get(); }
}
