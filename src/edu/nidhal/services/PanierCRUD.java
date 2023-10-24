/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.nidhal.services;

import edu.nidhal.entities.Panier;
import edu.nidhal.entities.PanierItem;
import edu.nidhal.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PanierCRUD {

    public void ajouterPanier(PanierItem p) {
        try {
            Connection connection = MyConnection.getInstance().getCnx();
            String query = "INSERT INTO panier (element, quantite, montant) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, p.getElement());
            preparedStatement.setInt(2, p.getQuantite());
            preparedStatement.setFloat(3, p.getMontant());
            preparedStatement.executeUpdate();
            System.out.println("Panier ajouté avec succès.");
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'ajout du panier : " + ex.getMessage());
        }
    }
public void EffacerPanier() {
        try {
            Connection connection = MyConnection.getInstance().getCnx();
            String query = "DELETE FROM panier WHERE idp = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Panier supprimé avec succès.");
            } else {
                System.out.println("Panier introuvable.");
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression du panier : " + ex.getMessage());
        }
    }
    
     public void Effacerpanier(int idp) {
        try {
            String deleteQuery = "DELETE FROM panier WHERE idp = ?";
            PreparedStatement preparedStatement = new MyConnection().getCnx().prepareStatement(deleteQuery);
            preparedStatement.setInt(1, idp);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Panier effacée avec succès.");
            } else {
                System.out.println("Panier inexistante.");
            }

            preparedStatement.close();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
   

    public void ModifierPanier(PanierItem item) {
        int idp = item.getIdp();
if (idp <= 0) {
    System.err.println("Invalid idp value.");
    return;}
        try {
            Connection connection = MyConnection.getInstance().getCnx();
            String query = "UPDATE panier SET element = ?, quantite = ?, montant = ? WHERE idp = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getElement());
            preparedStatement.setInt(2, item.getQuantite());
            preparedStatement.setFloat(3, item.getMontant());
            preparedStatement.setInt(4, idp);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Panier modifié avec succès.");
            } else {
                System.out.println("Panier introuvable.");
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la modification du panier : " + ex.getMessage());
        }
    }

    public List<Panier> afficherPanier() {
        List<Panier> panierList = new ArrayList<>();
        try {
            Connection connection = MyConnection.getInstance().getCnx();
            String query = "SELECT * FROM panier";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Panier p = new Panier();
                p.setIdp(resultSet.getInt("idp"));
                p.setElement(resultSet.getString("element"));
                p.setQuantite(resultSet.getInt("quantite"));
                p.setMontant(resultSet.getFloat("montant"));
                panierList.add(p);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la récupération des paniers : " + ex.getMessage());
        }
        return panierList;
    }
}
