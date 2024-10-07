package models;

public class Permission {
    private String nomPermission;

    // Constructeur
    public Permission(String nomPermission) {
        this.nomPermission = nomPermission;
    }

    // Getters et Setters
    public String getNomPermission() {
        return nomPermission;
    }

    public void setNomPermission(String nomPermission) {
        this.nomPermission = nomPermission;
    }
}

