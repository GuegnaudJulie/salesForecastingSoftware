package fr.galettedebroons.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
	
	@Id
	private String code_client;
	private String enseigne_client;
	private String adresse_client;
		
	public Client(String code_client, String enseigne_client, String adresse_client) {
		this.code_client = code_client;
		this.enseigne_client = enseigne_client;
		this.adresse_client = adresse_client;
	}
	
	public String getCode_client() {
		return code_client;
	}
	public void setCode_client(String code_client) {
		this.code_client = code_client;
	}
	public String getEnseigne_client() {
		return enseigne_client;
	}
	public void setEnseigne_client(String enseigne_client) {
		this.enseigne_client = enseigne_client;
	}
	public String getAdresse_client() {
		return adresse_client;
	}
	public void setAdresse_client(String adresse_client) {
		this.adresse_client = adresse_client;
	}

}
