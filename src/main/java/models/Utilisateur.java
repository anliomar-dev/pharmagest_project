package models;

import java.util.Date;

public class Utilisateur {
    public int id;
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

    public Utilisateur(int id, String prenom, String nom, Date dateNaissance, String email, String telephone, String adresse) {
    }

    public Utilisateur(int id, String prenom, String nom, String dateNaissance, String email, String telephone, String adresse, String identifant, Boolean superadmin) {
    }

    public Utilisateur(String prenom, String nom, String dateNaissance, String telephone, String email) {
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Constructeur
    public Utilisateur(Integer id, String prenom, String nom, Date dateNaissance,
                       String telephone, String email, String adresse, String identifiant,
                       String motDePasse, String status, boolean estSuperAdmin, Permission permission) {
        this.id = id;
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

