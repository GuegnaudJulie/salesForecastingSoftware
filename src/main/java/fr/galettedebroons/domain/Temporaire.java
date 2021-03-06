package fr.galettedebroons.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Temporaire {

	private int id;
	private String bon_livraison;
	private Date date;
	private String code_profil;
	private String nom_client;
	private String code_produit;
	private int quantite_livree;
    private int quantite_reprise;
	private String code_erreur;
	
	public Temporaire (){}
	
	public Temporaire(String bon_livraison, Date date, String code_profil, String nom_client, String code_produit, int quantite_l, int quantite_r,String code_erreur){
		this.bon_livraison = bon_livraison;
		this.date = date;
		this.code_profil = code_profil;
		this.nom_client = nom_client;
		this.code_produit = code_produit;
		this.quantite_livree = quantite_l;
		this.quantite_reprise = quantite_r;
		this.code_erreur = code_erreur;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBon_livraison() {
		return bon_livraison;
	}
	
	public void setBon_livraison(String bon_livraison) {
		this.bon_livraison = bon_livraison;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getCode_profil() {
		return code_profil;
	}
	
	public void setCode_profil(String code_profil) {
		this.code_profil = code_profil;
	}
	
	public String getNom_client() {
		return nom_client;
	}
	
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
	
	public String getCode_produit() {
		return code_produit;
	}
	
	public void setCode_produit(String code_produit) {
		this.code_produit = code_produit;
	}
	
	public String getCode_erreur() {
		return code_erreur;
	}
	
	public void setCode_erreur(String code_erreur) {
		this.code_erreur = code_erreur;
	}
	
    public int getQuantite_livree() {
        return quantite_livree;
    }
    
    public void setQuantite_livree(int quantite_livree) {
        this.quantite_livree = quantite_livree;
    }
    
    public int getQuantite_reprise() {
        return quantite_reprise;
    }
    
    public void setQuantite_reprise(int quantite_reprise) {
        this.quantite_reprise = quantite_reprise;
    }
    
}
