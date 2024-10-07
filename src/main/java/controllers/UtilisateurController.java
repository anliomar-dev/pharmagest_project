package controllers;

import java.util.Date;

public class UtilisateurController {
    // Attributs
    private String prenom;
    private String nom;
    private Date dateNaissance;
    private String telephone;
    private String email;
    private String adresse;
    private String identifiant;
    private String motDePasse;
    private String status;
    private boolean estSuperAdmin;

    // Constructeur
    public UtilisateurController(String prenom, String nom, Date dateNaissance, String telephone, String email, String adresse, String identifiant, String motDePasse, String status, boolean estSuperAdmin) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.status = status;
        this.estSuperAdmin = estSuperAdmin;
    }

    // Méthodes
    public void creerUtilisateur() {
        // Logique pour créer un utilisateur
    }

    public void modifierUtilisateur() {
        // Logique pour modifier un utilisateur
    }

    public void supprimerUtilisateur() {
        // Logique pour supprimer un utilisateur
    }
}
