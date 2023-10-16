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
public class Panier {
    private int idp;
    private String element;
    private int quantite;
    private float montant;

     public Panier(){
   
   } 
    
    public Panier(int idp, String element, int quantite, float montant) {
        this.idp = idp;
        this.element = element;
        this.quantite = quantite;
        this.montant = montant;
    }

    public Panier(String element, int quantite, float montant) {
        this.element = element;
        this.quantite = quantite;
        this.montant = montant;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Panier{" + "idp=" + idp + ", element=" + element + ", quantite=" + quantite + ", montant=" + montant + '}';
    }
    
    
    
    
}
