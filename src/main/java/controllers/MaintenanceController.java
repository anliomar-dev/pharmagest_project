package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.SessionManager;
import utils.Utils;

import java.io.IOException;

public class MaintenanceController {
    Utils sceneLoader = new Utils();

    @FXML private Label usernameLabel;

    public void initialize(){
        usernameLabel.setText("Bienvenue " + SessionManager.setUserInfo(SessionManager.getIdentifiant()));
    }

    @FXML public Button founisseurButton;
    @FXML public  Button utilisateurButton;
    @FXML public  Button majListPrixButton;
    @FXML public  Button returnButton;
    @FXML public Button medicamentButton;
    @FXML public Button formeButton;
    @FXML public Button familleButton;

    public void returnButtonOnAction(ActionEvent e) throws IOException{
        sceneLoader.loadScene("Dashboard.fxml", "Dashboard", returnButton);
    }

    public void utilisateurButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Utilisateur.fxml", "Utilisateurs", utilisateurButton);
    }

    public void founisseurButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Fournisseurs.fxml", "Maintenance founisseurs", founisseurButton);
    }

    public void majListPrixButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("ListePrix.fxml", "Listes des prix", majListPrixButton);
    }

    public void medicamentButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Medicament.fxml", "Médicament", medicamentButton);
    }

    public void formeButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Forme.fxml", "Forme", formeButton);
    }

    public void familleButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Famille.fxml", "Famille", familleButton);
    }
}
