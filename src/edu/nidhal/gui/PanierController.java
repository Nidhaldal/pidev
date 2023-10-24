/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.gui;

import edu.nidhal.entities.Panier;
import edu.nidhal.services.PanierCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.omg.CORBA.SystemException;

/**
 * FXML Controller class
 *
 * @author nidha
 */
public class PanierController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfelement;
    @FXML
    private TextField tfquantite;
    @FXML
    private TextField tfmontant;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnafficher;
    @FXML
    private Button btnCommande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    

    
    
    @FXML
private void ajouterPanier(ActionEvent event) {
    String idpText = tfid.getText();
    String element = tfelement.getText();
    String quantiteText = tfquantite.getText();
    String montantText = tfmontant.getText();

    
    if (idpText.isEmpty() || element.isEmpty() || quantiteText.isEmpty() || montantText.isEmpty()) {
        afficherErreur("Tous les champs doivent être remplis.");
        return;
    }

    try {
        int idp = Integer.parseInt(idpText);
        int quantite = Integer.parseInt(quantiteText);
        float montant = Float.parseFloat(montantText);

        if (idp < 0 || quantite < 0 || montant < 0) {
            afficherErreur("Les valeurs doivent être positives.");
            return;
        }

        Panier p = new Panier(idp, element, quantite, montant);
        
        
        PanierCRUD pc = new PanierCRUD();
        //pc.ajouterPanier(p);

       
        tfid.clear();
        tfelement.clear();
        tfquantite.clear();
        tfmontant.clear();
    } catch (NumberFormatException e) {
        afficherErreur("Veuillez saisir des valeurs numériques valides.");
    }
}

private void afficherErreur(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void afficherPanier(ActionEvent event) {
        
    PanierCRUD pc = new PanierCRUD();
    List<Panier> panierList = pc.afficherPanier(); 

    StringBuilder panierContent = new StringBuilder();
    for (Panier p : panierList) {
        panierContent.append("ID: ").append(p.getIdp()).append("\n");
        panierContent.append("Élément: ").append(p.getElement()).append("\n");
        panierContent.append("Quantité: ").append(p.getQuantite()).append("\n");
        panierContent.append("Montant: ").append(p.getMontant()).append("\n");
        panierContent.append("\n");
    }

    // Afficher le contenu du panier dans une boîte de dialogue
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Contenu du Panier");
    alert.setHeaderText("Contenu actuel du panier :");
    alert.setContentText(panierContent.toString());
    alert.showAndWait();
}

    @FXML
    private void allerVersCommande(ActionEvent event) {
        

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeWindow.fxml"));
            Parent commandeRoot = loader.load();
            Stage stage = (Stage) btnCommande.getScene().getWindow();
            stage.setScene(new Scene(commandeRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    }
    
    
