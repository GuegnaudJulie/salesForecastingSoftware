package fr.galettedebroons.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProfilClient {
	
	@Id
	private int id;
	private Client client;
	private Gamme code_gamme;
	private List<Livraison> livraison;
	private Boolean actif;
	
		
	public ProfilClient(Gamme code_gamme, List<Livraison> livraison, Boolean actif) {
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
	
	public Gamme getCode_gamme() {
		return code_gamme;
	}
	public void setCode_gamme(Gamme code_gamme) {
		this.code_gamme = code_gamme;
	}
	
	@OneToMany(mappedBy="profil")
	public List<Livraison> getLivraison() {
		return livraison;
	}
	public void setLivraison(List<Livraison> livraison) {
		this.livraison = livraison;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
