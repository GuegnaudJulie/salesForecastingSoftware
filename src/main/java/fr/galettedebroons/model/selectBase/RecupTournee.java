package fr.galettedebroons.model.selectBase;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Tournee;
import fr.galettedebroons.main.Main;

public class RecupTournee {

	public EntityManager manager_;
	
	public RecupTournee(Main main){
		manager_ = main.getManager();
	}
	
	public Tournee recuperationTournee(String nom){
		Tournee tournee = manager_.createQuery("select t from Tournee t where nom LIKE :nom", Tournee.class)
				.setParameter("nom", nom)
				.getSingleResult();
		
		return tournee;
	}
	
}
