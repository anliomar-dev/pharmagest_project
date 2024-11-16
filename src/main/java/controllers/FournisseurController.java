package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import models.Fournisseur;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class FournisseurController {
    @FXML
    public TableView<Fournisseur>tableFournisseurs;
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
    public void initialize() {
        // Initialisation des colonnes
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));


        // Exemple d'ajout de fournisseurs dans la table
        tableFournisseurs.getItems().add(new Fournisseur(1, "Pays A", "Fournisseur A", "123456789", "emailA@test.com", "Adresse A"));
        tableFournisseurs.getItems().add(new Fournisseur(2, "Pays B", "Fournisseur", "987654321", "emailB@test.com", "Adresse B"));
    }

    // Méthode pour ajouter un fournisseur (appelée ailleurs dans le code)
    public void ajouterFournisseur(Fournisseur fournisseur) {
        tableFournisseurs.getItems().add(fournisseur);
    }

}

