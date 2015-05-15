package fr.galettedebroons.model.selectBase;

import java.sql.Date;

import javax.persistence.EntityManager;

import fr.galettedebroons.domain.Prevision;
import fr.galettedebroons.domain.Produit;
import fr.galettedebroons.domain.Profil;
import fr.galettedebroons.main.Main;

public class RecupPrevision {

	public EntityManager manager_;
	
	public RecupPrevision(Main main){
		manager_ = main.getManager();
	}
	
	public Prevision prevision(Profil profil, Produit prod, Date date){
		Prevision prev = manager_.createQuery("select p from Prevision p where date LIKE :date AND " +
				"prevision_profil LIKE :profil AND " +
				"prevision_produit LIKE :produit", Prevision.class)
				.setParameter("profil", profil)
				.setParameter("produit", prod)
				.setParameter("date", date)
				.getSingleResult();
		
		return prev;
	}
}
