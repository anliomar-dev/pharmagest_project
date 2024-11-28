package controllers;

import DAO.FournisseurDAO;
import DAO.UtilisateurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Fournisseur;
import models.Utilisateur;
import utils.Utils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UtilisateurController {
    @FXML private TextField nomadd;
    @FXML private TextField prenomadd;
    @FXML private TextField emailadd;
    @FXML private TextField teladd;
    @FXML private TextField adresseadd;
    @FXML private TextField identifiantadd;
    @FXML private TextField mdpadd;
    @FXML private TextField confirmmdpadd;
    @FXML private Label errortextnewutilisateur;
    @FXML private Label emailerror;
    @FXML private Label telvalid;
    @FXML private Label mdperror;
    @FXML private Label confirmmdperror;

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
    //private final ObservableList<Utilisateur> utilisateur_table_list  = FXCollections.observableArrayList();

//    public void initialize() throws SQLException {
//        // Set up cell value factories
//        prenom_column.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
////            dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
//        numtel_column.setCellValueFactory(new PropertyValueFactory<>("telephone"));
//        email_column.setCellValueFactory(new PropertyValueFactory<>("email"));
//
//        // Fetch data and bind to TableView
//
//        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
//        List<Utilisateur> utilisateurs = utilisateurDAO.getallUtilisateurs();
//
//        // Add each fournisseur to the ObservableList
//        utilisateur_table_list.addAll(utilisateurs);
//        utilisateur_table.setItems(utilisateur_table_list);
//
//    }

    public void returnmaintenanceButtonOnAction(ActionEvent event) {

    }
    public void handleutilisateuraddButton (ActionEvent e) throws SQLException{
        String nom = nomadd.getText().trim();
        String prenom = prenomadd.getText().trim();
        String email = emailadd.getText().trim();
        String tel = teladd.getText().trim();
        String adresse = adresseadd.getText().trim();
        String identifiant = identifiantadd.getText().trim();
        String mot_de_passe = mdpadd.getText().trim();
        String confirm_mot_de_passe = confirmmdpadd.getText().trim();
        String[] data = {nom, prenom, email, adresse, identifiant, mot_de_passe};
        String phoneRegex = "^[0-9]{8}$";
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&-+=()])(?=\\S+$).{8,20}$";
        boolean isEmailValid = false;
        boolean isPhoneValid = false;
        boolean ismdpValid = false;
        boolean isconfirmmdpValid = false;
        boolean hasEmptyFileds = Arrays.stream(data).anyMatch(String::isEmpty);
        if(hasEmptyFileds){
            errortextnewutilisateur.setText("Toutes les champs doivent être remplit");
        }else{
            errortextnewutilisateur.setText("");
            boolean validmail = Utils.validateField(email, emailRegex);
            if(!validmail){
                emailerror.setText("Email non valide !");
            }else{
                emailerror.setText("");
                isEmailValid = true;
            }
            boolean validtel = Utils.validateField(tel, phoneRegex);
            if(!validtel){
                telvalid.setText("Numéro téléphone non valide !");
            }else{
                telvalid.setText("");
                isPhoneValid = true;
            }
            boolean validmdp = Utils.validateField(mot_de_passe, passwordRegex);
            if(!validmdp){
                mdperror.setText("Le mot de passe doit contenir au moins de 8 caractère avec au moins un nombre, une minuscule, un majuscule et un caractère spéciale");
            }else {
                mdperror.setText("");
                ismdpValid = true;
            }
            if(!mot_de_passe.equals(confirm_mot_de_passe)){
                confirmmdperror.setText("Mot de passe non identique !");
            }else{
                confirmmdperror.setText("");
                isconfirmmdpValid = true;
            }
            if(isEmailValid && isPhoneValid && ismdpValid && isconfirmmdpValid){
                Utilisateur newUtilisateur = new Utilisateur(nom, prenom, email, tel, adresse, identifiant, mot_de_passe);
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                utilisateurDAO.addUtilisateur(newUtilisateur);
                List<TextField> fields = Arrays.asList(
                        nomadd,
                        prenomadd,
                        emailadd,
                        teladd,
                        adresseadd,
                        identifiantadd,
                        mdpadd,
                        confirmmdpadd
                );
                fields.forEach(field -> field.clear());
            }else{
                errortextnewutilisateur.setText("Certains champs sont invalides");
            }
        }
    }


}
