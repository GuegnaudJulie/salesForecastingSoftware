package fr.galettedebroons.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Produit {
	
	@Id
	private String code_produit;
	private String nom_produit;
	private String presentation_produit;
	private Gamme code_gamme;
	private int qte_lot;
	private String nature_produit;
	private ArrayList<Livraison> livraison;
	
	public Produit(String code_produit, String nom_produit,
			String presentation_produit, Gamme code_gamme, int qte_lot) {
		super();
		this.code_produit = code_produit;
		this.nom_produit = nom_produit;
		this.presentation_produit = presentation_produit;
		this.code_gamme = code_gamme;
		this.qte_lot = qte_lot;
	}
	
	public String getCode_produit() {
		return code_produit;
	}
	public void setCode_produit(String code_produit) {
		this.code_produit = code_produit;
	}
	public String getNom_produit() {
		return nom_produit;
	}
	public void setNom_produit(String nom_produit) {
		this.nom_produit = nom_produit;
	}
	public String getPresentation_produit() {
		return presentation_produit;
	}
	public void setPresentation_produit(String presentation_produit) {
		this.presentation_produit = presentation_produit;
	}
	
	@ManyToOne
	public Gamme getCode_gamme() {
		return code_gamme;
	}
	public void setCode_gamme(Gamme code_gamme) {
		this.code_gamme = code_gamme;
	}
	public int getQte_lot() {
		return qte_lot;
	}
	public void setQte_lot(int qte_lot) {
		this.qte_lot = qte_lot;
	}
	public String getNatureProduit() {
		return nature_produit;
	}
	public void setNatureProduit(String nature_produit) {
		this.nature_produit = nature_produit;
	}
	
	@ManyToMany
	public ArrayList<Livraison> getLivraison() {
		return livraison;
	}
	public void setLivraison(ArrayList<Livraison> livraison) {
		this.livraison = livraison;
	}
}
