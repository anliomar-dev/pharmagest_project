package controllers;

import DAO.UtilisateurDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Utilisateur;
import utils.Utils;
import utils.ValidationUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class UtilisateurController {
    Utils sceneLoader = new Utils();

    //Button functions
    @FXML public Button utilisateurAddButton;
    @FXML public Button utilisateurModifierButton;

    @FXML public Button maintenanceButton;
    public void maintenanceButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Maintenance.fxml", "Maintenance", maintenanceButton);
    }

    @FXML public Button utilisateurAnnulerButton;
    public void utilisateurAnnulerButtonOnAction(ActionEvent e) throws IOException {
        clearForm();
    }

    //Table columns
    @FXML private TableView<Utilisateur> utilisateurTable;
    @FXML private TableColumn<Utilisateur, String> nomColumn;
    @FXML private TableColumn<Utilisateur, String> prenomColumn;
    @FXML private TableColumn<Utilisateur, String> emailColumn;
    @FXML private TableColumn<Utilisateur, String> telephoneColumn;
    @FXML private TableColumn<Utilisateur, String> identifiantColumn;
    @FXML private TableColumn<Utilisateur, String> roleColumn;
    @FXML private TableColumn<Utilisateur, String> adminColumn;

    //ComboBox
    @FXML private ComboBox<String> roleComboBox;
    @FXML private ComboBox<String> adminComboBox;
    @FXML private ComboBox<String> searchComboBox;

    //ComboBox values
    private final ObservableList<String> roles = FXCollections.observableArrayList("Pharmacien", "Vendeur");
    private final ObservableList<String> admin = FXCollections.observableArrayList("Admin", "Employé");
    private final ObservableList<String> columns = FXCollections.observableArrayList("Nom", "Prénom", "Email", "Téléphone", "Identifiant");
    private String originalUsername;

    //Initialize
    @FXML
    public void initialize() throws SQLException {
        if (utilisateurTable != null && searchComboBox != null && roleComboBox != null && adminComboBox != null) {

            //Set up ComboBox
            searchComboBox.setItems(columns);
            roleComboBox.setItems(roles);
            adminComboBox.setItems(admin);

            //Table columns
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            identifiantColumn.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
            roleColumn.setCellValueFactory(new PropertyValueFactory<>("status"));adminColumn.setCellValueFactory(cellData -> {
                boolean isAdmin = cellData.getValue().isEstSuperAdmin();
                return new SimpleStringProperty(isAdmin ? "Admin" : "Employé");
            });

            utilisateurTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    originalUsername = newSelection.getIdentifiant();

                    nomField.setText(newSelection.getNom());
                    prenomField.setText(newSelection.getPrenom());
                    emailField.setText(newSelection.getEmail());
                    telephoneField.setText(newSelection.getTelephone());
                    adminComboBox.setValue(newSelection.isEstSuperAdmin() ? "Admin" : "Employé");
                    roleComboBox.setValue(String.valueOf(newSelection.getStatus()));
                    identifiantField.setText(String.valueOf(newSelection.getIdentifiant()));
                }
            });
            loadUtilisateurData();
        }
    }

    public boolean isRowSelected() {
        return utilisateurTable.getSelectionModel().getSelectedItem() != null;
    }

    private ObservableList<Utilisateur> utilisateurList = FXCollections.observableArrayList();

    //Load the table
    private void loadUtilisateurData() throws SQLException {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurList = utilisateurDAO.getAllUtilisateurs();
        utilisateurTable.setItems(utilisateurList);
    }

    //Search forms
    @FXML private TextField searchField;
    @FXML private Label searchError;
    
    //Handling search
    public void searchButtonOnAction(ActionEvent e) throws SQLException {

        ObservableList<Utilisateur> filteredList = FXCollections.observableArrayList();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurList = utilisateurDAO.getAllUtilisateurs();

        String search = searchField.getText().trim();
        String lowerCaseFilter = search.toLowerCase();

        if(search.isEmpty() || searchComboBox.getValue() == null){
            loadUtilisateurData();
        } else {
            String selectedComboBox = searchComboBox.getValue().trim();
            if(selectedComboBox.isEmpty()){
                searchError.setText("Choisir une colonne");
            }else{
                for (Utilisateur utilisateur : utilisateurList) {
                    boolean matches = false;

                    switch (selectedComboBox) {
                        case "Nom":
                            matches = utilisateur.getNom().toLowerCase().contains(lowerCaseFilter);
                            break;
                        case "Prénom":
                            matches = utilisateur.getPrenom().toLowerCase().contains(lowerCaseFilter);
                            break;
                        case "Email":
                            matches = utilisateur.getEmail().toLowerCase().contains(lowerCaseFilter);
                            break;
                        case "Téléphone":
                            matches = utilisateur.getTelephone().toLowerCase().contains(lowerCaseFilter);
                            break;
                        case "Identifiant":
                            matches = utilisateur.getIdentifiant().toLowerCase().contains(lowerCaseFilter);
                            break;
                    }
                    if (matches) {
                        filteredList.add(utilisateur);
                    }
                }
            }
            utilisateurTable.setItems(filteredList);
        }
    }

    //Annuler search
    public void searchButtonAnnulerOnAction(ActionEvent e) throws SQLException{
        searchComboBox.getSelectionModel().clearSelection();
        searchField.clear();
        loadUtilisateurData();
    }

    //  TextField
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private TextField telephoneField;
    @FXML private TextField identifiantField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField passwordIdentiqueField;

    //  Message erros
    @FXML private Label nomFieldError;
    @FXML private Label prenomFieldError;
    @FXML private Label emailFieldError;
    @FXML private Label telephoneFieldError;
    @FXML private Label roleFieldError;
    @FXML private Label adminFieldError;
    @FXML private Label identifiantFieldError;
    @FXML private Label passwordFieldError;
    @FXML private Label passwordIdentiqueFieldError;

    //Filter regex errors
    public static boolean isValidUsername(String input) {
        return !input.matches("^[a-zA-Z0-9]{3,16}$");
    }

    //Handling adding utilisateur
    public void utilisateurAddButtonOnAction(ActionEvent e) throws SQLException {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        boolean isInvalid = false;
        boolean isAdmin = false;

        // Accumulate validation errors
        if (ValidationUtils.validateName(nomField, nomFieldError)) isInvalid = true;
        if (ValidationUtils.validateName(prenomField, prenomFieldError)) isInvalid = true;
        if (ValidationUtils.validateEmail(emailField, emailFieldError)) isInvalid = true;
        if (ValidationUtils.validatePhone(telephoneField, telephoneFieldError)) isInvalid = true;
        if (validateIdentifiant()) {
            isInvalid = true;
        }else{
            if (ValidationUtils.validatePassword(passwordField, passwordIdentiqueField, passwordFieldError, passwordIdentiqueFieldError)) isInvalid = true;
        }

        if (roleComboBox.getValue() == null) {
            roleFieldError.setText("Choisir un rôle");
            isInvalid = true;
        } else {
            roleFieldError.setText("");
        }

        if (adminComboBox.getValue() != null) {
            String selectedAdmin = adminComboBox.getValue().trim();
            isAdmin = selectedAdmin.equals("Admin");
            adminFieldError.setText("");
        } else {
            adminFieldError.setText("Choisir un statut");
            isInvalid = true;
        }

        if (!isInvalid) {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String email = emailField.getText().trim();
            String tel = telephoneField.getText().trim();
            String identifiant = identifiantField.getText().trim();
            String password = Utils.hashWithSHA256((passwordField).getText().trim());
            String role = roleComboBox.getValue().trim();

            Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, tel, identifiant, password, role, isAdmin);
            if (utilisateurDAO.addUtilisateur(newUtilisateur)) {
                loadUtilisateurData();
                clearForm();
            }
        }
    }

    //Valdiate identifiant private
    private boolean validateIdentifiant() throws SQLException {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        String identifiant = identifiantField.getText().trim();
        if (identifiant.isEmpty()) {
            identifiantFieldError.setText("Entrez un identifiant");
            return true;
        } else if (utilisateurDAO.verifyUsername(identifiant.toLowerCase())) {
            identifiantFieldError.setText("Identifiant existant !");
            return true;
        } else if (isValidUsername(identifiant)) {
            identifiantFieldError.setText("Identifiant invalide !");
            return true;
        }
        identifiantFieldError.setText("");
        return false;
    }

    public void utilisateurModifierButtonOnAction(ActionEvent e) throws SQLException{
        if(isRowSelected()) {
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            boolean isInvalid = false;
            boolean isAdmin = false;

            // Accumulate validation errors
            if (ValidationUtils.validateName(nomField, nomFieldError)) isInvalid = true;
            if (ValidationUtils.validateName(prenomField, prenomFieldError)) isInvalid = true;
            if (ValidationUtils.validateEmail(emailField, emailFieldError)) isInvalid = true;
            if (ValidationUtils.validatePhone(telephoneField, telephoneFieldError)) isInvalid = true;

            if (Objects.equals(identifiantField.getText(), originalUsername)) {
                identifiantFieldError.setText("");
            } else {
                if (validateIdentifiant()) isInvalid = true;
            }

            if (passwordField.getText().isEmpty()) {
                passwordFieldError.setText("");
                passwordIdentiqueFieldError.setText("");
            } else {
                if (ValidationUtils.validatePassword(passwordField, passwordIdentiqueField, passwordFieldError, passwordIdentiqueFieldError))
                    isInvalid = true;
            }

            if (roleComboBox.getValue() == null) {
                roleFieldError.setText("Choisir un rôle");
                isInvalid = true;
            } else {
                roleFieldError.setText("");
            }

            if (adminComboBox.getValue() != null) {
                String selectedAdmin = adminComboBox.getValue().trim();
                isAdmin = selectedAdmin.equals("Admin");
                adminFieldError.setText("");
            } else {
                adminFieldError.setText("Choisir un statut");
                isInvalid = true;
            }

            if (!isInvalid) {
                String nom = nomField.getText().trim();
                String prenom = prenomField.getText().trim();
                String email = emailField.getText().trim();
                String tel = telephoneField.getText().trim();
                String identifiant = identifiantField.getText().trim();
                String role = roleComboBox.getValue().trim();
                String password = Utils.hashWithSHA256((passwordField).getText().trim());

                Utilisateur modifierUtilisateur = new Utilisateur(nom, prenom, email, tel, identifiant, password, role, isAdmin);
                int id = utilisateurDAO.getUtilisateurId(identifiant);
                if (id > 0) {
                    if (utilisateurDAO.modifierUtilisateur(id, modifierUtilisateur)) {
                        loadUtilisateurData();
                        clearForm();
                    }
                }
            }
        }else{
            nomFieldError.setText("Choisir un utilisateur");
        }
    }

    //Delete utilisateur
    public void supprimerMedicamentButtonOnAction(ActionEvent e) throws SQLException{
        if(isRowSelected()) {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String username = identifiantField.getText().trim();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer utilisateur");
            alert.setHeaderText("Vous voulez supprimer l'utilisateur : " + prenom + " " + nom);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                int id = utilisateurDAO.getUtilisateurId(username);
                if (id > 0) {
                    utilisateurDAO.deleteUtilisateur(id);
                    loadUtilisateurData();
                    clearForm();
                }
            }else{
                loadUtilisateurData();
                clearForm();
            }
        }else{
            searchError.setText("Choisir un utilisateur");
        }
    }


    private void clearForm() {
        utilisateurTable.getSelectionModel().clearSelection();

        // Clear text fields
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        telephoneField.clear();
        identifiantField.clear();
        passwordField.clear();
        passwordIdentiqueField.clear();

        // Clear combo box selections
        roleComboBox.getSelectionModel().clearSelection();
        adminComboBox.getSelectionModel().clearSelection();

        // Clear error labels
        nomFieldError.setText("");
        prenomFieldError.setText("");
        emailFieldError.setText("");
        telephoneFieldError.setText("");
        identifiantFieldError.setText("");
        passwordFieldError.setText("");
        passwordIdentiqueFieldError.setText("");
        roleFieldError.setText("");
        adminFieldError.setText("");
        searchError.setText("");
    }
}
