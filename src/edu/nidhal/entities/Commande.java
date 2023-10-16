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
public class Commande {
    
    private int idc;
    private String etat;
    private String mode_pay; 
   public Commande(){
   
   } 
    
    public Commande(int idc, String etat, String mode_pay) {
        this.idc = idc;
        this.etat = etat;
        this.mode_pay = mode_pay;
    }

    public Commande(String etat, String mode_pay) {
        this.etat = etat;
        this.mode_pay = mode_pay;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMode_pay() {
        return mode_pay;
    }

    public void setMode_pay(String mode_pay) {
        this.mode_pay = mode_pay;
    }

    @Override
    public String toString() {
        return "Commande{" + "idc=" + idc + ", etat=" + etat + ", mode_pay=" + mode_pay + '}';
    }

    public String getNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
