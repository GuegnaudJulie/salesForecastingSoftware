package fr.galettedebroons.model.selectBase;

import java.util.List;
import javax.persistence.EntityManager;
import fr.galettedebroons.test.Main;

public class RecupTemporaire {
	
	public EntityManager manager_;
	
	public RecupTemporaire(Main main){
		manager_ = main.getManager();
	}

	public List<String> recuperationProduitTemp(){
		List<String> produit = manager_.createQuery("select distinct t.code_produit " +
				"from Temporaire t where code_erreur = 'CP' or  code_erreur = 'P'", String.class).getResultList();
		return produit;
	}
	
	public List<Object[]> recuperationClientTemp(){
		List<Object[]> produit = manager_.createQuery("select distinct t.nom_client, t.code_profil " +
				"from Temporaire t where code_erreur = 'CP' or  code_erreur = 'P'", Object[].class).getResultList();
		return produit;
	}
	
	public boolean recuperationClientInexistant(){
		boolean innexistant = false;
		
		List<String> listCodeErreur = manager_.createQuery("Select distinct t.code_erreur from Temporaire t", String.class)
				.getResultList();
		
		if (listCodeErreur.contains("C") | listCodeErreur.contains("CP"))
			innexistant = true;
		
		return innexistant;
	}
	
	public boolean recuperationProdInexistant(){
		boolean innexistant = false;
		
		List<String> listCodeErreur = manager_.createQuery("Select distinct t.code_erreur from Temporaire t", String.class)
				.getResultList();
		
		if (listCodeErreur.contains("P") | listCodeErreur.contains("CP"))
			innexistant = true;
		
		return innexistant;
	}
	
}
