package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.DatePicker;

import java.util.Date;

public class Utilisateur {
    public int id;
    private String prenom;
    private String nom;
    private String telephone;
    private String email;
    private String identifiant;
    private String motDePasse;
    private String status;
    private boolean estSuperAdmin;

    // Associations
    private Permission permission;

    //Constructeur pour ajouter un utilisateur
    public Utilisateur(String nom, String prenom, String email, String tel, String identifiant, String motDePasse, String status, Boolean estSuperAdmin) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = tel;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.status = status;
        this.estSuperAdmin = estSuperAdmin;
    }

    //Contructeur pour modifier (unfinished)
    public Utilisateur(int id, String nom, String prenom, String email, String telephone, String identifiant, Boolean estSuperAdmin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.identifiant = identifiant;
        this.estSuperAdmin = estSuperAdmin;
    }

    public Utilisateur() {

    }

    public Utilisateur(int utilisateurId, String nom, String prenom, String email, String telephone, String identifiant, String status, boolean estSuperAdmin) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.identifiant = identifiant;
        this.status = status;
        this.estSuperAdmin = estSuperAdmin;
    }

    public Utilisateur(String nom, String prenom, String email, String telephone, String identifiant, String status, boolean estSuperAdmin) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.identifiant = identifiant;
        this.status = status;
        this.estSuperAdmin = estSuperAdmin;
    }

    public int getId() {
        return id;
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
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getIdentifiant() {
        return identifiant;
    }
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isEstSuperAdmin() {
        return estSuperAdmin;
    }
    public void setEstSuperAdmin(boolean estSuperAdmin) {
        this.estSuperAdmin = estSuperAdmin;
    }
    public Permission getPermission() {
        return permission;
    }
    public void setPermission(Permission permission) {
        this.permission = permission;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }



    @Override
    public String toString() {
        return "Utilisateur{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", identifiant='" + identifiant + '\'' +
//                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }



    // Méthodes
    public void createUtilisateur() {
        // Logique pour créer un utilisateur
    }

    public void updateUtilisateur() {
        // Logique pour modifier un utilisateur
    }

    public void deleteUtilisateur() {
        // Logique pour supprimer un utilisateur
    }
}

