package fr.galettedebroons.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Prevision {

	private int id;
	private int quantite;
	private Date date;
	private Profil prevision_profil;
	private Produit prevision_produit;
	
	public Prevision(){}
	
	public Prevision(int quantite, Profil prevision_profil, Produit prevision_produit, Date date){
		this.quantite = quantite;
		this.prevision_profil = prevision_profil;
		this.prevision_produit = prevision_produit;
		this.date = date;
	}
	
	public Prevision(int quantite){
		this.quantite = quantite;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	@ManyToOne
	public Profil getPrevision_profil() {
		return prevision_profil;
	}
	
	public void setPrevision_profil(Profil prevision_profil) {
		this.prevision_profil = prevision_profil;
	}
	
	@ManyToOne
	public Produit getPrevision_produit() {
		return prevision_produit;
	}
	
	public void setPrevision_produit(Produit prevision_produit) {
		this.prevision_produit = prevision_produit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
