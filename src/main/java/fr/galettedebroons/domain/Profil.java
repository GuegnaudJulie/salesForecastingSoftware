package fr.galettedebroons.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Profil {
	
	private String code_client;
	private Client client_profil;
	private List<Livraison> livraison_profil;
	private Boolean actif;
	private List<Tournee> profil_tournee;
	private List<MargeLivraison> marge_profil;
	private List<Prevision> prevision_profil;
	private List<QuantiteReelle> reel_profil;
	
	public Profil(){}
	
	public Profil(List<Livraison> livraison, Boolean actif) {
		this.livraison_profil = livraison;
		this.actif = actif;
	}
	
	public Profil(String code_client, List<Livraison> livraison,List<Prevision> prev, List<QuantiteReelle> reel_profil, List<MargeLivraison> marge_profil, Boolean actif) {
		this.code_client = code_client;
		this.livraison_profil = livraison;
		this.prevision_profil = prev;
		this.reel_profil = reel_profil;
		this.marge_profil = marge_profil;
		this.actif = actif;
	}
	
	@Id
	public String getCode_client() {
		return code_client;
	}

	public void setCode_client(String code_client) {
		this.code_client = code_client;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}
	
	@OneToMany(mappedBy="livraison_profil", cascade=CascadeType.PERSIST)
	public List<Livraison> getLivraison_profil() {
		return livraison_profil;
	}
	
	public void setLivraison_profil(List<Livraison> livraison_profil) {
		this.livraison_profil = livraison_profil;
	}

	public void addLivraison(Livraison l){
		livraison_profil.add(l);
	}
	
	@ManyToOne
	public Client getClient_profil() {
		return client_profil;
	}

	public void setClient_profil(Client client) {
		this.client_profil = client;
	}

	@ManyToMany
	public List<Tournee> getProfil_tournee() {
		return profil_tournee;
	}

	public void setProfil_tournee(List<Tournee> profil_tournee) {
		this.profil_tournee = profil_tournee;
	}
	
	public void addTournee(Tournee tournee){
		profil_tournee.add(tournee);
	}

	@OneToMany(mappedBy="marge_profil", cascade=CascadeType.PERSIST)
	public List<MargeLivraison> getMarge_profil() {
		return marge_profil;
	}

	public void setMarge_profil(List<MargeLivraison> marge_profil) {
		this.marge_profil = marge_profil;
	}

	public void addMarge(MargeLivraison m){
		marge_profil.add(m);
	}
	
	@OneToMany(mappedBy="prevision_profil", cascade=CascadeType.PERSIST)
	public List<Prevision> getPrevision_profil() {
		return prevision_profil;
	}

	public void setPrevision_profil(List<Prevision> prevision_profil) {
		this.prevision_profil = prevision_profil;
	}
	
	public void addPrevision(Prevision p){
		prevision_profil.add(p);
	}

	@OneToMany(mappedBy="reel_profil", cascade=CascadeType.PERSIST)
	public List<QuantiteReelle> getReel_profil() {
		return reel_profil;
	}

	public void setReel_profil(List<QuantiteReelle> reel_profil) {
		this.reel_profil = reel_profil;
	}
	
	public void addReel(QuantiteReelle p){
		reel_profil.add(p);
	}
}
