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
	private int qte_eff_vendue;
	
	public Livraison(){}
	
	public Livraison(String bon_livraison, List<Produit> code_produit, Date date_livraison, int qte_livraison, int qte_eff) {
		super();
		this.bon_livraison = bon_livraison;
		this.livraison_produit = code_produit;
		this.date_livraison = date_livraison;
		this.qte_livraison = qte_livraison;
		this.qte_eff_vendue = qte_eff;
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
	
	

	@ManyToOne
	public Profil getLivraison_profil() {
		return livraison_profil;
	}
	
	public void setLivraison_profil(Profil profil) {
		this.livraison_profil = profil;
	}

    /**
     * @return the qte_eff_vendue
     */
    public int getQte_eff_vendue() {
        return qte_eff_vendue;
    }

    /**
     * @param qte_eff_vendue the qte_eff_vendue to set
     */
    public void setQte_eff_vendue(int qte_eff_vendue) {
        this.qte_eff_vendue = qte_eff_vendue;
    }
}
