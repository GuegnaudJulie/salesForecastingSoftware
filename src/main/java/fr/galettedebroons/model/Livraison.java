package fr.galettedebroons.model;

import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Livraison {
	
	@Id
	private String bon_livraison;
	private Client code_client;
	private Produit code_produit;
	private Date date_livraison;
	private int qte_livraison;
	private int qte_reprise;
	
	
	public Livraison(String bon_livraison, Client code_client,
			Produit code_produit, Date date_livraison, int qte_livraison,
			int qte_reprise) {
		super();
		this.bon_livraison = bon_livraison;
		this.code_client = code_client;
		this.code_produit = code_produit;
		this.date_livraison = date_livraison;
		this.qte_livraison = qte_livraison;
		this.qte_reprise = qte_reprise;
	}
	
	public String getBon_livraison() {
		return bon_livraison;
	}
	
	public void setBon_livraison(String bon_livraison) {
		this.bon_livraison = bon_livraison;
	}
	
	@ManyToOne
	public Client getCode_client() {
		return code_client;
	}
	
	public void setCode_client(Client code_client) {
		this.code_client = code_client;
	}
	
	@ManyToMany
	public Produit getCode_produit() {
		return code_produit;
	}
	
	public void setCode_produit(Produit code_produit) {
		this.code_produit = code_produit;
	}
	
	public Date getDate_livraison() {
		return date_livraison;
	}
	
	public void setDate_livraison(Date date_livraison) {
		this.date_livraison = date_livraison;
	}
	public int getQte_livraison() {
		return qte_livraison;
	}
	
	public void setQte_livraison(int qte_livraison) {
		this.qte_livraison = qte_livraison;
	}
	
	public int getQte_reprise() {
		return qte_reprise;
	}
	
	public void setQte_reprise(int qte_reprise) {
		this.qte_reprise = qte_reprise;
	}

	
	
}
