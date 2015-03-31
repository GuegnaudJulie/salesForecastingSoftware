package fr.galettedebroons.model.selectBase;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Profil;
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
	
	public Boolean[] recuperationJoursTournee(Profil profil){
		Object[] joursLivraison = manager_.createQuery("select t.lundi, t.mardi, t.mercredi, t.jeudi, t.vendredi, t.samedi, t.dimanche from Tournee t where profil_tournee LIKE :profil", Object[].class)
				.setParameter("profil", profil)
				.getSingleResult();
		
		System.out.println(joursLivraison[0]);
		
		int i = 0;
		if (i == 1){
			System.out.println("bwaaaa !!!!");
		}
		
		return null;
	}
}
