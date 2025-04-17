package models;

public class Forme {
    private String nomForme;

    // Constructeur
    public Forme(String nomForme) {
        this.nomForme = nomForme;
    }

    @Override
    public String toString() {
        return nomForme;
    }

    // Getters et Setters
    public String getNomForme() {
        return nomForme;
    }

    public void setNomForme(String nomForme) {
        this.nomForme = nomForme;
    }

}

