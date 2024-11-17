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
import DAO.FournisseurDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FournisseurController {
    // Initialization of the Utils instance to load a new scene
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

    // Declaration of ObservableList for Fournisseurs
    private final ObservableList<Fournisseur> fournisseurObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        // Display authenticated user's username in the sidebar
        UsernameLabel.setText(SessionManager.getIdentifiant());

        if (tableFournisseurs != null) {
            initializeTable();
        }

        if (newFournisseurForm != null) {
            initializeForm();
        }
    }

    // Initialize the fournisseurs table
    private void initializeTable() throws SQLException {
        // Initializing columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        // Bind the TableView to the ObservableList
        tableFournisseurs.setItems(fournisseurObservableList);

        // Load the fournisseurs from the database
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        List<Fournisseur> fournisseurs = fournisseurDAO.getAllFounisseur();

        // Add each fournisseur to the ObservableList
        fournisseurObservableList.addAll(fournisseurs);
    }

    /**
     * Method to add a new fournisseur to the TableView
     * @param fournisseur
     */
    public void addFournisseurToTableView(Fournisseur fournisseur) {
        fournisseurObservableList.add(fournisseur); // Add to ObservableList, TableView will automatically update
    }

    // Initialize the form for adding a new fournisseur
    private void initializeForm() {
        System.out.println("New fournisseur form initialized");
    }

    // Switch to the dashboard scene
    public void DashboardButtonOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        sceneLoader.loadScene("Dashboard.fxml", "Dashboard", DashboardButton);
    }

    // Switch to the add new fournisseur form
    public void NewFournisseurOnAction(ActionEvent actionEvent) throws IOException {
        sceneLoader.loadScene("NewFournisseur.fxml", "New fournisseur", NewFournisseurButton);
    }

    // Return to the fournisseur table view
    public void AllFournisseurButtonOnAction(ActionEvent actionEvent) throws IOException {
        sceneLoader.loadScene("Fournisseurs.fxml", "All fournisseurs", AllFournisseurButton);
    }
}
