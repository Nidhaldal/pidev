/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.gui;

import edu.nidhal.entities.PanierItem;
import edu.nidhal.services.CommandeCRUD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class EffacerCommandeWindowController implements Initializable {

    @FXML
    private TextField tfidc;
    @FXML
    private Button ECbutton;
    private double totalAmount;
    private ObservableList<PanierItem> selectedItems;

    @FXML
    private void EffacerCommande(ActionEvent event) {
        try {
            // Parse the ID as an integer
            int idc = Integer.parseInt(tfidc.getText());

            CommandeCRUD cc = new CommandeCRUD();
            // Call the EffacerCommande method from CommandeCRUD
            cc.EffacerCommande(idc);

            tfidc.clear();
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Commande effacée avec succès.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Veuillez saisir un ID de commande valide.");
        }
    }

    // Display a JavaFX Alert
    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: You can add initialization code here, if needed.
    }

    public void initData(double totalAmount, ObservableList<PanierItem> selectedItems) {
    // Initialize your controller with the provided data
    this.totalAmount = totalAmount;
    this.selectedItems = selectedItems;
    
    // Add any additional initialization code if needed
}

}
