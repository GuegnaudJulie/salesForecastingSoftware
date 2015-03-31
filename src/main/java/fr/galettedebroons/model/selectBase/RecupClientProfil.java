package fr.galettedebroons.model.selectBase;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.main.Main;

public class RecupClientProfil {

	public EntityManager manager_;
	
	public RecupClientProfil(Main main){
		manager_ = main.getManager();
	}
	
	public boolean recuperationProfil(String code_client){
		boolean present;
		
		try{
			Profil p = manager_.createQuery("select p from Profil p where p.code_client LIKE :code", Profil.class)
					.setParameter("code", code_client)
					.getSingleResult();
			present = true;
		} catch (Exception e){
			present = false;
		}
		
		return present;
	}
	
}
