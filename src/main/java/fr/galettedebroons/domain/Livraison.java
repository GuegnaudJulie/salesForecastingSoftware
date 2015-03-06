package fr.galettedebroons.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Livraison {
	
	private String bon_livraison;
	private Profil livraison_profil;
	private List<Produit> livraison_produit;
	private Date date_livraison;
	private int qte_livraison;
	private int qte_reprise;
	
	public Livraison(){}
	
	public Livraison(String bon_livraison, List<Produit> code_produit, Date date_livraison, int qte_livraison, int qte_reprise) {
		super();
		this.bon_livraison = bon_livraison;
		this.livraison_produit = code_produit;
		this.date_livraison = date_livraison;
		this.qte_livraison = qte_livraison;
		this.qte_reprise = qte_reprise;
	}
	
	@Id
	public String getBon_livraison() {
		return bon_livraison;
	}
	
	public void setBon_livraison(String bon_livraison) {
		this.bon_livraison = bon_livraison;
	}
	
	@ManyToMany(mappedBy="livraison_produit")
	public List<Produit> getLivraison_produit() {
		return livraison_produit;
	}
	
	public void setLivraison_produit(List<Produit> livraison_produit) {
		this.livraison_produit = livraison_produit;
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

	@ManyToOne
	public Profil getLivraison_profil() {
		return livraison_profil;
	}
	
	public void setLivraison_profil(Profil profil) {
		this.livraison_profil = profil;
	}
}
