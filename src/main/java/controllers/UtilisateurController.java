package controllers;

import DAO.FournisseurDAO;
import DAO.UtilisateurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Fournisseur;
import models.Utilisateur;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UtilisateurController {

    @FXML
    private TableView<Utilisateur> utilisateur_table;
    @FXML
    private TableColumn<Utilisateur, String> prenom_column;
    @FXML
    private TableColumn<Utilisateur, String> nom_column;
    @FXML
    private TableColumn<Utilisateur, String> numtel_column;
    @FXML
    private TableColumn<Utilisateur, String> email_column;
    @FXML
    private TableColumn<Utilisateur, String> adresse_column;
    @FXML
    private TableColumn<Utilisateur, String> identifiant_column;
    @FXML
    private TableColumn<Utilisateur, Boolean> superadmin_column;
    private final ObservableList<Utilisateur> utilisateur_table_list  = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        // Set up cell value factories
        prenom_column.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
//            dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        numtel_column.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        email_column.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Fetch data and bind to TableView

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        List<Utilisateur> utilisateurs = utilisateurDAO.getallUtilisateurs();

        // Add each fournisseur to the ObservableList
        utilisateur_table_list.addAll(utilisateurs);
    }

    public void returnmaintenanceButtonOnAction(ActionEvent event) {

    }
}
