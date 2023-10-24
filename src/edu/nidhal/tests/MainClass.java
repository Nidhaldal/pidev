/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.tests;

import edu.nidhal.entities.Commande;
import edu.nidhal.services.CommandeCRUD;
import edu.nidhal.tools.MyConnection;
import edu.nidhal.entities.Panier;
import edu.nidhal.services.PanierCRUD;
/**
 *
 * @author nidha
 */
public class MainClass {
    public static void main(String[] args){
       MyConnection mc= new MyConnection(); 
       CommandeCRUD ccd = new CommandeCRUD();
       //ccd.ajouterCommande();
       PanierCRUD pcd = new PanierCRUD();
       //pcd.ajouterPanier();
         //System.out.println(pcd.afficherPanier());
         //System.out.println(ccd.afficherCommandes());
           //ccd.EffacerCommande(2, "en cours", "en ligne");
         //pcd.EffacerPanier(1, "pull", 10, 12);
         //ccd.ModifierCommande(3, "terminé", "en ligne");
          //pcd.ModifierPanier(2, "kabout ", 2, 190);
    }
    
    
}
