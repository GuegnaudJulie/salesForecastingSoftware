package fr.galettedebroons.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Profil {
	
	private String id;
	private Client client_profil;
	private Gamme gamme_profil;
	private List<Livraison> livraison_profil;
	private Boolean actif;
	private Tournee profil_tournee;
	private MargeLivraison marge_profil;
	
	public Profil(){}
	
	public Profil(Gamme code_gamme, List<Livraison> livraison, Boolean actif) {
		this.gamme_profil = code_gamme;
		this.livraison_profil = livraison;
		this.actif = actif;
	}
	
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}
	
	@ManyToOne
	public Gamme getGamme_profil() {
		return gamme_profil;
	}
	
	public void setGamme_profil(Gamme gamme_profil) {
		this.gamme_profil = gamme_profil;
	}
	
	@OneToMany(mappedBy="livraison_profil")
	public List<Livraison> getLivraison_profil() {
		return livraison_profil;
	}
	
	public void setLivraison_profil(List<Livraison> livraison_profil) {
		this.livraison_profil = livraison_profil;
	}

	@ManyToOne
	public Client getClient_profil() {
		return client_profil;
	}

	public void setClient_profil(Client client) {
		this.client_profil = client;
	}

	@ManyToOne
	public Tournee getProfil_tournee() {
		return profil_tournee;
	}

	public void setProfil_tournee(Tournee profil_tournee) {
		this.profil_tournee = profil_tournee;
	}

	@ManyToOne
	public MargeLivraison getMarge_profil() {
		return marge_profil;
	}

	public void setMarge_profil(MargeLivraison marge_profil) {
		this.marge_profil = marge_profil;
	}
}
