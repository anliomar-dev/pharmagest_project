package controllers;

import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
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
    // Initialisation de l'instance de Utils pour charger une nouvelle scène
    Utils sceneLoader = new Utils();
    @FXML
    public TableView<Fournisseur> tableFournisseurs;
    @FXML
    public AnchorPane newFournisseurForm;

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
    private Button AllFournisseurButton;

    @FXML
    public void initialize() {
        // display autenticated user username in sidebar
        UsernameLabel.setText(SessionManager.getIdentifiant());
        if (tableFournisseurs != null) {
            initializeTable();
        }

        if (newFournisseurForm != null) {
            initializeForm();
        }
    }

    // Initialisation du tableau des fournisseurs
    private void initializeTable() {
        // Initialisation columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tableFournisseurs.getItems().add(new Fournisseur(1, "Pays A", "Fournisseur A", "123456789", "emailA@test.com", "Adresse A"));
        tableFournisseurs.getItems().add(new Fournisseur(2, "Pays B", "Fournisseur B", "987654321", "emailB@test.com", "Adresse B"));
    }
    /**
     * method to add new fournisseur in table view
     * @param fournisseur
     */
    public void ajouterFournisseur(Fournisseur fournisseur) {
        tableFournisseurs.getItems().add(fournisseur);
    }


    // Initialisation of new fournisseur form
    private void initializeForm() {
        // Initialisation des champs du formulaire
        System.out.println("Formulaire d'ajout de fournisseur initialisé");
    }

    // switch to dashboard scene
    public void DashboardButtonOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        sceneLoader.loadScene("Dashboard.fxml", "Dashboard", DashboardButton);
    }

    // switch add new fournisseur form
    public void NewFournisseurOnAction(ActionEvent actionEvent) throws IOException {
        sceneLoader.loadScene("NewFournisseur.fxml", "Nounveau fournisseur", NewFournisseurButton);
    }

    // return to fournisseur table view
    public void AllFournisseurButtonOnAction(ActionEvent actionEvent) throws IOException {
        sceneLoader.loadScene("Fournisseurs.fxml", "Tous les fournisseurs", AllFournisseurButton);
    }
}
