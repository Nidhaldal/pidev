/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.gui;

import edu.nidhal.entities.Commande;
import edu.nidhal.services.CommandeCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import edu.nidhal.entities.PanierItem;
import edu.nidhal.services.PanierCRUD;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CommandeWindowController implements Initializable {

    @FXML
    private TextField tfmod;
    
    @FXML
    private ComboBox<String> cbmp;
    @FXML
    private Button btnac;
    @FXML
    private Button btnajouter;
     private CommandeCRUD commandeCRUD;

    private double totalAmount; // Total amount from PanierItemWindow
    private ObservableList<PanierItem> selectedItems; // Selected items from PanierItemWindow

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> items = FXCollections.observableArrayList("en ligne", "cash");
        cbmp.getItems().addAll(items);
       
    }

    @FXML
    private void ajouterCommande(ActionEvent event) {
        try {
            String mode_pay = cbmp.getValue();
            

            if (mode_pay == null ) {
                afficherErreur("Veuillez remplir tous les champs correctement.");
                return;
            }

            // You can set the total amount from PanierItemWindow to the tfmod TextField
            String totalAmountText = tfmod.getText();
        float totalAmount = 0; // Initialize to a default value
        try {
            totalAmount = Float.parseFloat(totalAmountText);
        } catch (NumberFormatException e) {
            afficherErreur("Veuillez saisir un montant valide.");
            return;
        }
            
            Commande c = new Commande( mode_pay, (float) totalAmount);
      
            CommandeCRUD cc = new CommandeCRUD();
            cc.ajouterCommande(c, (float) totalAmount);

            cbmp.setValue(null);
            
         

        } catch (NumberFormatException e) {
            afficherErreur("Veuillez saisir des valeurs numériques valides.");
        }
        Properties properties = new Properties();
properties.put("mail.smtp.host", "smtp-mail.outlook.com"); //lahne el smtp relatif lil mail mte3ek
properties.put("mail.smtp.port", "587");
properties.put("mail.smtp.auth", "true");
properties.put("mail.smtp.starttls.enable", "true");
properties.put("mail.smtp.connectiontimeout", "5000"); // Augmentez le délai d'attente à 5 secondes


    Session session = Session.getInstance(properties, new Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("nidhalprojet@outlook.com", "Pidevjava");
    }
});

try {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress("Aziz.Aydi@esprit-tn.com"));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nidhal.dalhoumi@esprit.tn"));
    message.setSubject("Commande E-LBES");
    message.setText("Commande ajoutée  " );

    Transport.send(message);

    System.out.println("E-mail de Commande envoyé vers client !");
} catch (MessagingException e) {
    e.printStackTrace();
}
    }

    private void afficherErreur(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    // This method is used to pass data from PanierItemWindow
    public void initData(double totalAmount, ObservableList<PanierItem> selectedItems) {
        this.totalAmount = totalAmount;
        this.selectedItems = selectedItems;
    }

    @FXML
    private void EffacerCommande(ActionEvent event) {
        // Existing code for EffacerCommande

        // Add code to open the EffacerCommandeWindow
        openEffacerCommandeWindow();
    }

    // Add this method to open the EffacerCommandeWindow
    private void openEffacerCommandeWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EffacerCommandeWindow.fxml"));
            Parent root = loader.load();

            // Get the controller of the EffacerCommandeWindow
            EffacerCommandeWindowController controller = loader.getController();
            // Pass data to the EffacerCommandeWindow
            controller.initData(totalAmount, selectedItems);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Effacer Commande Window");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    public void setMontant(double montant) {
        // Mettez à jour le TextField avec le montant reçu
        tfmod.setText(String.valueOf(montant));
    }

    // You don't need this method as you are not using it
    // void transferData(ObservableList<PanierItem> items) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }

    //void transferData(ObservableList<PanierItem> items) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    @FXML
    private void consulterCommandes(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeItemWindow.fxml"));
        Parent root = loader.load();
        
        // Optionally, pass data to the CommandeItemWindowController if needed
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Consulter Commandes");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}

