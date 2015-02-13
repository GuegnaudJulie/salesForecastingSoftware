package fr.galettedebroons.domain;

import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Gamme {
	
	@Id
	private String code_gamme;
	private int duree_conservation;
	private Produit code_prod;
	private ProfilClient pc;
	
	
	public Gamme(String code_gamme, int duree_conservation, Produit code_p, ProfilClient pcl) {
		this.code_gamme = code_gamme;
		this.duree_conservation = duree_conservation;
		this.code_prod = code_p;
		this.pc = pcl;
	}
	
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

	@OneToMany
	public Produit getCode_prod() {
		return code_prod;
	}

	public void setCode_prod(Produit code_prod) {
		this.code_prod = code_prod;
	}

	@OneToMany
	public ProfilClient getPc() {
		return pc;
	}

	public void setPc(ProfilClient pc) {
		this.pc = pc;
	}
	
	
}
