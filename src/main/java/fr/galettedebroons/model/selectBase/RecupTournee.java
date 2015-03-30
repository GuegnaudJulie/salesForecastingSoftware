package fr.galettedebroons.model.selectBase;

import javax.persistence.EntityManager;
import fr.galettedebroons.test.Main;

public class RecupTournee {

	public EntityManager manager_;
	
	public RecupTournee(Main main){
		manager_ = main.getManager();
	}
	
}
