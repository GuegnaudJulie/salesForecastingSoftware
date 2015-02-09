package fr.galettedebroons.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ProfilClient {
	
	private int id;
	private String code_client;
	private String code_gamme;
	private String livraison;
	
		
	public ProfilClient(String code_client, String code_gamme, String livraison) {
		this.code_client = code_client;
		this.code_gamme = code_gamme;
		this.livraison = livraison;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCode_client() {
		return code_client;
	}
	public void setCode_client(String code_client) {
		this.code_client = code_client;
	}
	public String getCode_gamme() {
		return code_gamme;
	}
	public void setCode_gamme(String code_gamme) {
		this.code_gamme = code_gamme;
	}
	public String getLivraison() {
		return livraison;
	}
	public void setLivraison(String livraison) {
		this.livraison = livraison;
	}
	
	

}
