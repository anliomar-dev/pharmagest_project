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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    // Constructeur for create fournisseur
    public Fournisseur(String pays, String nom, String telephone, String email, String adresse) {
        this.nom = nom;
        this.pays = pays;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    // Constructeur for update, retrieve all founisseurs or delete fournisseur
    public Fournisseur(Integer id, String pays, String nom, String telephone, String email, String adresse) {
        this.id = id;
        this.pays = pays;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;

    }


    //
    @Override
    public String toString() {
        return "Fournisseur{" +
                "id=" + id +
                ", pays='" + pays + '\'' +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }

}
