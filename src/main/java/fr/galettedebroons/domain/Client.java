package fr.galettedebroons.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	private String code_client;
	private String enseigne_client;
	private List<Profil> client_profil;

	public Client(){}
	
	public Client(String code_client, String enseigne_client, List<Profil> client_profil) {
		this.code_client = code_client;
		this.enseigne_client = enseigne_client;
		this.client_profil = client_profil;
	}
	
	@Id
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
	
	@OneToMany(mappedBy="client_profil")
	public List<Profil> getClient_profil() {
		return client_profil;
	}

	public void setClient_profil(List<Profil> client_profil) {
		this.client_profil = client_profil;
	}
}
