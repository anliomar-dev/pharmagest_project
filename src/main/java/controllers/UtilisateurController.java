package controllers;

import DAO.UtilisateurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Utilisateur;
import utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurController {
    //Button functions
    Utils sceneLoader = new Utils();

    @FXML public Button addUtilisateurPage;
    public void addUtilisateurPageButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("AddUtilisateur.fxml", "utilisateurs", addUtilisateurPage);
    }
    @FXML public Button maintenanceButton;
    public void maintenanceButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Maintenance.fxml", "utilisateurs", maintenanceButton);
    }
    @FXML public Button utilisateurButton;
    public void utilisateurButtonOnAction(ActionEvent e) throws IOException {
        sceneLoader.loadScene("Utilisateur1.fxml", "utilisateurs", utilisateurButton);
    }

    //Display in table
    @FXML private TableView<Utilisateur> utilisateurTable;
    @FXML private TableColumn<Utilisateur, Integer> idColumn;
    @FXML private TableColumn<Utilisateur, String> nomColumn;
    @FXML private TableColumn<Utilisateur, String> prenomColumn;
    @FXML private TableColumn<Utilisateur, String> emailColumn;
    @FXML private TableColumn<Utilisateur, String> telephoneColumn;
    @FXML private TableColumn<Utilisateur, String> adresseColumn;
    @FXML private TableColumn<Utilisateur, String> identifiantColumn;

    @FXML private ComboBox<String> roleComboBox;
    private final ObservableList<String> roles = FXCollections.observableArrayList("Pharmacien", "Vendeur");

    @FXML
    public void initialize() throws SQLException {
        if(roleComboBox != null) roleComboBox.setItems(roles);
        if (idColumn == null || nomColumn == null) return;
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        identifiantColumn.setCellValueFactory(new PropertyValueFactory<>("identifiant"));

        loadUtilisateurData();
    }

    private void loadUtilisateurData() throws SQLException {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        ObservableList<Utilisateur> utilisateurList = utilisateurDAO.getAllUtilisateurs();
        utilisateurTable.setItems(utilisateurList);
    }

    @FXML
    private void refreshTable() throws SQLException {
        loadUtilisateurData();
    }

    //  Textfiled
    @FXML private TextField nomAdd;
    @FXML private TextField prenomAdd;
    @FXML private TextField emailAdd;
    @FXML private TextField telephoneAdd;
    @FXML private TextField identifiantAdd;
    @FXML private TextField passwordAdd;
    @FXML private TextField passwordIdentiqueAdd;
//    @FXML private MenuButton statusAdd;
//    @FXML private MenuItem vendeurButtonOnAction;
//    @FXML private MenuItem pharmacienButtonOnAction;
//    @FXML private ToggleGroup superadminGroup;
//    @FXML private RadioButton superadminouiAdd;
//    @FXML private RadioButton superadminnonAdd;

    //  Message erros
    @FXML private Label nomAddError;
    @FXML private Label prenomAddError;
    @FXML private Label emailAddError;
    @FXML private Label telephoneAddError;
    @FXML private Label identifiantAddError;
    @FXML private Label passwordAddError;
    @FXML private Label passwordIdentiqueAddError;

    @FXML private Label occupationAdderror;
    @FXML private Label superadminAdderror;
//    @FXML private Label errortextnewutilisateur;

    //Filter regex errors
    public static boolean containsNumbers(String input) {
        return input.matches(".*\\d.*");
    }
    public static boolean containsOnlyNumbers(String input) {
        return !input.matches("^[0-9]+$");
    }
    public static boolean only8Digits(String input) {
        return !input.matches("^\\d{8}$");
    }
    public static boolean isValidField(String input) {
        return !input.matches("^[\\p{L} .'-]+$");
    }
    public static boolean isValidEmail(String input) {
        return !input.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }
    public static boolean isValidUsername(String input) {
        return !input.matches("^[a-zA-Z0-9]{3,16}$");
    }
    public static boolean isValidPassword(String input) {
        return !input.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,}$");
    }

    //Filter to add in dbb
    @FXML public Button utilisateurAddButton;
    public void utilisateurAddButtonOnAction(ActionEvent e) throws SQLException{
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        boolean isInvalid = false;

        String nom = nomAdd.getText().trim();
        String prenom = prenomAdd.getText().trim();
        String email = emailAdd.getText().trim();
        String numTel = telephoneAdd.getText().trim();
        String identifiant = identifiantAdd.getText().trim();
        String password = passwordAdd.getText().trim();
        String passwordIdentique = passwordIdentiqueAdd.getText().trim();

        if(nom.isEmpty()){
            nomAddError.setText("Entrez un nom");
            isInvalid = true;
        }else if(containsNumbers(nom) && isValidField(nom)){
            nomAddError.setText("Nom invalide !");
            isInvalid = true;
        }else{
            nomAddError.setText("");
            isInvalid = false;
        }
        if(prenom.isEmpty()){
            prenomAddError.setText("Entrez un prénom");
            isInvalid = true;
        }else if(containsNumbers(prenom) && isValidField(prenom)){
            prenomAddError.setText("Prénom invalide !");
            isInvalid = true;
        }else{
            prenomAddError.setText("");
            isInvalid = false;
        }
        if(email.isEmpty()){
            emailAddError.setText("Entrez un Email");
            isInvalid = true;
        }else if(isValidEmail(email)){
            emailAddError.setText("Email invalide !");
            isInvalid = true;
        }else{
            emailAddError.setText("");
            isInvalid = false;
        }
        if(numTel.isEmpty()){
            telephoneAddError.setText("Entrez un numéro téléphone");
            isInvalid = true;
        }else if(containsOnlyNumbers(numTel) && only8Digits(numTel)){
            telephoneAddError.setText("Numéro téléphone invalide !");
            isInvalid = true;
        }else{
            telephoneAddError.setText("");
            isInvalid = false;
        }
        if(identifiant.isEmpty()){
            identifiantAddError.setText("Entrez un identifiant");
            isInvalid = true;
        }else if(utilisateurDAO.verifyUsername(identifiant.toLowerCase())){
            identifiantAddError.setText("Identifiant existant !");
            isInvalid = true;
        }else if(isValidUsername(identifiant)){
            identifiantAddError.setText("Identifiant invalide !");
            isInvalid = true;
        }else{
            identifiantAddError.setText("");
            isInvalid = false;
        }
        if(password.isEmpty()){
            passwordAddError.setText("Entrez un mot de passe");
            isInvalid = true;
        }else if(isValidPassword(password)){
            passwordAddError.setText("Mot de passe invalide !");
            isInvalid = true;
        }else{
            passwordAddError.setText("");
            isInvalid = false;
            if(passwordIdentique.isEmpty()){
                passwordIdentiqueAddError.setText("Réntrer le mot de passe");
                isInvalid = true;
            }
            if(!passwordIdentique.matches(password)){
                passwordIdentiqueAddError.setText("Mot de passe non identique");
                isInvalid = true;
            }else{
                passwordIdentiqueAddError.setText("");
                isInvalid = false;
            }
        }

    }

//    private String selectedRole = ""; // To store the selected role
//    private boolean isSuperAdmin = false; // To store the superadmin status
//
//
//
//    @FXML
//    public void vendeurButtonOnAction(ActionEvent event) {
//        selectedRole = "Vendeur";
//        occupationAdd.setText("Vendeur");
//        occupationAdderror.setText(""); // Clear any previous isInvalid
//    }
//
//    @FXML
//    public void pharmacienButtonOnAction(ActionEvent event) {
//        selectedRole = "Pharmacien";
//        occupationAdd.setText("Pharmacien");
//        occupationAdderror.setText(""); // Clear any previous isInvalid
//    }
//
//    @FXML
//    public void handleSuperAdminToggle(ActionEvent event) {
//        if (superadminouiAdd.isSelected()) {
//            isSuperAdmin = true;
//        } else if (superadminnonAdd.isSelected()) {
//            isSuperAdmin = false;
//        }
//    }

//    public void handleutilisateurAddButton(ActionEvent e) throws SQLException {
//        String nom = nomAdd.getText().trim();
//        String prenom = prenomAdd.getText().trim();
//        String email = emailAdd.getText().trim();
//        String tel = telAdd.getText().trim();
//        String occupation = occupationAdd.getText().trim();
////        String superadminoui = superadminouiAdd.getText().trim();
////        String superadminnon = superadminnonAdd.getText().trim();
//        String adresse = adresseAdd.getText().trim();
//        String username = identifiantAdd.getText().trim();
//        String mot_de_passe = mdpAdd.getText().trim();
//        String confirm_mot_de_passe = confirmmdpAdd.getText().trim();
//        String[] data = {nom, prenom, email, adresse, username, mot_de_passe};
//        String numbersRegex = ".*\\d.*";
//        String dateRegex = "\\d{2}/\\d{2}/\\d{4}";
//        String phoneRegex = "^[0-9]{8}$";
//        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&-+=()])(?=\\S+$).{8,20}$";
//        boolean isNomValid = false;
//        boolean isPrenomValid = false;
//        boolean isDDNValid = false;
//        boolean isEmailValid = false;
//        boolean isPhoneValid = false;
//        boolean isIdentifiantValid = false;
//        boolean ismdpValid = false;
//        boolean isconfirmmdpValid = false;
//        boolean hasEmptyFileds = Arrays.stream(data).anyMatch(String::isEmpty);
//        if (hasEmptyFileds) {
//            errortextnewutilisateur.setText("Toutes les champs doivent être remplit");
//        } else {
//            errortextnewutilisateur.setText("");
//            // Check errors in TextField
//            boolean nomnumbers = Utils.validateField(nom, numbersRegex);
//            if (!nomnumbers) {
//                nomAdderror.setText("");
//                isNomValid = true;
//            } else {
//                nomAdderror.setText("Nom non valide");
//            }
//            boolean prenomnumbers = Utils.validateField(prenom, numbersRegex);
//            if (!prenomnumbers) {
//                prenomAdderror.setText("");
//                isPrenomValid = true;
//            } else {
//                prenomAdderror.setText("Prenom non valide");
//            }
//
//            boolean validmail = Utils.validateField(email, emailRegex);
//            if (!validmail) {
//                emailAdderror.setText("Email non valide !");
//            } else {
//                emailAdderror.setText("");
//                isEmailValid = true;
//            }
//            boolean validtel = Utils.validateField(tel, phoneRegex);
//            if (!validtel) {
//                numtelAdderror.setText("Numéro téléphone non valide !");
//            } else {
//                numtelAdderror.setText("");
//                isPhoneValid = true;
//            }
//            String query = "SELECT * FROM public.\"utilisateur\" WHERE \"identifiant\" = ?";
//            try (Connection connection = DatabaseConnection.getConnection();
//                 PreparedStatement statement = connection.prepareStatement(query)) {
//                statement.setString(1, username);
//                ResultSet resultSet = statement.executeQuery();
//                if (resultSet.next()) {
//                    identifiantAdderror.setText("Identifiant pris");
//                } else {
//                    identifiantAdderror.setText("");
//                    isIdentifiantValid = true;
//                }
//            } catch (Exception f) {
//                f.printStackTrace();
//            }
//            boolean validmdp = Utils.validateField(mot_de_passe, passwordRegex);
//            if (!validmdp) {
//                mdpAdderror.setText("Le mot de passe doit contenir au moins de 8 caractère avec au moins un nombre, une minuscule, un majuscule et un caractère spéciale");
//            } else {
//                mdpAdderror.setText("");
//                ismdpValid = true;
//            }
//            if (!mot_de_passe.equals(confirm_mot_de_passe)) {
//                confirmmdpAdderror.setText("Mot de passe non identique !");
//            } else {
//                confirmmdpAdderror.setText("");
//                isconfirmmdpValid = true;
//            }
//            if (isEmailValid && isPhoneValid && isIdentifiantValid && ismdpValid && isconfirmmdpValid) {
//                Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, tel, adresse, username, mot_de_passe, selectedRole, isSuperAdmin);
//                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
//                utilisateurDAO.AddUtilisateur(newUtilisateur);
//                List<Field> fields = Arrays.asList(
//                        nomAdd,
//                        prenomAdd,
//                        emailAdd,
//                        telAdd,
//                        adresseAdd,
//                        identifiantAdd,
//                        mdpAdd,
//                        confirmmdpAdd
//                );
//                fields.forEach(field -> field.clear());
//            } else {
//                errortextnewutilisateur.setText("Certains champs sont invalides");
//            }
//        }
//    }
//    @FXML
//    public void superadminouiAddButtonOnAction(ActionEvent event) {
//        superadminAdderror.setText("Vendeur");
//    }
//    public void superadminnonAddButtonOnAction(ActionEvent event) {
//        superadminAdderror.setText("Pharmacien");
//    }
//    @FXML
//    public void initialize() {
//        superadminGroup = new ToggleGroup();
//        superadminouiAdd.setToggleGroup(superadminGroup);
//        superadminnonAdd.setToggleGroup(superadminGroup);
//    }
//
//    @FXML
//    public void handleValidation(ActionEvent event) {
//        if (superadminGroup.getSelectedToggle() == null) {
//            superadminAdderror.setText("Please select either 'Vendeur' or 'Pharmacien'.");
//        } else {
//            RadioButton selectedButton = (RadioButton) superadminGroup.getSelectedToggle();
//            superadminAdderror.setText(selectedButton.getText());
//        }
//    }
}
