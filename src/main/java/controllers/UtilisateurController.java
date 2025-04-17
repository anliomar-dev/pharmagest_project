package controllers;

import DAO.UtilisateurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import models.Utilisateur;
import utils.Utils;
import utils.ValidationUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UtilisateurController {
    Utils sceneLoader = new Utils();

    //Button functions
    @FXML public Button addUtilisateurPage;
    @FXML public Button utilisateurAddButton;
    @FXML public Button utilisateurModifierButton;

    public void addUtilisateurPageButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("FormUtilisateur.fxml", "Ajouter un utilisateur", addUtilisateurPage);
    }

    @FXML
    public Button maintenanceButton;

    public void maintenanceButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Maintenance.fxml", "Maintenance", maintenanceButton);
    }

    @FXML
    public Button utilisateurRetourButton;

    public void utilisateurRetourButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Utilisateur.fxml", "Gérer les utilisateurs", utilisateurRetourButton);
    }

    @FXML
    public Button utilisateurAnnulerButton;

    public void utilisateurAnnulerButtonOnAction(ActionEvent e) throws IOException {
        clearForm();
    }

    //Table columns
    @FXML
    private TableView<Utilisateur> utilisateurTable;
    @FXML
    private TableColumn<Utilisateur, String> nomColumn;
    @FXML
    private TableColumn<Utilisateur, String> prenomColumn;
    @FXML
    private TableColumn<Utilisateur, String> emailColumn;
    @FXML
    private TableColumn<Utilisateur, String> telephoneColumn;
    @FXML
    private TableColumn<Utilisateur, String> identifiantColumn;
    @FXML
    private TableColumn<Utilisateur, Void> actionsColumn;

    //ComboBox
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private ComboBox<String> adminComboBox;
    @FXML
    private ComboBox<String> searchComboBox;

    //ComboBox values
    private final ObservableList<String> roles = FXCollections.observableArrayList("Pharmacien", "Vendeur");
    private final ObservableList<String> admin = FXCollections.observableArrayList("Admin", "Employé");
    private final ObservableList<String> columns = FXCollections.observableArrayList("Nom", "Prénom", "Email", "Téléphone", "Identifiant");

    //Initialize
    @FXML
    public void initialize() throws SQLException {
        if (utilisateurTable != null && searchComboBox != null) {
            initializeTable();
        }
        if (roleComboBox != null && adminComboBox != null) {
            initializeForm();
        }
    }

    //Initialize table
    private void initializeTable() throws SQLException {
        //ComboBox
        searchComboBox.setItems(columns);
        //Table columns
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        identifiantColumn.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
        actionsColumn.setCellFactory(column -> new TableCell<>() {
            private final Button modifyBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            private final HBox buttons = new HBox(5, modifyBtn, deleteBtn);

            {
                buttons.setAlignment(Pos.CENTER);
                modifyBtn.setOnAction(event -> {
                    Utilisateur utilisateur = getTableView().getItems().get(getIndex());
                    try {
                        modifierDisplayUtilisateurForm(utilisateur);
                        sceneLoader.loadScene("FormUtilisateur.fxml", "Modifier utilisateur", modifyBtn);
                        utilisateurAddButton.setVisible(false);
                        utilisateurModifierButton.setVisible(true);
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                deleteBtn.setOnAction(event -> {
                    Utilisateur user = getTableView().getItems().get(getIndex());
                    try {
                        deleteUser(user);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : buttons);
            }
        });
        loadUtilisateurData();
    }
    //Initialize utilisateur form
    private void initializeForm() {
        roleComboBox.setItems(roles);
        adminComboBox.setItems(admin);
    }

    private ObservableList<Utilisateur> utilisateurList = FXCollections.observableArrayList();
    //Load the table
    private void loadUtilisateurData() throws SQLException {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurList = utilisateurDAO.getAllUtilisateurs();
        utilisateurTable.setItems(utilisateurList);
    }

    //Search forms
    @FXML
    private TextField searchField;
    @FXML
    private Label searchError;
    
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
            String selectedColumn = searchComboBox.getValue().trim();
            for (Utilisateur utilisateur : utilisateurList) {
                boolean matches = false;

                switch (selectedColumn) {
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

    //Handling modify utilisateur
    public void modifierDisplayUtilisateurForm(Utilisateur utilisateur) throws SQLException{
        System.out.println(utilisateur);
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
        if (ValidationUtils.validatePassword(passwordField, passwordIdentiqueField, passwordFieldError, passwordIdentiqueFieldError)) isInvalid = true;
        if (validateIdentifiant()) isInvalid = true;

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
                clearForm();
                try {
                    sceneLoader.loadScene("Utilisateur.fxml", "utilisateurs", utilisateurRetourButton);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

    //Delete utilisateur
    private void deleteUser(Utilisateur user) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer utilisateur");
        alert.setHeaderText("Supprimer l'utilisateur : " + user.getNom() + " " + user.getPrenom());
        alert.setContentText("Are you sure you want to delete this user?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

            utilisateurDAO.deleteUtilisateur(user.getId());
            loadUtilisateurData();
        }
    }

    //  TextField for modifier
    @FXML private TextField nomModifier;
    @FXML private TextField prenomModifier;
    @FXML private TextField emailModifier;
    @FXML private TextField telephoneModifier;
    @FXML private TextField identifiantModifier;
    @FXML private PasswordField passwordModifier;
    @FXML private PasswordField passwordIdentiqueModifier;

    //  Message erros
    @FXML private Label nomModifierError;
    @FXML private Label prenomModifierError;
    @FXML private Label emailModifierError;
    @FXML private Label telephoneModifierError;
    @FXML private Label roleModifierError;
    @FXML private Label adminModifierError;
    @FXML private Label identifiantModifierError;
    @FXML private Label passwordModifierError;
    @FXML private Label passwordIdentiqueModifierError;

    private Utilisateur user;

    public void setUser(Utilisateur user){
        this.user = user;
        nomModifier.setText(user.getNom());
        prenomModifier.setText(user.getPrenom());
        emailModifier.setText(user.getEmail());
        telephoneModifier.setText(user.getTelephone());
        identifiantModifier.setText(user.getIdentifiant());
    }

    private void clearForm() {
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
    }
}
