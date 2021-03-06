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
	private boolean editable;
	
	public MargeLivraison(){}
	
	public MargeLivraison(double taux_reprise) {
		this.taux_reprise = taux_reprise;
	}
	
	public MargeLivraison(double taux_reprise, Profil marge_profil, Produit marge_produit, boolean edition) {
		this.taux_reprise = taux_reprise;
		this.marge_profil = marge_profil;
		this.marge_produit = marge_produit;
		this.editable = edition;
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

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
