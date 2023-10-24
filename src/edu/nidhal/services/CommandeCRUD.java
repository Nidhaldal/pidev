/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.services;

import edu.nidhal.entities.Commande;
import edu.nidhal.tools.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommandeCRUD {

    public void ajouterCommande(Commande c , float totalAmount) {
        try {
            String query = "INSERT INTO Commande ( mode_pay, montant, date) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = new MyConnection().getCnx().prepareStatement(query);
            
            preparedStatement.setString(1, c.getMode_pay());
            preparedStatement.setFloat(2, totalAmount);
            
            Date sqlDate = Date.valueOf(c.getDate());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.executeUpdate();
            System.out.println("Commande ajoutée avec succès.");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'ajout de la commande : " + ex.getMessage());
        }
    }

    public List<Commande> afficherCommandes() {
        List<Commande> myList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Commande";
            Statement st = new MyConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Commande c = new Commande();
                c.setIdc(rs.getInt(1));
                
                c.setMode_pay(rs.getString("mode_pay"));
                c.setMontant(rs.getFloat("montant"));
                c.setDate(rs.getDate("date").toLocalDate());
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

   public void EffacerCommande(int idc) {
        try {
            String deleteQuery = "DELETE FROM Commande WHERE idc = ?";
            PreparedStatement preparedStatement = new MyConnection().getCnx().prepareStatement(deleteQuery);
            preparedStatement.setInt(1, idc);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Commande effacée avec succès.");
            } else {
                System.out.println("Commande inexistante.");
            }

            preparedStatement.close();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

   public void ModifierCommande(Commande selectedCommande) {
    try {
        String updateQuery = "UPDATE Commande SET  mode_pay = ?, montant = ?, date = ? WHERE idc = ?";
        PreparedStatement preparedStatement = new MyConnection().getCnx().prepareStatement(updateQuery);
        
        preparedStatement.setString(1, selectedCommande.getMode_pay());
        preparedStatement.setFloat(2, selectedCommande.getMontant());

        // Handle the date
        Date sqlDate;
        LocalDate localDate = selectedCommande.getDate();
        if (localDate != null) {
            sqlDate = Date.valueOf(localDate);
        } else {
            // Provide a default date if needed
            sqlDate = Date.valueOf(LocalDate.now());
        }
        preparedStatement.setDate(3, sqlDate);

        preparedStatement.setInt(5, selectedCommande.getIdc());
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Commande modifiée avec succès.");
        } else {
            System.out.println("Commande inexistante.");
        }
        preparedStatement.close();
    } catch (SQLException ex) {
        System.err.println("Error: " + ex.getMessage());
    }
}



    public void EffacerCommande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
