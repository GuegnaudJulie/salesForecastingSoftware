package fr.galettedebroons.model.selectBase;

import java.util.List;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.main.Main;

public class RecupProduit {

	public EntityManager manager_;
	
	public RecupProduit(Main main){
		manager_ = main.getManager();
	}
	
	public List<Produit> listTousProduit(){
		List<Produit> prod = manager_.createQuery("select p FROM Produit p", Produit.class).getResultList();
		
		return prod;
	}
}
