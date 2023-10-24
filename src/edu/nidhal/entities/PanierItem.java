/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.entities;

/**
 *
 * @author nidha
 */
public class PanierItem {


    private int idp;        // Product ID
    private float montant;  // Total amount for this item
    private int quantite;   // Quantity of this item
    private String element; // Item description or name

    public PanierItem( float montant, int quantite, String element) {
       
        this.montant = montant;
        this.quantite = quantite;
        this.element = element;
    }

    public PanierItem(int idp, float montant, int quantite, String element) {
       this.idp = idp;
    this.montant = montant;
    this.quantite = quantite;
    this.element = element; //To change body of generated methods, choose Tools | Templates.
    }

    public PanierItem(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters and setters for the attributes
    public int getIdp() {
        return idp;
    }

    //public void setIdp(int idp) {
        //this.idp = idp;
    //}

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "PanierItem{" +
                "idp=" + idp +
                ", montant=" + montant +
                ", quantite=" + quantite +
                ", element='" + element + '\'' +
                '}';
    }
}

