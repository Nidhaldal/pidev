/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.services;

import edu.nidhal.entities.Commande;
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
public class CommandeCRUD {
    
    public void ajouterCommande(){
        try {
            String requete ="INSERT INTO Commande(etat,mode_pay)"
                    +"VALUES('en cours','en ligne')";
            
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Commande ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajouterCommande2(Commande c){
      
        
    }
    public List<Commande> afficherCommandes(){
        List<Commande> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM Commande ";
            Statement st = new MyConnection().getCnx().createStatement();
           ResultSet rs= st.executeQuery(requete3);
            while(rs.next()){
                Commande c = new Commande();
                c.setIdc(rs.getInt(1));
                c.setEtat(rs.getString("etat"));
                c.setMode_pay(rs.getString("mode_pay"));
                myList.add(c);
                
            }
        } catch (SQLException ex) {
            System.err.println("ex.getMessage()");
        }
        return myList;
    
    
    }
    
    
    public void EffacerCommande(int idc, String etat, String mode_pay) {
    try {
        // Define the SQL DELETE query
        String deleteQuery = "DELETE FROM Commande WHERE idc = ? AND etat = ? AND mode_pay = ?";
        
        // Create a PreparedStatement with the delete query
        PreparedStatement preparedStatement = new MyConnection().getCnx().prepareStatement(deleteQuery);
        preparedStatement.setInt(1, idc);
        preparedStatement.setString(2, etat);
        preparedStatement.setString(3, mode_pay);

        // Execute the DELETE query
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if any rows were deleted
        if (rowsAffected > 0) {
            System.out.println("Commande effacée avec succès.");
        } else {
            System.out.println("Commande inexistante.");
        }

        // Close the PreparedStatement
        preparedStatement.close();
    } catch (SQLException ex) {
        System.err.println("Error: " + ex.getMessage());
    }
}
    
    
    
    public void ModifierCommande(int idc, String newEtat, String newMode_pay) {
    try {
        // Define the SQL UPDATE query
        String updateQuery = "UPDATE Commande SET etat = ?, mode_pay = ? WHERE idc = ?";
        
        // Create a PreparedStatement with the update query
        PreparedStatement preparedStatement = new MyConnection().getCnx().prepareStatement(updateQuery);
        preparedStatement.setString(1, newEtat);
        preparedStatement.setString(2, newMode_pay);
        preparedStatement.setInt(3, idc);

        // Execute the UPDATE query
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if any rows were updated
        if (rowsAffected > 0) {
            System.out.println("Commande modifiée avec succès.");
        } else {
            System.out.println("Commande inexistante.");
        }

        // Close the PreparedStatement
        preparedStatement.close();
    } catch (SQLException ex) {
        System.err.println("Error: " + ex.getMessage());
    }
}
    
    
    
    
    
    
    }
    

