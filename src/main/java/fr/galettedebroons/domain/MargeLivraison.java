package fr.galettedebroons.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MargeLivraison {
	
	private int id;
	private double taux_reprise;
	private Profil marge_profil;
	private Produit marge_produit;
	
	public MargeLivraison(){}
	
	public MargeLivraison(double taux_reprise) {
		this.taux_reprise = taux_reprise;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getTaux_reprise() {
		return taux_reprise;
	}

	public void setTaux_reprise(double taux_reprise) {
		this.taux_reprise = taux_reprise;
	}

	@ManyToOne
	public Profil getMarge_profil() {
		return marge_profil;
	}

	public void setMarge_profil(Profil marge_profil) {
		this.marge_profil = marge_profil;
	}

	@ManyToOne
	public Produit getMarge_produit() {
		return marge_produit;
	}

	public void setMarge_produit(Produit marge_produit) {
		this.marge_produit = marge_produit;
	}
}
