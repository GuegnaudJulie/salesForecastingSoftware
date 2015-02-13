package fr.galettedebroons.domain;

import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Tournee {
	
	@Id
	private int id_tournee;
	private String nom;
	private String jour_tournee;
	private ProfilClient profil;
	
	public Tournee(int id_tournee, String nom, String jour_tournee, ProfilClient p) {
		this.id_tournee = id_tournee;
		this.nom = nom;
		this.jour_tournee = jour_tournee;
		this.profil = p;
	}
	
	public int getId_tournee() {
		return id_tournee;
	}
	public void setId_tournee(int id_tournee) {
		this.id_tournee = id_tournee;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany
	public String getJour_tournee() {
		return jour_tournee;
	}

	public void setJour_tournee(String jour_tournee) {
		this.jour_tournee = jour_tournee;
	}

	public ProfilClient getProfil() {
		return profil;
	}

	public void setProfil(ProfilClient profil) {
		this.profil = profil;
	}
	

}
