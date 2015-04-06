package fr.galettedebroons.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class QuantiteReelle {

	private int id;
	private int quantite;
	private Date date;
	private Profil reel_profil;
	private Produit reel_produit;
	private boolean rupture_stock;
	
	public QuantiteReelle(){}
	
	public QuantiteReelle(int quantite, Profil reel_profil, Produit reel_produit, Date date, boolean rupture){
		this.quantite = quantite;
		this.reel_profil = reel_profil;
		this.reel_produit = reel_produit;
		this.date = date;
		this.rupture_stock = rupture;
	}
	
	public QuantiteReelle(int quantite){
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
	public Profil getReel_profil() {
		return reel_profil;
	}
	
	public void setReel_profil(Profil reel_profil) {
		this.reel_profil = reel_profil;
	}
	
	@ManyToOne
	public Produit getReel_produit() {
		return reel_produit;
	}
	
	public void setReel_produit(Produit reel_produit) {
		this.reel_produit = reel_produit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isRupture_stock() {
		return rupture_stock;
	}

	public void setRupture_stock(boolean rupture_stock) {
		this.rupture_stock = rupture_stock;
	}
}
