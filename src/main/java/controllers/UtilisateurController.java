package controllers;

import DAO.UtilisateurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Utilisateur;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UtilisateurController {

    @FXML
    private TableView<Utilisateur> utilisateurTable;
    @FXML
    private TableColumn<Utilisateur, String> prenom_column;
    @FXML
    private TableColumn<Utilisateur, String> nom_column;
    @FXML
    private TableColumn<Utilisateur, String> telephone_column;
    @FXML
    private TableColumn<Utilisateur, String> email_column;
    @FXML
    private TableColumn<Utilisateur, String> adresse_column;
    @FXML
    private TableColumn<Utilisateur, String> identifiant_column;
    @FXML
    private TableColumn<Utilisateur, Boolean> superadmin_column;

    public void initialize() {
        // Set up cell value factories
        prenom_column.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
//            dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        telephone_column.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        email_column.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Fetch data and bind to TableView
        ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList(UtilisateurDAO.getallUtilisateurs());
        utilisateurTable.setItems(utilisateurs);
    }

    public void returnmaintenanceButtonOnAction(ActionEvent event) {

    }
}
