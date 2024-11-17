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
import models.Fournisseur;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FournisseurController {
    // Initialization of the Utils instance to load a new scene
    Utils sceneLoader = new Utils();

    // list of fournisseur table columns
    @FXML public TableView<Fournisseur> tableFournisseurs;

    @FXML public AnchorPane newFournisseurForm;

    @FXML private TableColumn<Fournisseur, Integer> colId;

    @FXML private TableColumn<Fournisseur, String> colPays;

    @FXML private TableColumn<Fournisseur, String> colNom;

    @FXML private TableColumn<Fournisseur, String> colTelephone;

    @FXML private TableColumn<Fournisseur, String> colEmail;

    @FXML private TableColumn<Fournisseur, String> colAdresse;

    @FXML private Label UsernameLabel; // label to display authenticated user's username
    @FXML private Button DashboardButton; // button to switch to dashboard
    @FXML private Button NewFournisseurButton; // button to go to add new fournisseur form
    @FXML private Button AllFournisseurButton; // button to return to list of all fournisseur

    // filed to add new fournisseur
    @FXML private TextField paysTextField;
    @FXML private TextField NomFournisseurTextField;
    @FXML private TextField PhoneTextFIeld;
    @FXML private TextField emailTextField;
    @FXML private TextField adresseTextField;
    @FXML private Button resetNewFournisseurFormButton;
    @FXML private Button saveNewFournisseurButton;
    @FXML private Label saveNewFournisseurLabelMessage;
    @FXML private Label phoneLabelMessage;
    @FXML private Label emailLabelMessage;

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


    public void handleResetNewFournisseurForm(ActionEvent actionEvent) {
        // List of fields to cleat
        List<TextField> fields = Arrays.asList(
                paysTextField,
                NomFournisseurTextField,
                adresseTextField,
                emailTextField,
                PhoneTextFIeld);

        // cleear each field
        fields.forEach(field -> field.clear());
    }

    public void handleSaveNewFournisseur(ActionEvent actionEvent) throws SQLException {
        String pays = paysTextField.getText().trim();
        String nom = NomFournisseurTextField.getText().trim();
        String telephone = PhoneTextFIeld.getText().trim();
        String email = emailTextField.getText().trim();
        String adresse = adresseTextField.getText().trim();
        String[] data = {pays, nom, email, adresse};
        // Expression régulière pour numéro international
        String phoneRegex = "^\\+\\d{1,4}\\s?\\d{7,10}$";
        String phoneRegexLocal = "^\\d{7,10}$";
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        boolean isEmailValid = false;
        boolean isPhoneValid = false;

        boolean hasEmptyFileds = Arrays.stream(data).anyMatch(String::isEmpty);
        // check if there is an empty field
        if(hasEmptyFileds){
            saveNewFournisseurLabelMessage.setText("Tous les champs doivent être remplis !");
        }else{
            // if there is no empty field
            saveNewFournisseurLabelMessage.setText("");
            // verify if email is valid
            boolean validateEmail = Utils.validateField(email, emailRegex);
            if (!validateEmail){
                emailLabelMessage.setText("Email invalide !");
            }else{
                isEmailValid = true; // set isEmailValid to true if email is valid
            }
            // check if the fournisseur's country is maurice or mauritius
            if(Objects.equals(pays.toLowerCase(), "maurice") ||
                    Objects.equals(pays.toLowerCase(), "mauritius") ){
                boolean validateLocalPhoneNumber = Utils.validateField(telephone, phoneRegexLocal);
                boolean validatePhoneNumber = Utils.validateField(telephone, phoneRegex);
                // we can specify the country code or not if fournisseur's country is mauritius or maurice
                if (!validateLocalPhoneNumber && !validatePhoneNumber){
                    phoneLabelMessage.setText("Numéro invalide! Exemple : 54297857 ou +230 54297857");
                }else{
                    phoneLabelMessage.setText("");
                    isPhoneValid = true;
                }
            }else{
                // if fournisseur's country is not mauritius or maurice, we have to specify the country code
                phoneLabelMessage.setText("");
                boolean validatePhoneNumber = Utils.validateField(telephone, phoneRegex);
                if (!validatePhoneNumber){
                    phoneLabelMessage.setText("pour les numero non locaux, ils doivent debuter par le code du pays. exp: +230 54297857");
                }else{
                    phoneLabelMessage.setText("");
                    isPhoneValid = true;
                }
            }
            // if email and phone number are valid
            if(isEmailValid && isPhoneValid){
                Fournisseur newFournisseur = new Fournisseur(pays, nom, telephone, email, adresse);
                FournisseurDAO fournisseurDAO = new FournisseurDAO();
                fournisseurDAO.addFournisseur(newFournisseur);
                // List of fields to cleat
                List<TextField> fields = Arrays.asList(
                        paysTextField,
                        NomFournisseurTextField,
                        adresseTextField,
                        emailTextField,
                        PhoneTextFIeld);

                // cleear each field
                fields.forEach(field -> field.clear());
            }else{
                saveNewFournisseurLabelMessage.setText("certains champs sont invalides");
            }
        }

    }
}
