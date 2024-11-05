package models;

public class Fournisseur {
    private Integer id;
    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private String pays;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    // Constructeur for create fournisseur
    public Fournisseur(String nom, String adresse, String telephone, String email, String pays) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.pays = pays;
    }

    // Constructeur for update, retrieve all founisseurs or delete fournisseur
    public Fournisseur(Integer id, String nom, String adresse, String telephone, String email, String pays) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.pays = pays;
    }

}
