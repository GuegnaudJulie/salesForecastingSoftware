package fr.galettedebroons.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tournee {
	
	private int id;
	private String nom;
	private String jour_tournee;
	private List<Profil> profil_tournee;
	
	public Tournee(){}
	
	public Tournee(int id_tournee, String nom, String jour_tournee, List<Profil> profil_tournee) {
		this.id = id_tournee;
		this.nom = nom;
		this.jour_tournee = jour_tournee;
		this.profil_tournee = profil_tournee;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	public void setId(int id_tournee) {
		this.id = id_tournee;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getJour_tournee() {
		return jour_tournee;
	}

	public void setJour_tournee(String jour_tournee) {
		this.jour_tournee = jour_tournee;
	}

	@OneToMany(mappedBy="profil_tournee", cascade=CascadeType.PERSIST)
	public List<Profil> getProfil_tournee() {
		return profil_tournee;
	}

	public void setProfil_tournee(List<Profil> profil_tournee) {
		this.profil_tournee = profil_tournee;
	}
	

}
