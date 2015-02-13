package fr.galettedebroons.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class ProfilClient {
	
	private int id;
	private String code_client;
	private Gamme code_gamme;
	private Livraison livraison;
	private Boolean actif;
	
		
	public ProfilClient(String code_client, Gamme code_gamme, Livraison livraison, Boolean actif) {
		this.code_client = code_client;
		this.code_gamme = code_gamme;
		this.livraison = livraison;
		this.actif = actif;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public String getCode_client() {
		return code_client;
	}
	public void setCode_client(String code_client) {
		this.code_client = code_client;
	}
	
	@OneToOne
	public Gamme getCode_gamme() {
		return code_gamme;
	}
	public void setCode_gamme(Gamme code_gamme) {
		this.code_gamme = code_gamme;
	}
	
	@OneToMany
	public Livraison getLivraison() {
		return livraison;
	}
	public void setLivraison(Livraison livraison) {
		this.livraison = livraison;
	}
	
	

}
