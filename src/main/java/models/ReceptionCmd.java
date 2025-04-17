package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReceptionCmd {

    private final SimpleStringProperty medicament;
    private final SimpleIntegerProperty stockActuel;
    private final SimpleIntegerProperty qteCmdee;
    private final SimpleIntegerProperty qteLivree;
    private final SimpleDoubleProperty prixUnitAchat;
    private final SimpleDoubleProperty ligneTotal;
    private final SimpleIntegerProperty stockApres;

    public ReceptionCmd(String medicament, int stockActuel, int qteCmdee, int qteLivree, double prixUnitAchat, double ligneTotal, int stockApres) {
        this.medicament = new SimpleStringProperty(medicament);
        this.stockActuel = new SimpleIntegerProperty(stockActuel);
        this.qteCmdee = new SimpleIntegerProperty(qteCmdee);
        this.qteLivree = new SimpleIntegerProperty(qteLivree);
        this.prixUnitAchat = new SimpleDoubleProperty(prixUnitAchat);
        this.ligneTotal = new SimpleDoubleProperty(ligneTotal);
        this.stockApres = new SimpleIntegerProperty(stockApres);
    }

    // Getters pour lier au TableView
    public String getMedicament() {
        return medicament.get();
    }

    public int getStockActuel() {
        return stockActuel.get();
    }

    public int getQteCmdee() {
        return qteCmdee.get();
    }

    public int getQteLivree() {
        return qteLivree.get();
    }

    public double getPrixUnitAchat() {
        return prixUnitAchat.get();
    }

    public double getLigneTotal() {
        return ligneTotal.get();
    }

    public int getStockApres() {
        return stockApres.get();
    }

    // Optionnel : setters si besoin
}
