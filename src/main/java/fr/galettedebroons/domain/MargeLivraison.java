package fr.galettedebroons.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * connaitre 
 * @author 14008374
 *
 */
public class MargeLivraison {
	
	@Id
	@GeneratedValue
	private int id;
	private double taux_reprise;
	
	public MargeLivraison(double taux_reprise) {
		this.taux_reprise = taux_reprise;
	}
	
	public double getTaux_reprise() {
		return taux_reprise;
	}

	public void setTaux_reprise(double taux_reprise) {
		this.taux_reprise = taux_reprise;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
