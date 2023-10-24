/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.entities;

import java.time.LocalDate;

public class Commande {
    private int idc;
   
    private String mode_pay;
    private float montant;
    private LocalDate date;
    private String nom;  // Add the "Nom" attribute
    private String details;  // Add the "Details" attribute

    public Commande() {
        this.date = LocalDate.now();
    }

    

    public Commande( String mode_pay, float montant) {
        
        this.mode_pay = mode_pay;
        this.montant = montant;
        this.date = LocalDate.now();
    }

    public Commande(String root_Commande) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Commande(int idc, float montant, String etat, String modePay, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Commande(int idc, float montant,  String modePay, LocalDate parsedDate) {
        this.idc = idc;
    this.montant = montant;
    
    this.mode_pay = modePay;; //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    

    public String getMode_pay() {
        return mode_pay;
    }

    public void setMode_pay(String mode_pay) {
        this.mode_pay = mode_pay;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Commande{" +
                
                ", mode_pay='" + mode_pay + '\'' +
                ", montant=" + montant +
                ", date=" + date +
                ", nom='" + nom + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}

