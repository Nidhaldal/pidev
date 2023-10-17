/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.gui;

import edu.nidhal.entities.Commande;
import edu.nidhal.services.CommandeCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nidha
 */
public class CommandeWindowController implements Initializable {

    @FXML
    private TextField tfidc;
    @FXML
    private Button btnajouter;
    private TextField tfmp;
    private TextField tfec;
    @FXML
    private TextField tfmod;
    @FXML
    private ComboBox<String> cbec;
    @FXML
    private ComboBox<String> cbmp;
    @FXML
    private Button btnac;

    /**
     * Initializes the controller private ComboBox<String> cbmp;
 class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
ObservableList<String> items = FXCollections.observableArrayList("en ligne", "cash"); 
        cbmp.getItems().addAll(items);
        cbec.getItems().addAll("en cours", "terminé");
       
        
    }    

   @FXML
private void ajouterCommande(ActionEvent event) {
    try {
        int idc = Integer.parseInt(tfidc.getText());
        String mode_pay = cbmp.getValue();
        String etat = cbec.getValue(); 
        float montant = Float.parseFloat(tfmod.getText());

        
        if (idc < 0 || mode_pay == null || etat == null || montant < 0) {
            afficherErreur("Veuillez remplir tous les champs correctement.");
            return;
        }

        Commande c = new Commande(idc, etat, mode_pay, montant);

        
        CommandeCRUD cc = new CommandeCRUD();
        cc.ajouterCommande(c);

        
        tfidc.clear();
        cbmp.setValue(null);
        cbec.setValue(null);
        tfmod.clear();
    } catch (NumberFormatException e) {
        afficherErreur("Veuillez saisir des valeurs numériques valides.");
    }
}

@FXML
private void EffacerCommande(ActionEvent event) {
   
    int idc = Integer.parseInt(tfidc.getText());
    
    CommandeCRUD cc = new CommandeCRUD();
    cc.EffacerCommande(idc);

    tfidc.clear();
    cbmp.setValue(null);
    cbec.setValue(null);
    tfmod.clear();
}

    private void afficherErreur(String veuillez_remplir_tous_les_champs_correcte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
