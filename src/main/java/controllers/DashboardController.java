package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.SessionManager;
import utils.Utils;
import models.Utilisateur;

import java.io.IOException;

public class DashboardController {
    public Button leaveButton;
    Utils sceneLoader = new Utils();


    @FXML private Label usernameLabel;

    public void initialize(){
        usernameLabel.setText("Bienvenue " + SessionManager.setUserInfo(SessionManager.getIdentifiant()));
    }

    @FXML private Button maintenanceButton;
    public void maintenanceButtonOnAction(ActionEvent event) throws IOException {
        sceneLoader.loadScene("Maintenance.fxml", "Maintenance", maintenanceButton);
    }

    @FXML private Button venteButton;
    public void venteButtonOnAction(ActionEvent event) throws IOException {
        sceneLoader.loadScene("Vente.fxml", "Vente", venteButton);
    }

    @FXML private Button caisseButton;
    public void caisseButtonOnAction(ActionEvent event) throws IOException {
        sceneLoader.loadScene("Caisse.fxml", "Caisse", caisseButton);
    }

    @FXML private Button commandeButton;
    public void commandeButtonOnAction(ActionEvent event) throws IOException {
        sceneLoader.loadScene("commande.fxml", "Commande", commandeButton);
    }

    @FXML private Button receptionCmdButton;
    public void setReceptionCmdButtonOnAction(ActionEvent event) throws IOException {
        sceneLoader.loadScene("receptionCommande.fxml", "Reception des commandes", receptionCmdButton);
    }

    @FXML private Button changeButton;
    public void changeButtonOnAction(ActionEvent event) throws IOException {
        sceneLoader.loadScene("login.fxml", "Login", changeButton);
    }


    public void leaveButtonOnAction(ActionEvent event) { System.exit(0); }
}
