package controllers;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import utils.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import models.Fournisseur;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.SessionManager;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;

public class FournisseurController {

    // Variable pour savoir si on est dans la scène Fournisseur ou NewFournisseur
    private boolean isFournisseurSceneActive = true;

    @FXML
    public TableView<Fournisseur> tableFournisseurs;

    @FXML
    private TableColumn<Fournisseur, Integer> colId;

    @FXML
    private TableColumn<Fournisseur, String> colPays;

    @FXML
    private TableColumn<Fournisseur, String> colNom;

    @FXML
    private TableColumn<Fournisseur, String> colTelephone;

    @FXML
    private TableColumn<Fournisseur, String> colEmail;

    @FXML
    private TableColumn<Fournisseur, String> colAdresse;

    @FXML
    private Label UsernameLabel;

    @FXML
    private Button DashboardButton;

    @FXML
    private Button NewFournisseurButton;

    @FXML
    public void initialize() {
        // Si on est dans la scène Fournisseur, on initialise toutes les colonnes
        if (isFournisseurSceneActive) {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colPays.setCellValueFactory(new PropertyValueFactory<>("pays"));
            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

            // Exemple d'ajout de fournisseurs dans la table
            tableFournisseurs.getItems().add(new Fournisseur(1, "Pays A", "Fournisseur A", "123456789", "emailA@test.com", "Adresse A"));
            tableFournisseurs.getItems().add(new Fournisseur(2, "Pays B", "Fournisseur B", "987654321", "emailB@test.com", "Adresse B"));
        } else {
            // Si on est dans NewFournisseur, on cache certaines colonnes
            colId.setVisible(false);    // Masquer la colonne "Id"
            colPays.setVisible(false);  // Masquer la colonne "Pays"
            colNom.setVisible(false);   // Masquer la colonne "Nom"
            colTelephone.setVisible(false);  // Masquer la colonne "Téléphone"
            colEmail.setVisible(false);      // Masquer la colonne "Email"
            colAdresse.setVisible(false);    // Masquer la colonne "Adresse"
        }

        // Affichage du nom d'utilisateur
        UsernameLabel.setText(SessionManager.getIdentifiant());
    }

    public void setFournisseurSceneActive(boolean isActive) {
        this.isFournisseurSceneActive = isActive;
    }

    // Méthode pour ajouter un fournisseur dans la TableView
    public void ajouterFournisseur(Fournisseur fournisseur) {
        tableFournisseurs.getItems().add(fournisseur);
    }

    public void DashboardButtonOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        // Initialisation de l'instance de Utils pour charger une nouvelle scène
        Utils sceneLoader = new Utils();
        // Changer de scène pour le Dashboard après avoir cliqué sur le bouton
        sceneLoader.loadScene("Dashboard.fxml", "Dashboard", DashboardButton);
    }

    public void NewFournisseurOnAction(ActionEvent actionEvent) throws IOException {
        setFournisseurSceneActive(false); // Activer la scène NewFournisseur
        Utils sceneLoader = new Utils();
        sceneLoader.loadScene("NewFournisseur.fxml", "New fournisseur", NewFournisseurButton);
    }
}
