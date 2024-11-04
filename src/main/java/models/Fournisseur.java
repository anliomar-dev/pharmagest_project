package models;

public class Fournisseur {
    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private String pays;

    // Constructeur
    public Fournisseur(String nom, String adresse, String telephone, String email, String pays) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.pays = pays;
    }

    // Méthodes
    public void ajouterFournisseur() {
        // Logique pour ajouter un fournisseur
    }

    public void updateFournisseur(int id) {
        // Logique pour mettre à jour un fournisseur
    }

    public void supprimerFournisseur(int id) {
        // Logique pour supprimer un fournisseur
    }
}
