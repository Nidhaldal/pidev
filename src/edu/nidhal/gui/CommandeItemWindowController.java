/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.nidhal.entities.Commande;
import edu.nidhal.services.CommandeCRUD;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CommandeItemWindowController {
    @FXML
    private TableView<Commande> commandeTableView;
    @FXML
    private TableColumn<Commande, Integer> idcColumn;
    @FXML
    private TableColumn<Commande, Float> montantColumn;
    
    @FXML
    private TableColumn<Commande, String> modePayColumn;
    @FXML
    private TableColumn<Commande, String> dateColumn;
    private TextField idcField;
    private TextField montantField;
    private TextField modePayField;
    private TextField dateField;

    private CommandeCRUD commandeCRUD;
    @FXML
    private Button btneff;

    public CommandeItemWindowController() {
        commandeCRUD = new CommandeCRUD();
    }

    public void initialize() {
        // Initialize the TableView columns
        idcColumn.setCellValueFactory(new PropertyValueFactory<>("idc"));
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        
        modePayColumn.setCellValueFactory(new PropertyValueFactory<>("mode_pay"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        ObservableList<Commande> commandeList = FXCollections.observableArrayList();
        commandeList.addAll(commandeCRUD.afficherCommandes());
        commandeTableView.setItems(commandeList);

        // Set event handlers for buttons
        
    }

   



    private void clearInputFields() {
        idcField.clear();
        montantField.clear();
        
        modePayField.clear();
        dateField.clear();
    }
    private void showErrorAlert(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Date Format Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void EffacerCommande(ActionEvent event) {
        openEffacerCommandeWindow();
    }
    private void openEffacerCommandeWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EffacerCommandeWindow.fxml"));
            Parent root = loader.load();

            // Get the controller of the EffacerCommandeWindow
            EffacerCommandeWindowController controller = loader.getController();
            // Pass data to the EffacerCommandeWindow
            

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Effacer Commande Window");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
