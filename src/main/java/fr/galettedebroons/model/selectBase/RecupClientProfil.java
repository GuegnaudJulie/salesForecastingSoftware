package fr.galettedebroons.model.selectBase;

import javax.persistence.EntityManager;
import fr.galettedebroons.test.Main;

public class RecupClientProfil {

	public EntityManager manager_;
	
	public RecupClientProfil(Main main){
		manager_ = main.getManager();
	}
	
}
