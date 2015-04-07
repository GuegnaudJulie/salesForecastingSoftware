package fr.galettedebroons.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Gamme {
	
	private String code_gamme;
	private int duree_conservation;
	private List<Produit> produit_gamme;
	
	public Gamme(){}
	
	public Gamme(String code_gamme) {
		this.code_gamme = code_gamme;
	}
	
	public Gamme(String code_gamme, int duree_conservation) {
		this.code_gamme = code_gamme;
		this.duree_conservation = duree_conservation;
	}
	
	public Gamme(String code_gamme, int duree_conservation, List<Produit> produit_gamme) {
		this.code_gamme = code_gamme;
		this.duree_conservation = duree_conservation;
		this.produit_gamme = produit_gamme;
	}
	
	@Id
	public String getCode_gamme() {
		return code_gamme;
	}
	
	public void setCode_gamme(String code_gamme) {
		this.code_gamme = code_gamme;
	}
	
	public int getDuree_conservation() {
		return duree_conservation;
	}
	
	public void setDuree_conservation(int duree_conservation) {
		this.duree_conservation = duree_conservation;
	}

	@OneToMany(mappedBy="produit_gamme", cascade=CascadeType.PERSIST)
	public List<Produit> getProduit_gamme() {
		return produit_gamme;
	}

	public void setProduit_gamme(List<Produit> code_prod) {
		this.produit_gamme = code_prod;
	}

	public void addProduit(Produit produit){
		produit_gamme.add(produit);
	}
}
