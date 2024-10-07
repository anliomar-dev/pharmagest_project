package models;

import java.util.Date;

public class Utilisateur {
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

    // Associations
    private Permission permission;

    // Constructeur
    public Utilisateur(String prenom, String nom, Date dateNaissance, String telephone, String email, String adresse,
                       String identifiant, String motDePasse, String status, boolean estSuperAdmin, Permission permission) {
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
        this.permission = permission;
    }

    // Getters et Setters
    // (à ajouter ici)
}

