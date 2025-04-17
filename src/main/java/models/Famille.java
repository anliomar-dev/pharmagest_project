package models;
public class Famille {
    private int numFamille;
    private String nomFamille;

    // Constructeur
    public Famille(int numFamille, String nomFamille) {
        this.numFamille = numFamille;
        this.nomFamille = nomFamille;
    }

    public Famille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    public Famille(int numFamille) {
        this.numFamille = numFamille;
    }

    // Getters et Setters
    public int getNumFamille() {
        return numFamille;
    }

    public void setNumFamille(int numFamille) {
        this.numFamille = numFamille;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }
}
