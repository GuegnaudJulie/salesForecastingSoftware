package fr.galettedebroons.model.selectBase;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.MargeLivraison;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.main.Main;

public class RecupMargeLivraison {

	Main main_;
	public EntityManager manager_;
	
	public RecupMargeLivraison(Main main){
		main_ = main;
		manager_ = main.getManager();
	}
	
	public MargeLivraison recuperationML(Profil profil, Produit produit){
		MargeLivraison ml = manager_.createQuery("select ml from MargeLivraison ml where " +
				"marge_profil LIKE :profil AND " +
				"marge_produit LIKE :prod", MargeLivraison.class)
				.setParameter("profil", profil)
				.setParameter("prod", produit)
				.setMaxResults(1)
				.getSingleResult();
		
		return ml;
	}

	public MargeLivraison recuperationML(Profil profil, String code_prod) {
		RecupProduit rp = new RecupProduit(main_);
		Produit produit = rp.recupProduit(code_prod);
		
		MargeLivraison ml = recuperationML(profil, produit);
		
		return ml;
	}
}
