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
	private boolean lundi = false;
	private boolean mardi = false;
	private boolean mercredi = false;
	private boolean jeudi = false;
	private boolean vendredi = false;
	private boolean samedi = false;
	private boolean dimanche = false;
	private List<Profil> profil_tournee;
	
	public Tournee(){}
	
	public Tournee(String nom, boolean lundi, boolean mardi, boolean mercredi, boolean jeudi,
			boolean vendredi, boolean samedi, boolean dimanche, List<Profil> profil_tournee) {
		this.nom = nom;
		this.lundi = lundi;
		this.mardi = mardi;
		this.mercredi = mercredi;
		this.jeudi = jeudi;
		this.vendredi = vendredi;
		this.samedi = samedi;
		this.dimanche = dimanche;
		this.profil_tournee = profil_tournee;
	}
	
	public Tournee(String nom, boolean lundi, boolean mardi, boolean mercredi, boolean jeudi,
			boolean vendredi, boolean samedi, boolean dimanche) {
		this.nom = nom;
		this.lundi = lundi;
		this.mardi = mardi;
		this.mercredi = mercredi;
		this.jeudi = jeudi;
		this.vendredi = vendredi;
		this.samedi = samedi;
		this.dimanche = dimanche;
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

	public boolean isLundi() {
		return lundi;
	}

	public void setLundi(boolean lundi) {
		this.lundi = lundi;
	}

	public boolean isMardi() {
		return mardi;
	}

	public void setMardi(boolean mardi) {
		this.mardi = mardi;
	}

	public boolean isMercredi() {
		return mercredi;
	}

	public void setMercredi(boolean mercredi) {
		this.mercredi = mercredi;
	}

	public boolean isJeudi() {
		return jeudi;
	}

	public void setJeudi(boolean jeudi) {
		this.jeudi = jeudi;
	}

	public boolean isVendredi() {
		return vendredi;
	}

	public void setVendredi(boolean vendredi) {
		this.vendredi = vendredi;
	}

	public boolean isSamedi() {
		return samedi;
	}

	public void setSamedi(boolean samedi) {
		this.samedi = samedi;
	}

	public boolean isDimanche() {
		return dimanche;
	}

	public void setDimanche(boolean dimanche) {
		this.dimanche = dimanche;
	}

	@OneToMany(mappedBy="profil_tournee", cascade=CascadeType.PERSIST)
	public List<Profil> getProfil_tournee() {
		return profil_tournee;
	}

	public void setProfil_tournee(List<Profil> profil_tournee) {
		this.profil_tournee = profil_tournee;
	}
	

}
