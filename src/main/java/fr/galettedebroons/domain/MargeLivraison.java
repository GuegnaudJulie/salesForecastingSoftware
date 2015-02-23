package fr.galettedebroons.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * connaitre 
 * @author 14008374
 *
 */

@Entity
public class MargeLivraison {
	
	private int id;
	private double taux_reprise;
	private List<Profil> marge_profil;
	private Gamme gamme_marge;
	
	public MargeLivraison(){}
	
	public MargeLivraison(double taux_reprise) {
		this.taux_reprise = taux_reprise;
	}
	
	public double getTaux_reprise() {
		return taux_reprise;
	}

	public void setTaux_reprise(double taux_reprise) {
		this.taux_reprise = taux_reprise;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(mappedBy="marge_profil")
	public List<Profil> getMarge_profil() {
		return marge_profil;
	}

	public void setMarge_profil(List<Profil> marge_profil) {
		this.marge_profil = marge_profil;
	}

	@OneToOne(mappedBy="gamme_marge")
	public Gamme getGamme_marge() {
		return gamme_marge;
	}

	public void setGamme_marge(Gamme gamme_marge) {
		this.gamme_marge = gamme_marge;
	}
}