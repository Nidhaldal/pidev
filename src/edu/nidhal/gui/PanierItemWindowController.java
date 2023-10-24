/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.gui;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import edu.nidhal.entities.Panier;
import edu.nidhal.entities.PanierItem;
import edu.nidhal.services.PanierCRUD;
import edu.nidhal.gui.CommandeWindowController;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import edu.nidhal.tools.CurrencyConverter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PanierItemWindowController {
    private DoubleProperty totalMontantProperty = new SimpleDoubleProperty(0.0);
    private final String targetCurrencyCode = "USD";
    private CurrencyConverter currencyConverter;
    private static final String ACCOUNT_SID = "AC2951637ede1a7f3817bc5b27f32cf53f";
    private static final String AUTH_TOKEN = "91730c58af72c4a6d8c954db16da05ff";
    
    
    @FXML
    private TableView<PanierItem> panierTableView;
    @FXML
    private TableColumn<PanierItem, Integer> idpColumn;
    @FXML
    private TableColumn<PanierItem, Float> montantColumn;
    @FXML
    private TableColumn<PanierItem, Integer> quantiteColumn;
    @FXML
    private TableColumn<PanierItem, String> elementColumn;
    @FXML
    private Button addItemButton;
    @FXML
    private Button removeItemButton;
    @FXML
    private Button updateItemButton;
    @FXML
    private Button clearCartButton;
    private TextField idpField;
    @FXML
    private TextField montantField;
    @FXML
    private TextField quantiteField;
    @FXML
    private TextField elementField;
    @FXML
    private Button exportCSVButton;
    @FXML
    private Label totalMontantLabel;
     @FXML
    private Button passerCommandeButton;
     @FXML
    private Button convertCurrencyButton;
     @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;



    private PanierCRUD panierCRUD;
    private CurrencyConverter CurrencyConverter;
    @FXML
    private TextField convertedAmountTextField;
    

    public PanierItemWindowController() {
        panierCRUD = new PanierCRUD();
        CurrencyConverter = new CurrencyConverter();
    }

    public void initialize() {
        // Initialize the TableView columns
        idpColumn.setCellValueFactory(new PropertyValueFactory<>("idp"));
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        elementColumn.setCellValueFactory(new PropertyValueFactory<>("element"));

        ObservableList<PanierItem> panierItemList = FXCollections.observableArrayList();
        List<Panier> panierList = panierCRUD.afficherPanier();
        for (Panier panier : panierList) {
            PanierItem panierItem = new PanierItem(panier.getIdp(), panier.getMontant(), panier.getQuantite(), panier.getElement());
            panierItemList.add(panierItem);
        }
        panierTableView.setItems(panierItemList);

        // Set event handlers for buttons (Add Item, Remove Item, Update Item, Clear Cart)
        addItemButton.setOnAction(event -> handleAddItem());
        removeItemButton.setOnAction(event -> handleRemoveItem());
        updateItemButton.setOnAction(event -> handleUpdateItem());
        clearCartButton.setOnAction(event -> handleClearCart());

        // Handle cell editing for montant and quantite columns
        montantColumn.setOnEditCommit(new EventHandler<CellEditEvent<PanierItem, Float>>() {
            @Override
            public void handle(CellEditEvent<PanierItem, Float> event) {
                PanierItem item = event.getRowValue();
                item.setMontant(event.getNewValue());
                handleUpdateItem();
            }
        });

        quantiteColumn.setOnEditCommit(new EventHandler<CellEditEvent<PanierItem, Integer>>() {
            @Override
            public void handle(CellEditEvent<PanierItem, Integer> event) {
                PanierItem item = event.getRowValue();
                item.setQuantite(event.getNewValue());
                handleUpdateItem();
            }
        });

        // Allow editing for montant and quantite columns
        montantColumn.setCellFactory(tc -> new EditingCell<>(new FloatStringConverter()));
        quantiteColumn.setCellFactory(tc -> new EditingCell<>(new IntegerStringConverter()));

        // Bind the total montant calculation to the label
        totalMontantProperty.bind(Bindings.createDoubleBinding(() -> {
            double total = 0.0;
            for (PanierItem item : panierTableView.getItems()) {
                total += item.getMontant() * item.getQuantite();
            }
            return total;
        }, panierTableView.getItems()));

        // Display the total montant in the label
        totalMontantLabel.textProperty().bind(Bindings.format("Total Montant: %.2f", totalMontantProperty));
    }

    @FXML
    private void handleAddItem() {
        // Implement logic for adding an item to the "panier" database
        try {
           
            float montant = Float.parseFloat(montantField.getText());
            int quantite = Integer.parseInt(quantiteField.getText());
            String element = elementField.getText();

            PanierItem item = new PanierItem( montant, quantite, element);
            panierCRUD.ajouterPanier(item);

            panierTableView.getItems().add(item); // Add item to the TableView
            clearInputFields();
        } catch (NumberFormatException e) {
            showErrorAlert("Invalide . svp entrez des valeurs valides.");
        }
    }

    @FXML
    private void handleRemoveItem() {
        // Implement logic for removing an item from the "panier" database
        PanierItem selectedItem = panierTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            panierCRUD.Effacerpanier(selectedItem.getIdp());
            panierTableView.getItems().remove(selectedItem); // Remove item from the TableView
        } else {
            showErrorAlert("svp choisissez une panier à effacer .");
        }
    }

    @FXML
    private void handleUpdateItem() {
        // Implement logic for updating an item in the "panier" database
        PanierItem selectedItem = panierTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String montantText = montantField.getText();
            String quantiteText = quantiteField.getText();
            String elementText = elementField.getText();

            // Check if any of the fields are empty
            if (montantText.isEmpty() || quantiteText.isEmpty() || elementText.isEmpty()) {
                showErrorAlert("svp remplissez tous les champs.");
            } else {
                try {
                    // Parse the values if the fields are not empty
                    float montant = Float.parseFloat(montantText);
                    int quantite = Integer.parseInt(quantiteText);
                    // Create an updated item
                     selectedItem.setMontant(montant);
                selectedItem.setQuantite(quantite);
                selectedItem.setElement(elementText);
                    // Call the ModifierPanier method with the updatedItem
                    panierCRUD.ModifierPanier(selectedItem);
                    // Refresh the TableView
                    panierTableView.refresh();
                } catch (NumberFormatException e) {
                    showErrorAlert("Invalide .svp entrez une valeur numérique valide.");
                }
            }
        } else {
            showErrorAlert("svp choisissez une panier à modifier.");
        }
    }

    @FXML
    private void handleClearCart() {
        // Implement logic to clear the entire "panier" database
        panierCRUD.EffacerPanier();
        panierTableView.getItems().clear(); // Clear all items from the TableView
    }

    @FXML
    private void handleExportToCSV() {
        exportToCSV();
    }
    @FXML
    private void passerCommande() {
        try {
            // Load the CommandeWindow
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeWindow.fxml"));
            Parent root = loader.load();

            // Get the controller of the CommandeWindow
            CommandeWindowController commandeController = loader.getController();
             double totalMontant = totalMontantProperty.get(); // Get the total montant
             commandeController.setMontant(totalMontant);
            // Transfer the PanierItem data to the CommandeWindow
            //commandeController.transferData(panierTableView.getItems());

            // Show the CommandeWindow
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Passer Commande");
            stage.show();
        } catch (IOException e) {
            showErrorAlert("Error opening CommandeWindow.");
        }
    }
     @FXML
    
private void convertCurrencyButtonClicked(ActionEvent event) {
    double conversionRate = 0.30; // Conversion rate from TND to USD

    try {
        double amountInTND = totalMontantProperty.get(); // Get the amount in TND

        // Convert the amount to USD
        double convertedAmount = amountInTND * conversionRate;

        // Update the TextField with the converted amount
        convertedAmountTextField.setText(String.format("%.2f USD", convertedAmount));

        // Update the totalMontantProperty (if needed)
        totalMontantProperty.set(convertedAmount);
    } catch (NumberFormatException e) {
        showErrorAlert("Invalid input. Please enter a valid amount.");
    }
}

    @FXML
    private void handleSendButtonAction(ActionEvent event) {
        String toPhoneNumber = phoneNumberField.getText();
        String messageText = messageField.getText();

        if (toPhoneNumber.isEmpty() || messageText.isEmpty()) {
            showAlert("Please enter a phone number and a message.");
            return;
        }

        // Initialize Twilio
        Twilio.init("AC2951637ede1a7f3817bc5b27f32cf53f", "91730c58af72c4a6d8c954db16da05ff");

        // Send an SMS using Twilio
        Message message = Message.creator(
                new PhoneNumber(toPhoneNumber), // To phone number
                new PhoneNumber("+18475534468\n" +
""), // Your Twilio phone number
                messageText
        ).create();

        showAlert("SMS sent successfully. Message SID: " + message.getSid());
    }

    private void clearInputFields() {
        
        montantField.clear();
        quantiteField.clear();
        elementField.clear();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void exportToCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(panierTableView.getScene().getWindow());

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                // Write the CSV header
                writer.write("IDP,Montant,Quantité,Élément\n");

                // Get the data from the TableView and write it to the CSV file
                for (PanierItem item : panierTableView.getItems()) {
                    writer.write(
                        String.format("%d,%.2f,%d,%s\n", item.getIdp(), item.getMontant(), item.getQuantite(), item.getElement())
                    );
                }

                writer.close();
            } catch (IOException e) {
                showErrorAlert("Error exporting to CSV file.");
            }
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Twilio SMS");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}

