package fr.galettedebroons.model.accessBase;

import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Gamme;
import fr.galettedebroons.domain.Produit;
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
	
	public String[] recuperationGamme(){
		List<String> ListGamme = manager_.createQuery("select g.code_gamme " +
				"from Gamme g ", String.class).getResultList();
		
		String[] gamme = new String[ListGamme.size()];
		int i = 0;
		for (String g : ListGamme){
			gamme[i] = g;
			i++;
		}
		
		return gamme;
	}
	
	public int recuperationValiditeGamme(Gamme g){
		Integer duree = manager_.createQuery("select g.duree_conservation from Gamme g where g LIKE :gamme", Integer.class)
				.setParameter("gamme", g)
				.getSingleResult(); 
		
		return duree;
	}
	
	public boolean gammePresent(String code){
		boolean present;
		try{
			manager_.createQuery("select g from Gamme g where code_gamme LIKE :code", Gamme.class)
					.setParameter("code", code)
					.getSingleResult();
			present = true;
		} catch (Exception e) {
			present = false;
		}
		return present;
	}
	
	public Gamme recuperationGammeProd(Produit prod){
		Gamme gamme = manager_.createNamedQuery("select p.produit_gamme from Produit p where p LIKE :prod", Gamme.class)
				.setParameter("prod", prod)
				.getSingleResult();
		
		return gamme;
	}

	public Gamme recuperationGammeProduit(String code_produit){
		Gamme gamme = manager_.createQuery("select p.produit_gamme from Produit p where code_produit LIKE :cp", Gamme.class).setParameter("cp", code_produit).getSingleResult();	
		return gamme;
	}
	
}
