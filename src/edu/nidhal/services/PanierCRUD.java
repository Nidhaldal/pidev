/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.services;

import edu.nidhal.entities.Panier;
import edu.nidhal.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
        
/**
 *
 * @author nidha
 */





/**
 *
 * @author nidha
 */
public class PanierCRUD {
    
    public void ajouterPanier(){
        try {
            String requete ="INSERT INTO Panier(element,quantite,montant)"
                    +"VALUES('pull','10','12')";
            
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Panier ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajouterPanier2(Panier p){
      
        
    }
    public List<Panier> afficherPanier(){
        List<Panier> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM Panier ";
            Statement st = new MyConnection().getCnx().createStatement();
           ResultSet rs= st.executeQuery(requete3);
            while(rs.next()){
                Panier p = new Panier();
                p.setIdp(rs.getInt(1));
                p.setElement(rs.getString("element"));
                p.setQuantite(rs.getInt("quantite"));
                p.setMontant(rs.getFloat("montant"));

                myList.add(p);
                
            }
        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return myList;
    }
    
     public void EffacerPanier(int idp, String element, int quantite , float montant) {
    try {
        String deleteQuery = "DELETE FROM Panier WHERE idp = ? AND element = ? AND quantite = ? AND montant = ?";
        
        PreparedStatement preparedStatement = new MyConnection().getCnx().prepareStatement(deleteQuery);
        preparedStatement.setInt(1, idp);
        preparedStatement.setString(2, element);
        preparedStatement.setInt(3, quantite);
        preparedStatement.setFloat(4, montant);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Panier effacé avec succès.");
        } else {
            System.out.println("Panier inexistante .");
        }

        preparedStatement.close();
    } catch (SQLException ex) {
        System.err.println("Error: " + ex.getMessage());
    }
}
     
     
     
     
      public void ModifierPanier(int idp, String newElement, int newQuantite ,float newMontant) {
    try {
        // Define the SQL UPDATE query
        String updateQuery = "UPDATE Panier SET element = ?, quantite = ? , montant=?  WHERE idp = ?";
        
        // Create a PreparedStatement with the update query
        PreparedStatement preparedStatement = new MyConnection().getCnx().prepareStatement(updateQuery);
        preparedStatement.setString(1, newElement);
        preparedStatement.setInt(2, newQuantite);
        preparedStatement.setFloat(3, newMontant);
        preparedStatement.setInt(4, idp);

        // Execute the UPDATE query
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if any rows were updated
        if (rowsAffected > 0) {
            System.out.println("Panier modifiée avec succès.");
        } else {
            System.out.println("Panier inexistante.");
        }

        // Close the PreparedStatement
        preparedStatement.close();
    } catch (SQLException ex) {
        System.err.println("Error: " + ex.getMessage());
    }
     
}
}