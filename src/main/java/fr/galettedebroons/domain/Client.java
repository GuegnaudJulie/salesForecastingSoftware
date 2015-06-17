package fr.galettedebroons.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	private int id_client;
	private String enseigne_client;
	private List<Profil> client_profil;

	public Client(){}
	
	public Client(String enseigne_client) {
		this.enseigne_client = enseigne_client;
	}
	
	public Client(String enseigne_client, List<Profil> client_profil) {
		this.enseigne_client = enseigne_client;
		this.setClient_profil(client_profil);
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public int getCode_client() {
		return id_client;
	}
	
	public void setCode_client(int id) {
		this.id_client = id;
	}
	
	public String getEnseigne_client() {
		return enseigne_client;
	}
	
	public void setEnseigne_client(String enseigne_client) {
		this.enseigne_client = enseigne_client;
	}
	
	@OneToMany(mappedBy="client_profil", cascade=CascadeType.PERSIST)
	public List<Profil> getClient_profil() {
		return client_profil;
	}

	public void setClient_profil(List<Profil> client_profil) {
		this.client_profil = client_profil;
	}
	
	public void addProfil(Profil profil){
		client_profil.add(profil);
	}
}
