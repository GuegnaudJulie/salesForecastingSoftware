package fr.galettedebroons.model.selectBase;

import javax.persistence.EntityManager;

import fr.galettedebroons.main.Main;

public class RecupProduit {

	public EntityManager manager_;
	
	public RecupProduit(Main main){
		manager_ = main.getManager();
	}
	
}
