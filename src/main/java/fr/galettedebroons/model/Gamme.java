package fr.galettedebroons.model;

import javax.persistence.Id;

public class Gamme {
	
	@Id
	private String code_gamme;
	private int duree_conservation;
	
	
	public Gamme(String code_gamme, int duree_conservation) {
		super();
		this.code_gamme = code_gamme;
		this.duree_conservation = duree_conservation;
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
	
	
	

}
