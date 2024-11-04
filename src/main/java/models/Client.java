package models;
import java.util.Date;
public class Client {
    private String nom;
    private String prenom;
    private String civilite;
    private Date ddn; // Date de naissance

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDdn() {
        return ddn;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    // Constructeur
    public Client(String nom, String prenom, String civilite, Date ddn) {
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.ddn = ddn;
    }

}

