/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.galettedebroons.domain;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Oumoul
 */
@Entity
public class VueTemp {
    private int id;
    private String bon_livraison;
    private Date date;
   private String code_profil;
private String nom_client;
private String code_produit;
private int quantite_livree;
   private int quantite_reprise;

   public VueTemp(){}
   public VueTemp(String bon_livraison, Date date, String code_profil, String nom_client, String code_produit, int quantitel, int quantiter){
       this.bon_livraison = bon_livraison;
		this.date = date;
		this.code_profil = code_profil;
		this.nom_client = nom_client;
		this.code_produit = code_produit;
		this.quantite_livree = quantitel;
                this.quantite_reprise = quantiter;
		
   }
   
   
   
   
   /**
     * @return the id
     */
   @Id 
   @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the bon_livraison
     */
    public String getBon_livraison() {
        return bon_livraison;
    }

    /**
     * @param bon_livraison the bon_livraison to set
     */
    public void setBon_livraison(String bon_livraison) {
        this.bon_livraison = bon_livraison;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the code_profil
     */
    public String getCode_profil() {
        return code_profil;
    }

    /**
     * @param code_profil the code_profil to set
     */
    public void setCode_profil(String code_profil) {
        this.code_profil = code_profil;
    }

    /**
     * @return the nom_client
     */
    public String getNom_client() {
        return nom_client;
    }

    /**
     * @param nom_client the nom_client to set
     */
    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    /**
     * @return the code_produit
     */
    public String getCode_produit() {
        return code_produit;
    }

    /**
     * @param code_produit the code_produit to set
     */
    public void setCode_produit(String code_produit) {
        this.code_produit = code_produit;
    }

    /**
     * @return the quantite_livree
     */
    public int getQuantite_livree() {
        return quantite_livree;
    }

    /**
     * @param quantite_livree the quantite_livree to set
     */
    public void setQuantite_livree(int quantite_livree) {
        this.quantite_livree = quantite_livree;
    }

    /**
     * @return the quantite_reprise
     */
    public int getQuantite_reprise() {
        return quantite_reprise;
    }

    /**
     * @param quantite_reprise the quantite_reprise to set
     */
    public void setQuantite_reprise(int quantite_reprise) {
        this.quantite_reprise = quantite_reprise;
    }
    
}
