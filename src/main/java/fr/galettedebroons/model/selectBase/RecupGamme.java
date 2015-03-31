package fr.galettedebroons.model.selectBase;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.main.Main;

public class RecupGamme {

	public EntityManager manager_;
	
	public RecupGamme(Main main){
		manager_ = main.getManager();
	}
	
	public Gamme recuperationGamme(String code_gamme){
		Gamme g = manager_.createQuery("select g from Gamme g where code_gamme LIKE :code", Gamme.class)
				.setParameter("code", code_gamme)
				.getSingleResult();
		
		return g;
	}
	
	public int recuperationValiditeGamme(Gamme g){
		int duree = manager_.createQuery("select g.duree_conservation from Gamme g where g LIKE :gamme", int.class)
				.setParameter("gamme", g)
				.getSingleResult(); 
		
		return duree;
	}
}
