package fr.galettedebroons.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	@Id
	private String code_client;
	private String enseigne_client;
	private String adresse_client;
	private ArrayList<ProfilClient> client_profil;
		
	@OneToMany(mappedBy="client")
	public ArrayList<ProfilClient> getClient_profil() {
		return client_profil;
	}

	public void setClient_profil(ArrayList<ProfilClient> client_profil) {
		this.client_profil = client_profil;
	}

	public Client(String code_client, String enseigne_client, String adresse_client, ArrayList<ProfilClient> cp) {
		this.code_client = code_client;
		this.enseigne_client = enseigne_client;
		this.adresse_client = adresse_client;
		this.client_profil = cp;
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
